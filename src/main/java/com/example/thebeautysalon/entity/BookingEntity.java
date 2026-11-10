package com.example.thebeautysalon.entity;

import com.example.thebeautysalon.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private MasterEntity master;
    @ManyToOne
    @JoinColumn(name = "beauty_service_id",nullable = false)
    private BeautyServiceEntity beautyService;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)
    private EnumStatus enumStatus;
    private BigDecimal price;

}
