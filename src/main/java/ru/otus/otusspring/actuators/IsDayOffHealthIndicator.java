package ru.otus.otusspring.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.otusspring.exception.CheckDayException;
import ru.otus.otusspring.service.IsDayOffService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class IsDayOffHealthIndicator implements HealthIndicator {

    private final IsDayOffService service;

    @Override
    public Health health() {
        boolean isDayOff;
        try {
            isDayOff = service.isDayOff(getDateAsString());
        }
        catch (CheckDayException e) {
            return Health.outOfService()
                    .withDetail("message", e.getMessage())
                    .build();
        }

        if (isDayOff) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Library is closed, day off!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Welcome!").build();
        }
    }

    private String getDateAsString() {

        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
