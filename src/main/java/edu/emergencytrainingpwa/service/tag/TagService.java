package edu.emergencytrainingpwa.service.tag;

import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.tag.TagUpdateDto;
import jakarta.validation.Valid;

public interface TagService {
    TagDto createTag(TagAddDto tagDto);

    TagDto updateTag(Long id, @Valid TagUpdateDto tagUpdateDto);

    void deleteTag(Long id);
}
