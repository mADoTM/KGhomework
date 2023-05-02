package ru.neoflex.holidaycalculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.holidaycalculator.dto.HolidayRequest;
import ru.neoflex.holidaycalculator.dto.HolidayResponse;
import ru.neoflex.holidaycalculator.service.HolidayService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("/calculate")
    public ResponseEntity<HolidayResponse> calculateHolidaySalary(
            @RequestBody HolidayRequest holidayRequest) {
        log.info("Received a message - " + holidayRequest);

        try {
            final var money = holidayService.calculate(
                    holidayRequest.getSalary(),
                    holidayRequest.getDaysCount());

            return ResponseEntity
                    .ok(new HolidayResponse(money));
        } catch (IllegalArgumentException e) {
            log.warn("Got an error during computing holidays money");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
