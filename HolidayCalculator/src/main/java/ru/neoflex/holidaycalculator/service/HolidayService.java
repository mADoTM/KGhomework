package ru.neoflex.holidaycalculator.service;

import java.math.BigDecimal;

public interface HolidayService {
    BigDecimal calculate(BigDecimal salaryPerYear, int days);
}
