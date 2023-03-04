package com.example.autoservice.controller;

import com.example.autoservice.dto.mapper.CommodityMapper;
import com.example.autoservice.dto.request.CommodityRequestDto;
import com.example.autoservice.dto.response.CommodityResponseDto;
import com.example.autoservice.model.Commodity;
import com.example.autoservice.service.CommodityService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodities")
public class CommodityController {
    private final CommodityService commodityService;
    private final CommodityMapper commodityMapper;

    public CommodityController(CommodityService commodityService, CommodityMapper commodityMapper) {
        this.commodityService = commodityService;
        this.commodityMapper = commodityMapper;
    }

    @GetMapping
    @ApiOperation("Get all commodities")
    public List<CommodityResponseDto> getAll() {
        return commodityService.getAll()
                .stream()
                .map(commodityMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get commodity by id")
    public CommodityResponseDto get(@PathVariable Long id) {
        Commodity commodity = commodityService.get(id);
        return commodityMapper.mapToDto(commodity);
    }

    @PostMapping
    @ApiOperation("Add a new commodity")
    public CommodityResponseDto create(@RequestBody CommodityRequestDto requestDto) {
        Commodity commodity = commodityMapper.mapToModel(requestDto);
        commodityService.add(commodity);
        return commodityMapper.mapToDto(commodity);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update commodity by id")
    public CommodityResponseDto update(@PathVariable Long id,
                                       @RequestBody CommodityRequestDto requestDto) {
        Commodity commodity = commodityMapper.mapToModel(requestDto);
        commodity.setId(id);
        commodityService.update(commodity);
        return commodityMapper.mapToDto(commodity);
    }
}
