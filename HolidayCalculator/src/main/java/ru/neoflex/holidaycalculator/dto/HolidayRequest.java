package ru.neoflex.holidaycalculator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HolidayRequest {
    private final BigDecimal salary;
    private final int daysCount;
}
