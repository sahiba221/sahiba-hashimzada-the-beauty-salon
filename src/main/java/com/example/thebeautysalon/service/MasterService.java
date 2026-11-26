package com.example.thebeautysalon.service;

import com.example.thebeautysalon.dto.request.MasterRequestDto;
import com.example.thebeautysalon.dto.response.MasterResponseDto;
import com.example.thebeautysalon.entity.CategoryEntity;
import com.example.thebeautysalon.entity.MasterEntity;
import com.example.thebeautysalon.entity.UserEntity;
import com.example.thebeautysalon.exception.AlreadyExistsException;
import com.example.thebeautysalon.exception.BadRequestException;
import com.example.thebeautysalon.exception.ResourceNotFoundException;
import com.example.thebeautysalon.mapper.MasterMapper;
import com.example.thebeautysalon.repository.CategoryRepository;
import com.example.thebeautysalon.repository.MasterRepository;
import com.example.thebeautysalon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;
    private final MasterMapper masterMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<MasterResponseDto> findAll(){
        List<MasterResponseDto> masters = masterRepository.findAll().stream()
                .map(masterMapper::toMasterResponseDto).toList();
        return masters;
    }

    public MasterResponseDto findById(Long id){
        MasterResponseDto master = masterRepository.findById(id).map(masterMapper::toMasterResponseDto).
                        orElseThrow(() -> new ResourceNotFoundException("Master not found with id: " + id));
        return master;
    }

    public MasterResponseDto create(MasterRequestDto masterRequestDto){
        MasterEntity newMaster = masterMapper.toMasterEntity(masterRequestDto);
        CategoryEntity category = categoryRepository.findById(masterRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + masterRequestDto.getCategoryId()));
        UserEntity user = userRepository.findById(masterRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + masterRequestDto.getUserId()));

        if(masterRepository.existsByUserId(user.getId())){
            throw new AlreadyExistsException("This user is already registered as a master.");
        }
        newMaster.setCategory(category);
        newMaster.setUser(user);
        newMaster.setExperienceYears(masterRequestDto.getExperienceYears());
        newMaster.setSpecialization(masterRequestDto.getSpecialization());
        newMaster.setRating(BigDecimal.valueOf(0));
        MasterEntity savedMaster = masterRepository.save(newMaster);
        MasterResponseDto masterResponseDto = masterMapper.toMasterResponseDto(savedMaster);
        return masterResponseDto;
    }

    public MasterResponseDto updateById(Long id, MasterRequestDto masterRequestDto){
        MasterEntity master = masterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Master not found with id: " + id));
        CategoryEntity category = categoryRepository.findById(masterRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + masterRequestDto.getCategoryId()));

        master.setCategory(category);

        UserEntity user = userRepository.findById(masterRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + masterRequestDto.getUserId()));

        master.setUser(user);
        master.setSpecialization(masterRequestDto.getSpecialization());
        master.setExperienceYears(masterRequestDto.getExperienceYears());
        MasterEntity savedMaster = masterRepository.save(master);
        MasterResponseDto updatedMaster = masterMapper.toMasterResponseDto(savedMaster);
        return updatedMaster;
    }

    public void deleteById(Long id){
        MasterEntity master = masterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Master not found with id: " + id));

        if(!master.getBookings().isEmpty() || !master.getWorkScheduleEntities().isEmpty()){
            throw new BadRequestException("Master cannot be deleted as they have active bookings or work schedules.");
        }
        masterRepository.deleteById(id);
    }

    public List<MasterResponseDto> getAllByCategoryId(Long categoryId){
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + categoryId));

        List<MasterResponseDto> mastersByCategoryId = masterRepository.findAllByCategoryId(categoryId)
                .stream().map(masterMapper::toMasterResponseDto).toList();

        return mastersByCategoryId;
    }
}