package com.example.thebeautysalon.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private Long masterId;
    private Long userId ;
    private Long bookingId;
    private Integer rating;
    private String comment;
}
