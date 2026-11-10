package com.example.thebeautysalon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterResponseDto {
    private Long id;
    private Long userId;
    private String specialization;
    private BigDecimal rating;
    private Integer experienceYears;
    private Long categoryId;
    private List<WorkScheduleResponseDto> workSchedules;
    private List<ReviewResponseDto> reviews;
}
