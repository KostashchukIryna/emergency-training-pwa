package edu.emergencytrainingpwa.service.course;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.Category;
import edu.emergencytrainingpwa.dao.entity.Course;
import edu.emergencytrainingpwa.dao.entity.Tag;
import edu.emergencytrainingpwa.dao.entity.User;
import edu.emergencytrainingpwa.dao.repository.CategoryRepo;
import edu.emergencytrainingpwa.dao.repository.CourseRepo;
import edu.emergencytrainingpwa.dao.repository.TagRepo;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.course.*;
import edu.emergencytrainingpwa.dto.courseModule.CourseModuleDto;
import edu.emergencytrainingpwa.dto.pageable.PageableDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.user.AuthorDto;
import edu.emergencytrainingpwa.exception.NotFoundException;
import edu.emergencytrainingpwa.exception.WrongEmailException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;
    private final ModelMapper modelMapper;

    /**
     * @param courseDto, userEmail
     */
    @Override
    public CourseResponseDto addNewCourse(CourseAddDto courseDto, String userEmail) {
        validateTitle(courseDto.getTitle());
        validateDescription(courseDto.getDescription());
        validateBenefits(courseDto.getBenefits());

        Course course = createNewCourse(courseDto, userEmail);
        return modelMapper.map(courseRepo.save(course), CourseResponseDto.class);
    }

    private Course createNewCourse(CourseAddDto courseDto, String userEmail) {
        User user = userRepo.findByEmail(userEmail)
            .orElseThrow(() -> new WrongEmailException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + userEmail));

        Category category = categoryRepo.findById(courseDto.getCategoryId())
            .orElseThrow(() ->
                new EntityNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND + courseDto.getCategoryId())
            );

        List<Tag> tagList = tagRepo.findAllById(courseDto.getTagIds());
        if (tagList.size() != courseDto.getTagIds().size()) {
            throw new EntityNotFoundException(ErrorMessage.TAGS_NOT_FOUND);
        }
        Set<Tag> tags = new HashSet<>(tagList);

        return Course.builder()
            .title(courseDto.getTitle())
            .description(courseDto.getDescription())
            .preview(courseDto.getPreview())
            .targetUsersText(courseDto.getTargetUsersText())
            .published(courseDto.getPublished())
            .imagePath(courseDto.getImagePath())
            .benefits(courseDto.getBenefits())
            .author(user)
            .category(category)
            .tags(tags)
            .build();
    }

    @Override
    public PageableDto<CourseCardDto> getCourses(Pageable pageable,
                                                 FilterCourseDto filter) {
        Page<CourseCardDto> page =
            courseRepo.findCourseCardsByFilter(filter, pageable);

        return new PageableDto<>(
                     page.getContent(),
                     page.getTotalElements(),
                     page.getNumber(),
                     page.getTotalPages(),
                     page.getSize(),
                     page.hasPrevious(),
                     page.hasNext(),
                     page.isFirst(),
                     page.isLast()
        );
    }

    @Override
    public CourseDetailDto getCourseById(Long id) {
        Course c = courseRepo.findByIdAndPublishedTrue(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.COURSE_NOT_FOUND + id));

        return CourseDetailDto.builder()
            .id(c.getId())
            .title(c.getTitle())
            .description(c.getDescription())
            .preview(c.getPreview())
            .targetUsersText(c.getTargetUsersText())
            .imagePath(c.getImagePath())
            .rating(c.getRating())
            .category(CategoryDto.builder()
                .id(c.getCategory().getId())
                .title(c.getCategory().getTitle())
                .emoji(c.getCategory().getEmoji())
                .build()
            )
            .author(AuthorDto.builder()
                .id(c.getAuthor().getId())
                .firstName(c.getAuthor().getFirstName())
                .lastName(c.getAuthor().getLastName())
                .build()
            )
            .benefits(c.getBenefits())
            .tags(c.getTags().stream()
                .map(t -> new TagDto(t.getId(), t.getTitle()))
                .collect(Collectors.toSet())
            )
            .modules(c.getModules().stream()
                .map(m -> new CourseModuleDto(
                    m.getId(),
                    m.getTitle(),
                    m.getDescription(),
                    m.getImagePath(),
                    m.getOrderNumber()
                ))
                .collect(Collectors.toList())
            )
            .createdAt(c.getCreatedAt())
            .build();
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title) || title.length() > 200) {
            throw new IllegalArgumentException(
                "Title is required and must be at most 200 characters."
            );
        }
    }

    private void validateDescription(String description) {
        if (!StringUtils.hasText(description)) {
            throw new IllegalArgumentException("Description must not be empty.");
        }
    }

    private void validateBenefits(java.util.List<String> benefits) {
        if (benefits == null || benefits.isEmpty()) {
            throw new IllegalArgumentException("At least one benefit is required.");
        }
        for (String b : benefits) {
            if (!StringUtils.hasText(b)) {
                throw new IllegalArgumentException("Benefit entries must not be blank.");
            }
        }
    }
    
}
