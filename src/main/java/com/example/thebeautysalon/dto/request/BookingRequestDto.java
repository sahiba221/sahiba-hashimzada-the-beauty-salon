package com.example.thebeautysalon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Master ID is required")
    private Long masterId;
    @NotNull(message = "beauty service ID is required")
    private Long beautyServiceId;
    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;
    @NotNull(message = "Start time is required")
    private LocalTime startTime;
    @NotNull(message = "End time is required")
    private LocalTime endTime;
    @NotNull(message = "Price is required")
    private BigDecimal price;

}
