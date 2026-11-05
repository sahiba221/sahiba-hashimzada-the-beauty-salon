package com.example.thebeautysalon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "masters")
public class MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    @Column(nullable = false)
    private String specialization;
    private BigDecimal rating;
    private Integer experienceYears;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    List<WorkScheduleEntity> workScheduleEntities = new ArrayList<>();
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    List<BookingEntity> bookings = new ArrayList<>();
    @OneToMany(mappedBy = "master")
    List<ReviewEntity> reviews = new ArrayList<>();
}
