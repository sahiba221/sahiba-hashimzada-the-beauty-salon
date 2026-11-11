package com.example.thebeautysalon.repository;

import com.example.thebeautysalon.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
