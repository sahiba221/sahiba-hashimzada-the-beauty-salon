package com.example.thebeautysalon.mapper;

import com.example.thebeautysalon.dto.request.MasterRequestDto;
import com.example.thebeautysalon.dto.response.MasterResponseDto;
import com.example.thebeautysalon.entity.MasterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MasterMapper {
    MasterEntity toMasterEntity(MasterRequestDto masterRequestDto);
    MasterResponseDto toMasterResponseDto(MasterEntity masterEntity);
}
