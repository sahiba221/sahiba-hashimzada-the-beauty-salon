package com.example.thebeautysalon.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}
