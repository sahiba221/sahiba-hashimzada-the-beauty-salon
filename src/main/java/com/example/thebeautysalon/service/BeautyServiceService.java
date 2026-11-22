package com.example.thebeautysalon.service;

import com.example.thebeautysalon.dto.request.BeautyServiceRequestDto;
import com.example.thebeautysalon.dto.response.BeautyServiceResponseDto;
import com.example.thebeautysalon.dto.response.CategoryResponseDto;
import com.example.thebeautysalon.entity.BeautyServiceEntity;
import com.example.thebeautysalon.entity.CategoryEntity;
import com.example.thebeautysalon.exception.AlreadyExistsException;
import com.example.thebeautysalon.exception.BadRequestException;
import com.example.thebeautysalon.exception.ResourceNotFoundException;
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
    private final CategoryMapper categoryMapper; // (Bunu gələcəkdə silə bilərsən, CategoryService-də var)
    private final BeautyServiceMapper beautyServiceMapper;


    public List<BeautyServiceResponseDto> getAllServices(){
        List<BeautyServiceResponseDto> beautyServices = beautyServiceRepository
                .findAll().stream().map(beautyServiceMapper::toBeautyServiceResponseDto)
                .toList();
        return beautyServices;
    }

    public List<BeautyServiceResponseDto> getAllByCategoryId(Long categoryId){
        categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category not found with id: " + categoryId));
        List<BeautyServiceResponseDto> services = beautyServiceRepository.findAllByCategoryId(categoryId)
                .stream().map(beautyServiceMapper::toBeautyServiceResponseDto).toList();
        return services;
    }

    public CategoryResponseDto findByIdCategory(Long id){
        CategoryResponseDto category = categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponseDto).orElseThrow(()
                        -> new ResourceNotFoundException("Category not found with id: " + id));
        return category;
    }

    public BeautyServiceResponseDto findByIdBeautyService(Long id){
        BeautyServiceResponseDto beautyService = beautyServiceRepository
                .findById(id).map(beautyServiceMapper::toBeautyServiceResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Beauty service not found with id: " + id));
        return beautyService;
    }

    public BeautyServiceResponseDto create(BeautyServiceRequestDto beautyServiceRequestDto){
        if(beautyServiceRepository.existsByNameIgnoreCase(beautyServiceRequestDto.getName())){
            throw new AlreadyExistsException("Beauty service already exists with name: " + beautyServiceRequestDto.getName());
        }
        BeautyServiceEntity newBeautyService = beautyServiceMapper
                .toBeautyServiceEntity(beautyServiceRequestDto);

        CategoryEntity category = categoryRepository.findById(beautyServiceRequestDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + beautyServiceRequestDto.getCategoryId()));

        newBeautyService.setCategory(category);
        beautyServiceRepository.save(newBeautyService);
        return beautyServiceMapper.toBeautyServiceResponseDto(newBeautyService);

    }

    public BeautyServiceResponseDto updateById(Long id, BeautyServiceRequestDto beautyServiceRequestDto){
        BeautyServiceEntity beautyService = beautyServiceRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Beauty service not found with id: " + id));

        if(!beautyServiceRequestDto.getName().equalsIgnoreCase(beautyService.getName())
                && beautyServiceRepository.existsByNameIgnoreCase(beautyServiceRequestDto.getName())){
            throw new AlreadyExistsException("Beauty service already exists with name: " + beautyServiceRequestDto.getName());
        }

        CategoryEntity category =categoryRepository.findById(beautyServiceRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + beautyServiceRequestDto.getCategoryId()));

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
                .orElseThrow(() -> new ResourceNotFoundException("Beauty service not found with id: " + id));

        if(!beautyService.getBookings().isEmpty()){
            throw new BadRequestException("Cannot delete beauty service because it has active bookings.");
        }
        beautyServiceRepository.deleteById(id);
    }
}