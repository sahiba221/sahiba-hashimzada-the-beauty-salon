package com.example.thebeautysalon.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
@Builder
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private MasterEntity master;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private BookingEntity booking;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

}
