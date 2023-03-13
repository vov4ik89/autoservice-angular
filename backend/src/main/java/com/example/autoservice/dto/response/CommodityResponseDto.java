package com.example.autoservice.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
