package com.example.autoservice.controller;

import com.example.autoservice.dto.mapper.OrderMapper;
import com.example.autoservice.dto.mapper.OwnerMapper;
import com.example.autoservice.dto.request.OwnerRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.dto.response.OwnerResponseDto;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final OrderMapper orderMapper;

    public OwnerController(OwnerService ownerService,
                           OwnerMapper ownerMapper,
                           OrderMapper orderMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    @ApiOperation("Get all owners")
    public List<OwnerResponseDto> getAll() {
        return ownerService.getAll()
                .stream()
                .map(ownerMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get owner by id")
    public OwnerResponseDto get(@PathVariable Long id) {
        Owner owner = ownerService.get(id);
        return ownerMapper.mapToDto(owner);
    }

    @PostMapping
    @ApiOperation("Add a new owner")
    public OwnerResponseDto create(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerMapper.mapToModel(requestDto);
        ownerService.add(owner);
        return ownerMapper.mapToDto(owner);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update owner by id")
    public OwnerResponseDto update(@PathVariable Long id,
                                   @RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerMapper.mapToModel(requestDto);
        owner.setId(id);
        ownerService.update(owner);
        return ownerMapper.mapToDto(owner);
    }

    @PostMapping("/{id}/orders")
    @ApiOperation("Get all orders by owner id")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        return ownerService.getOrders(id)
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
