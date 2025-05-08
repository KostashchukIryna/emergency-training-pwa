package edu.emergencytrainingpwa.service.tag;

import edu.emergencytrainingpwa.dao.entity.Tag;
import edu.emergencytrainingpwa.dao.repository.TagRepo;
import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
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

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title) || title.length() > 200) {
            throw new IllegalArgumentException(
                "Title is required and must be at most 200 characters."
            );
        }
    }
}
