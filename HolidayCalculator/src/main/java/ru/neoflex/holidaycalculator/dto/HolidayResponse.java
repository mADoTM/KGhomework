package ru.neoflex.holidaycalculator.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class HolidayResponse {
    private final BigDecimal money;
}
