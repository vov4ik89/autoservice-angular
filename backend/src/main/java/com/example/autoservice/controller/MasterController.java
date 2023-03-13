package com.example.autoservice.controller;

import com.example.autoservice.dto.mapper.MasterMapper;
import com.example.autoservice.dto.mapper.OrderMapper;
import com.example.autoservice.dto.request.MasterRequestDto;
import com.example.autoservice.dto.response.MasterResponseDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.model.Master;
import com.example.autoservice.service.MasterService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterMapper masterMapper;
    private final MasterService masterService;
    private final OrderMapper orderMapper;

    public MasterController(MasterMapper masterMapper,
                            MasterService masterService,
                            OrderMapper orderMapper) {
        this.masterMapper = masterMapper;
        this.masterService = masterService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    @ApiOperation("Get all masters")
    public List<MasterResponseDto> getAll() {
        return masterService.getAll()
                .stream()
                .map(masterMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get master by id")
    public MasterResponseDto get(@PathVariable Long id) {
        Master master = masterService.get(id);
        return masterMapper.mapToDto(master);
    }

    @PostMapping
    @ApiOperation("Add a new master")
    public MasterResponseDto create(@RequestBody MasterRequestDto requestDto) {
        Master master = masterMapper.mapToModel(requestDto);
        masterService.add(master);
        return masterMapper.mapToDto(master);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update master by id")
    public MasterResponseDto update(@PathVariable Long id,
                                    @RequestBody MasterRequestDto requestDto) {
        Master master = masterMapper.mapToModel(requestDto);
        master.setId(id);
        masterService.update(master);
        return masterMapper.mapToDto(master);
    }

    @GetMapping("/{id}/orders")
    @ApiOperation("Get orders by master id")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        return masterService.getOrders(id).stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation("Calculate salary by master id")
    public BigDecimal getSalary(@PathVariable Long id) {
        return masterService.getSalary(id);
    }
}
