package ru.ddg.java.commons.services.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ddg.java.commons.services.TimeService;

import java.time.*;
import java.util.Date;

@Component
@Slf4j
public class TimeServiceJava implements TimeService {
    @Getter
    private ZoneId utcZoneId = ZoneOffset.UTC;
    @Getter
    @Value("${ru.ddg.java.fake-time-offset-seconds:0}")
    private long fakeOffset = 0;

    public void addOffsetSeconds(long seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("seconds must be >= 0");
        }
        fakeOffset += seconds;
        log.info("Time moved forward to {} seconds, not it at {}, but real time is {}", seconds, getCurrentUtcTime(), LocalDateTime.now(utcZoneId));
    }

    @Override
    public long getCurrentMillis() {
        return System.currentTimeMillis() + fakeOffset * 1000;
    }

    @Override
    public LocalDateTime getCurrentLocalTime() {
        return LocalDateTime.now().plusSeconds(fakeOffset);
    }

    @Override
    public LocalDateTime getCurrentUtcTime() {
        return LocalDateTime.now(utcZoneId).plusSeconds(fakeOffset);
    }

    @Override
    public LocalDate getCurrentUtcDate() {
        return getCurrentUtcTime().toLocalDate();
    }

    @Override
    public Instant getCurrentInstant() {
        return Instant.now().plusSeconds(fakeOffset);
    }

    @Override
    public LocalDate getCurrentDateForZoneId(ZoneId zoneId) {
        return getCurrentInstant().atZone(zoneId).toLocalDate();
    }
}
