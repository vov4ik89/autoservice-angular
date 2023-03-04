package com.example.autoservice.dto.response;

import com.example.autoservice.model.Favor;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private Favor.Status status;
}
