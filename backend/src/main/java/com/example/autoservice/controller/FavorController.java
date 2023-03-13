package com.example.autoservice.controller;

import com.example.autoservice.dto.mapper.FavorMapper;
import com.example.autoservice.dto.request.FavorRequestDto;
import com.example.autoservice.dto.response.FavorResponseDto;
import com.example.autoservice.model.Favor;
import com.example.autoservice.service.FavorService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maintenances")
public class FavorController {
    private final FavorService favorService;
    private final FavorMapper favorMapper;

    public FavorController(FavorService favorService, FavorMapper favorMapper) {
        this.favorService = favorService;
        this.favorMapper = favorMapper;
    }

    @GetMapping
    @ApiOperation("Get all favors")
    public List<FavorResponseDto> getAll() {
        return favorService.getAll()
                .stream()
                .map(favorMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get favor by id")
    public FavorResponseDto get(@PathVariable Long id) {
        Favor favor = favorService.get(id);
        return favorMapper.mapToDto(favor);
    }

    @PostMapping
    @ApiOperation("Add a new favor")
    public FavorResponseDto create(@RequestBody FavorRequestDto requestDto) {
        Favor favor = favorMapper.mapToModel(requestDto);
        favorService.add(favor);
        return favorMapper.mapToDto(favor);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update favor by id")
    public FavorResponseDto update(@PathVariable Long id,
                                   @RequestBody FavorRequestDto requestDto) {
        Favor favor = favorMapper.mapToModel(requestDto);
        favor.setId(id);
        favorService.update(favor);
        return favorMapper.mapToDto(favor);
    }

    @GetMapping("/{id}/status")
    @ApiOperation("Update status by favor id")
    public FavorResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody FavorRequestDto requestDto) {
        Favor status = favorMapper.mapToModel(requestDto);
        favorService.updateStatus(id, status.getStatus());
        return favorMapper.mapToDto(status);
    }
}
