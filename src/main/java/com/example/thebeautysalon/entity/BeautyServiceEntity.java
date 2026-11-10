package com.example.thebeautysalon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "beauty_services")
public class BeautyServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    //    Default fetchType.Lazy
    @OneToMany(mappedBy = "beautyService", cascade = CascadeType.ALL)
    List<BookingEntity> bookings = new ArrayList<>();
    private Integer durationMinutes;

}
