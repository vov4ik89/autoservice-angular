package com.example.autoservice.dto.response;

import com.example.autoservice.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDateTime acceptDate;
    private BigDecimal price;
    private LocalDateTime endDate;
    private List<Long> favorIds;
    private List<Long> commodityIds;
    private Order.Status status;
}
