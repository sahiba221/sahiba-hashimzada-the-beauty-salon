package com.example.thebeautysalon.dto.response;

import com.example.thebeautysalon.enums.EnumStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private Long masterId;
    private Long beautyServiceId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EnumStatus status;
    private BigDecimal price;

}
