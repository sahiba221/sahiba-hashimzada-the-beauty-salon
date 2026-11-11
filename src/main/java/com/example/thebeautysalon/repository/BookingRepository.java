package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
