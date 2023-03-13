package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.CommodityRequestDto;
import com.example.autoservice.dto.response.CommodityResponseDto;
import com.example.autoservice.model.Commodity;
import org.springframework.stereotype.Component;

@Component
public class CommodityMapper {
    public Commodity mapToModel(CommodityRequestDto dto) {
        Commodity commodity = new Commodity();
        commodity.setName(dto.getName());
        commodity.setPrice(dto.getPrice());
        return commodity;
    }

    public CommodityResponseDto mapToDto(Commodity commodity) {
        CommodityResponseDto dto = new CommodityResponseDto();
        dto.setId(commodity.getId());
        dto.setName(commodity.getName());
        dto.setPrice(commodity.getPrice());
        return dto;
    }
}
