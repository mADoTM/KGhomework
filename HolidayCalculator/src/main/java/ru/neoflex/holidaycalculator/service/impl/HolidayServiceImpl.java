package ru.neoflex.holidaycalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.holidaycalculator.service.HolidayService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class HolidayServiceImpl implements HolidayService {
    private final int MONTH_DAYS_COUNT = 30;

    /**
     * Calculate salary for holiday.
     * @param salaryPerYear - Average salary per 12 months
     * @param days - Count of holiday days
     * @return salaryPerYear / Month (30 days) * days
     */
    @Override
    public BigDecimal calculate(BigDecimal salaryPerYear, int days) {
        log.info("Computing holiday cash");
        if (days < 0 || salaryPerYear.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        if (days == 0) {
            return new BigDecimal(0);
        }

        final var salaryPerDay = salaryPerYear.divide(
                BigDecimal.valueOf(MONTH_DAYS_COUNT),
                RoundingMode.HALF_UP);

        return salaryPerDay.multiply(BigDecimal.valueOf(days));
    }
}
