package edu.emergencytrainingpwa.service.tag;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.Tag;
import edu.emergencytrainingpwa.dao.repository.TagRepo;
import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.tag.TagUpdateDto;
import edu.emergencytrainingpwa.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;
    private final ModelMapper modelMapper;

    @Override
    public TagDto createTag(TagAddDto tagDto) {
        validateTitle(tagDto.getTitle());

        Tag newTag = Tag.builder()
            .title(tagDto.getTitle())
            .build();
        return modelMapper.map(tagRepo.save(newTag), TagDto.class);
    }

    @Override
    public TagDto updateTag(Long id, TagUpdateDto tagUpdateDto) {
        validateTagExists(id);
        validateTitle(tagUpdateDto.getTitle());
        Tag updatedTag = Tag.builder()
            .id(id)
            .title(tagUpdateDto.getTitle())
            .build();
        return modelMapper.map(tagRepo.save(updatedTag), TagDto.class);
    }

    @Override
    public void deleteTag(Long id) {
        validateTagExists(id);
        tagRepo.deleteById(id);
    }
    private void validateTagExists(Long id) {
        if (!tagRepo.existsById(id)) {
            throw new NotFoundException(ErrorMessage.TAG_NOT_FOUND);
        }
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title) || title.length() > 200) {
            throw new IllegalArgumentException(
                "Title is required and must be at most 200 characters."
            );
        }
    }
}
