package ru.ddg.java.commons.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Сервис-обертка для возможности мокать текущее время.
 */
public interface TimeService {
    /**
     * @return обертка над System.currentTimeMillis().
     */
    long getCurrentMillis();

    /**
     * @return current local time.
     */
    LocalDateTime getCurrentLocalTime();

    /**
     * @return utc zone id.
     */
    ZoneId getUtcZoneId();

    /**
     * @return current UTC time.
     */
    LocalDateTime getCurrentUtcTime();

    /**
     * @return current UTC date.
     */
    LocalDate getCurrentUtcDate();

    /**
     * @return current instant.
     */
    Instant getCurrentInstant();

    /**
     * Get current LocalDate for ZoneId
     *
     * @param zoneId    ZoneId
     * @return          LocalDate
     */
    LocalDate getCurrentDateForZoneId(ZoneId zoneId);
}
