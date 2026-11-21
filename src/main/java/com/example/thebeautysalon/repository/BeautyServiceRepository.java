package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.BeautyServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeautyServiceRepository extends JpaRepository<BeautyServiceEntity,Long> {
    Boolean existsByNameIgnoreCase(String name);
    List<BeautyServiceEntity> findAllByCategoryId(Long categoryId);
}
