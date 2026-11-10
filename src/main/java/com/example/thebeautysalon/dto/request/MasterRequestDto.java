package com.example.thebeautysalon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterRequestDto {
    @NotNull(message = "User ID are required")
    private Long userId;
    @NotBlank(message = "Specialization is required")
    private String specialization;
    @NotNull(message = "Experience years are required")
    @PositiveOrZero
    private Integer experienceYears;
    @NotNull(message = "Category ID are required")
    private Long categoryId;
}
