package com.example.thebeautysalon.dto.request;

import lombok.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkScheduleRequestDto {
    private Long masterId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isWorking;


}
