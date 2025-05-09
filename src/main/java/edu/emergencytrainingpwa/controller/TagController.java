package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.dto.tag.TagUpdateDto;
import edu.emergencytrainingpwa.dto.tag.TagAddDto;
import edu.emergencytrainingpwa.dto.tag.TagDto;
import edu.emergencytrainingpwa.service.tag.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping()
    public ResponseEntity<TagDto> createTag(@RequestBody TagAddDto tagDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(tagDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(
        @Valid @RequestBody TagUpdateDto tagUpdateDto,
        @PathVariable Long id) {
        return ResponseEntity.ok().body(tagService.updateTag(id,tagUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }
}
