package com.example.thebeautysalon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long masterId;
    private String masterName;
    private Long userId;
    private String userFullName;
    private Long bookingId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;


}
