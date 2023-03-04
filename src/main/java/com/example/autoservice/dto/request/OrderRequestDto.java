package com.example.autoservice.dto.request;

import com.example.autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long carId;
    private String description;
    private BigDecimal price;
    private List<Long> commodityIds;
    private Order.Status status;
}
