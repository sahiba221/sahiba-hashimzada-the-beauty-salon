package com.example.thebeautysalon.service;

import com.example.thebeautysalon.dto.request.CategoryRequestDto;
import com.example.thebeautysalon.dto.response.CategoryResponseDto;
import com.example.thebeautysalon.entity.CategoryEntity;
import com.example.thebeautysalon.mapper.CategoryMapper;
import com.example.thebeautysalon.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDto> getAll() {
        List<CategoryResponseDto> categories = categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponseDto).toList();
        return categories;
    }

    public CategoryResponseDto getById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not" +
                        " found with id: " + id));
        return categoryMapper.toCategoryResponseDto(category);
    }

    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryRequestDto.getName())) {
            throw new RuntimeException("Category with name : " +
                    categoryRequestDto.getName() + "already exists ");
        }
        CategoryEntity category = categoryMapper.toCategoryEntity(categoryRequestDto);
        CategoryEntity savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(savedCategory);

    }

    public CategoryResponseDto updateById(Long id, CategoryRequestDto categoryRequestDto) {
        CategoryEntity existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found with id: " + id));
        if (!categoryRequestDto.getName().equalsIgnoreCase(existingCategory.getName())
                && categoryRepository.existsByNameIgnoreCase(categoryRequestDto.getName())) {
            throw new RuntimeException("Category with name " +
                    categoryRequestDto.getName() + " already exists");
        }
        existingCategory.setName(categoryRequestDto.getName());
        existingCategory.setDescription(categoryRequestDto.getDescription());
        CategoryEntity updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toCategoryResponseDto(updatedCategory);
    }

    public void deleteById(Long id) {
        CategoryEntity existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found with id: " + id));

        if (!existingCategory.getBeautyServiceEntities().isEmpty() ||
                !existingCategory.getMasters().isEmpty()) {
            throw new RuntimeException("Category cannot be deleted as it contains " +
                    "active masters or services");
        }
        categoryRepository.deleteById(id);
    }

    public List<CategoryResponseDto> searchByName(String name) {
        if(name== null || name.isBlank()){
            throw new RuntimeException("Name cannot be null or blank");
        }
        List<CategoryResponseDto> categories = categoryRepository
                .findByNameContainingIgnoreCase(name).stream()
                .map(categoryMapper::toCategoryResponseDto).toList();
        return categories;

    }

}