package ru.neoflex.holidaycalculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class HolidayServiceTest {
    @Autowired
    private HolidayService service;

    @Test
    public void shouldThrowExceptionWhenDaysLessThan0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           service.calculate(new BigDecimal(0), -1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(new BigDecimal(0), -2);
        });
    }

    @Test
    public void shouldThrowExceptionWhenSalaryLessThan0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(new BigDecimal(-1), 5);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(new BigDecimal(-195195), 3);
        });
    }

    @Test
    public void shouldGet0WhenDaysEquals0() {
        Assertions.assertEquals(BigDecimal.ZERO, service.calculate(new BigDecimal(1203), 0));
        Assertions.assertEquals(BigDecimal.ZERO, service.calculate(new BigDecimal(1201233), 0));
        Assertions.assertEquals(BigDecimal.ZERO, service.calculate(new BigDecimal(45689), 0));
    }

    @Test
    public void computeDefaultSalary() {
        BigDecimal salary = new BigDecimal(12000);
        int holidayDays = 10;

        BigDecimal expected = BigDecimal.valueOf(4000);
        Assertions.assertEquals(expected, service.calculate(salary, holidayDays));
    }
}