package com.example.thebeautysalon.controller;


import com.example.thebeautysalon.dto.request.CategoryRequestDto;
import com.example.thebeautysalon.dto.response.CategoryResponseDto;
import com.example.thebeautysalon.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryResponseDto> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryResponseDto create(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.create(categoryRequestDto);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateById(@PathVariable Long id, @Valid
    @RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.updateById(id, categoryRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }

    @GetMapping("/search")
     public List<CategoryResponseDto> searchByName(@RequestParam String name){
        return categoryService.searchByName(name);
     }
}
