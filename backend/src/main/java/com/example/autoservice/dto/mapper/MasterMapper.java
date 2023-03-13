package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.MasterRequestDto;
import com.example.autoservice.dto.response.MasterResponseDto;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        return master;
    }

    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto dto = new MasterResponseDto();
        dto.setId(master.getId());
        dto.setName(master.getName());
        dto.setCompletedOrderIds(master.getCompletedOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toSet()));
        return dto;
    }
}
