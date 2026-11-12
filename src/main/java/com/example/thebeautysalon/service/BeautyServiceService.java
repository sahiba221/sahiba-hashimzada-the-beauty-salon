package com.example.thebeautysalon.service;

import com.example.thebeautysalon.repository.BeautyServiceRepository;
import com.example.thebeautysalon.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeautyServiceService {
    private final BeautyServiceRepository beautyServiceRepository;
    private final CategoryRepository categoryRepository;

    
}
