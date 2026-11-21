package com.example.thebeautysalon.mapper;

import com.example.thebeautysalon.dto.request.BeautyServiceRequestDto;
import com.example.thebeautysalon.dto.response.BeautyServiceResponseDto;
import com.example.thebeautysalon.entity.BeautyServiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeautyServiceMapper {
    BeautyServiceEntity toBeautyServiceEntity(BeautyServiceRequestDto beautyServiceRequestDto);
    BeautyServiceResponseDto toBeautyServiceResponseDto(BeautyServiceEntity beautyService);

}
