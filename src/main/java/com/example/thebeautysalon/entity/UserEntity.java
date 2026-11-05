package com.example.thebeautysalon.entity;

import com.example.thebeautysalon.enums.EnumRole;
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
@Table( name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private String fullName;
    @Column( nullable = false, unique = true)
    private String email;
    @Column( nullable = false, unique = true)
    private String phoneNumber;
    @Column( nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private EnumRole enumRole;
    @Column( nullable = false)
    private BigDecimal bonusBalance;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<BookingEntity> bookings = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    List<ReviewEntity> reviews = new ArrayList<>();

}
