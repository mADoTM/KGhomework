package ru.neoflex.holidaycalculator.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class HolidayRequest {
    private final BigDecimal salary;
    private final int daysCount;
}
