package com.example.thebeautysalon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeautyServiceRequestDto {
    @NotBlank(message = "Service name cannot be blank")
    private String name;
    private String  description;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotNull(message = "Category id is required")
    private Long categoryId;
    private Integer durationMinutes;

}
