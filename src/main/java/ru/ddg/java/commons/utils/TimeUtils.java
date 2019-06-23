package ru.ddg.java.commons.utils;

import java.time.*;
import java.util.*;

/**
 * Time utilities for time measures conversion. Can convert time to text and vice versa.
 */
public class TimeUtils {
	public static LocalDateTime convertDateToLocalDateTime(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertLocalDateTimeAndTimeZoneToDate(final LocalDateTime localDateTime, final ZoneId zoneId) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}
