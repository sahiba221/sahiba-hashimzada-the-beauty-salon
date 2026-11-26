package com.example.thebeautysalon.controller;

import com.example.thebeautysalon.dto.request.MasterRequestDto;
import com.example.thebeautysalon.dto.response.MasterResponseDto;
import com.example.thebeautysalon.service.MasterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/masters")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping
    public List<MasterResponseDto> findAll(){
        return masterService.findAll();
    }

    @GetMapping("/{id}")
    public MasterResponseDto findById(@PathVariable Long id){
        return masterService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MasterResponseDto create(@Valid @RequestBody MasterRequestDto masterRequestDto){
        return masterService.create(masterRequestDto);
    }

    @PutMapping("/{id}")
    public MasterResponseDto updateById(@PathVariable Long id, @Valid @RequestBody MasterRequestDto masterRequestDto){
        return masterService.updateById(id, masterRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        masterService.deleteById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<MasterResponseDto> getAllByCategoryId(@PathVariable Long categoryId){
        return masterService.getAllByCategoryId(categoryId);
    }
}