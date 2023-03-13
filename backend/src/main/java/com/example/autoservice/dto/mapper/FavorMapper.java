package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.request.FavorRequestDto;
import com.example.autoservice.dto.response.FavorResponseDto;
import com.example.autoservice.model.Favor;
import com.example.autoservice.repository.MasterRepository;
import com.example.autoservice.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class FavorMapper {
    private final MasterRepository masterRepository;
    private final OrderRepository orderRepository;

    public FavorMapper(MasterRepository masterRepository,
                       OrderRepository orderRepository) {
        this.masterRepository = masterRepository;
        this.orderRepository = orderRepository;
    }

    public Favor mapToModel(FavorRequestDto dto) {
        Favor favor = new Favor();
        favor.setMaster(masterRepository.getReferenceById(dto.getMasterId()));
        favor.setOrder(orderRepository.getReferenceById(dto.getOrderId()));
        favor.setPrice(dto.getPrice());
        favor.setStatus(dto.getStatus());
        return favor;
    }

    public FavorResponseDto mapToDto(Favor favor) {
        FavorResponseDto dto = new FavorResponseDto();
        dto.setId(favor.getId());
        dto.setMasterId(favor.getMaster().getId());
        dto.setOrderId(favor.getOrder().getId());
        dto.setPrice(favor.getPrice());
        dto.setStatus(favor.getStatus());
        return dto;
    }
}
