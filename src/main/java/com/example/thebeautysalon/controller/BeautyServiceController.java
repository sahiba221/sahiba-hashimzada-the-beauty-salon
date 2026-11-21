package com.example.thebeautysalon.controller;

import com.example.thebeautysalon.dto.request.BeautyServiceRequestDto;
import com.example.thebeautysalon.dto.response.BeautyServiceResponseDto;
import com.example.thebeautysalon.service.BeautyServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beauty-services")
@RequiredArgsConstructor
public class BeautyServiceController {

    private final BeautyServiceService beautyServiceService;

    @GetMapping
    public List<BeautyServiceResponseDto> getAll() {
        return beautyServiceService.getAllServices();
    }

    @GetMapping("/{id}")
    public BeautyServiceResponseDto getById(@PathVariable Long id) {
        return beautyServiceService.findByIdBeautyService(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<BeautyServiceResponseDto> getAllByCategoryId(@PathVariable Long categoryId) {
        return beautyServiceService.getAllByCategoryId(categoryId);
    }

    @PostMapping
    public BeautyServiceResponseDto create(@Valid @RequestBody BeautyServiceRequestDto beautyServiceRequestDto) {
        return beautyServiceService.create(beautyServiceRequestDto);
    }

    @PutMapping("/{id}")
    public BeautyServiceResponseDto updateById(@PathVariable Long id,
                                               @Valid @RequestBody BeautyServiceRequestDto beautyServiceRequestDto) {
        return beautyServiceService.updateById(id, beautyServiceRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        beautyServiceService.deleteById(id);
    }
}