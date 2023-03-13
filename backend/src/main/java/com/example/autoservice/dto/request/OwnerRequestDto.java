package com.example.autoservice.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class OwnerRequestDto {
    private List<Long> carIds;
    private List<Long> orderIds;
}
