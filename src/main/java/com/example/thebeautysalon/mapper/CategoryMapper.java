package com.example.thebeautysalon.mapper;

import com.example.thebeautysalon.dto.request.CategoryRequestDto;
import com.example.thebeautysalon.dto.response.CategoryResponseDto;
import com.example.thebeautysalon.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(CategoryRequestDto categoryRequestDto);
//    CategoryEntity toCategoryEntity(CategoryResponseDto categoryResponseDto);
//    CategoryRequestDto toCategoryRequestDto(CategoryEntity categoryEntity);
    CategoryResponseDto toCategoryResponseDto(CategoryEntity categoryEntity);
}
