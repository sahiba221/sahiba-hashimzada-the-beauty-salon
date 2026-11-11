package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.BeautyServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyServiceRepository extends JpaRepository<BeautyServiceEntity,Long> {
}
