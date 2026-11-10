package com.example.thebeautysalon.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
}
