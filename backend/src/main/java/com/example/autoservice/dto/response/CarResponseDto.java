package com.example.autoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private Long ownerId;
    private String brand;
    private String model;
    private int year;
    private String number;
}
