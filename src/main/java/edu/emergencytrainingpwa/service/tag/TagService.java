package edu.emergencytrainingpwa.service.tag;

import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;

public interface TagService {
    TagDto createTag(TagAddDto tagDto);
}
