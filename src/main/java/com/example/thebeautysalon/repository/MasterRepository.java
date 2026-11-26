package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
    List<MasterEntity> findAllByCategoryId(Long categoryId);
    Boolean existsByUserId(Long userId);
}
