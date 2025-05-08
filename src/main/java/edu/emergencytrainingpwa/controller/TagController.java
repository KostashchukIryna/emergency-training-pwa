package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping()
    public ResponseEntity<TagDto> createTag(@RequestBody TagAddDto tagDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(tagDto));
    }
}
