package com.example.thebeautysalon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Description can not be null")
    private String description;
}
