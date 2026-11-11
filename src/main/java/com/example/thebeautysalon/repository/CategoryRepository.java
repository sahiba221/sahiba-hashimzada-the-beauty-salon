package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Boolean existsByNameIgnoreCase(String name);
    List<CategoryEntity> findByNameContainingIgnoreCase(String name);
}
