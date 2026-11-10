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
@Table( name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    //    Default fetchType.Lazy
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<BeautyServiceEntity> beautyServiceEntities = new ArrayList<>();
    //    Default fetchType.Lazy
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<MasterEntity> masters = new ArrayList<>();
}
