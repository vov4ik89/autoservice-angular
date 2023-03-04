package com.example.autoservice.dto.request;

import com.example.autoservice.model.Favor;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class FavorRequestDto {
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private Favor.Status status;
}
