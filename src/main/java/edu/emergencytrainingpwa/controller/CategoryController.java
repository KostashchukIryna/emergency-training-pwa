package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.category.CategoryAddDto;
import edu.emergencytrainingpwa.dto.category.CategoryDto;
import edu.emergencytrainingpwa.dto.category.CategoryUpdateDto;
import edu.emergencytrainingpwa.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryAddDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createNewCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
        @Valid @RequestBody CategoryUpdateDto categoryUpdateDto,
        @PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.updateCategory(id,categoryUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
