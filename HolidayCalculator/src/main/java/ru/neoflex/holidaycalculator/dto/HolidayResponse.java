package ru.neoflex.holidaycalculator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HolidayResponse {
    private final BigDecimal money;
}
