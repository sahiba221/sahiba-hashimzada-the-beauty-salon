package com.example.thebeautysalon.service;

import com.example.thebeautysalon.dto.request.BeautyServiceRequestDto;
import com.example.thebeautysalon.dto.response.BeautyServiceResponseDto;
import com.example.thebeautysalon.dto.response.CategoryResponseDto;
import com.example.thebeautysalon.entity.BeautyServiceEntity;
import com.example.thebeautysalon.entity.CategoryEntity;
import com.example.thebeautysalon.mapper.BeautyServiceMapper;
import com.example.thebeautysalon.mapper.CategoryMapper;
import com.example.thebeautysalon.repository.BeautyServiceRepository;
import com.example.thebeautysalon.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeautyServiceService {
    private final BeautyServiceRepository beautyServiceRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BeautyServiceMapper beautyServiceMapper;


    public List<BeautyServiceResponseDto> getAllServices(){
        List<BeautyServiceResponseDto> beautyServices = beautyServiceRepository
                .findAll().stream().map(beautyServiceMapper::toBeautyServiceResponseDto)
                .toList();
        return beautyServices;
    }

    public List<BeautyServiceResponseDto> getAllByCategoryId(Long categoryId){
        categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found with id: " + categoryId));
        List<BeautyServiceResponseDto> services = beautyServiceRepository.findAllByCategoryId(categoryId)
                .stream().map(beautyServiceMapper::toBeautyServiceResponseDto).toList();
        return services;
    }

    public CategoryResponseDto findByIdCategory(Long id){
        CategoryResponseDto category = categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponseDto).orElseThrow(()
                        -> new RuntimeException(""));
        return category;
    }

    public BeautyServiceResponseDto findByIdBeautyService(Long id){
        BeautyServiceResponseDto beautyService = beautyServiceRepository
                .findById(id).map(beautyServiceMapper::toBeautyServiceResponseDto)
                .orElseThrow(() -> new RuntimeException(""));
        return beautyService;
    }

    public BeautyServiceResponseDto create(BeautyServiceRequestDto beautyServiceRequestDto){
        if(beautyServiceRepository.existsByNameIgnoreCase(beautyServiceRequestDto.getName())){
            throw new RuntimeException("");
        }
        BeautyServiceEntity newBeautyService = beautyServiceMapper
                .toBeautyServiceEntity(beautyServiceRequestDto);

        CategoryEntity category = categoryRepository.findById(beautyServiceRequestDto.getCategoryId())
                .orElseThrow(()-> new RuntimeException(""));

        newBeautyService.setCategory(category);
        beautyServiceRepository.save(newBeautyService);
        return beautyServiceMapper.toBeautyServiceResponseDto(newBeautyService);

    }

    public BeautyServiceResponseDto updateById(Long id, BeautyServiceRequestDto beautyServiceRequestDto){
        BeautyServiceEntity beautyService = beautyServiceRepository
                .findById(id).orElseThrow(() -> new RuntimeException(""));

        if(!beautyServiceRequestDto.getName().equalsIgnoreCase(beautyService.getName())
                && beautyServiceRepository.existsByNameIgnoreCase(beautyServiceRequestDto.getName())){
            throw new RuntimeException("");
        }

        CategoryEntity category =categoryRepository.findById(beautyServiceRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException(""));

        beautyService.setDescription(beautyServiceRequestDto.getDescription());
        beautyService.setPrice(beautyServiceRequestDto.getPrice());
        beautyService.setName(beautyServiceRequestDto.getName());
        beautyService.setDurationMinutes(beautyServiceRequestDto.getDurationMinutes());
        beautyService.setCategory(category);
        BeautyServiceEntity updatedBeautyService = beautyServiceRepository.save(beautyService);
        return beautyServiceMapper.toBeautyServiceResponseDto(updatedBeautyService);
    }

    public void deleteById(long id){
        BeautyServiceEntity beautyService = beautyServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));

        if(beautyService.getBookings().isEmpty()){
            throw new RuntimeException("");
        }
        beautyServiceRepository.deleteById(id);
    }
}
