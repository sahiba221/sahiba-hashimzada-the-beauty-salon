package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.WorkScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkScheduleEntity, Long> {
}
