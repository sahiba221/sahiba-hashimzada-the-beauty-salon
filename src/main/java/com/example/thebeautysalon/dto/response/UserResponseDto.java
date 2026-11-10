package com.example.thebeautysalon.dto.response;

import com.example.thebeautysalon.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private EnumRole enumRole;
    private BigDecimal bonusBalance;

}
