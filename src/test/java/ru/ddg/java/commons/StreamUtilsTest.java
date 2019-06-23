package ru.ddg.java.commons;

import org.junit.Test;
import ru.ddg.java.commons.utils.StreamUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamUtilsTest {
    @Test
    public void filterAndConsume() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);
        Stream.of(1, 2, 3)
                .filter(StreamUtils.filterAndConsume(i -> i == 2, i -> {
                    assertEquals("must be 2", 2, (long) i);
                    counter.incrementAndGet();
                }))
                .filter(StreamUtils.filterAndConsume(i -> i == 2, i -> assertTrue("wrong case", false)))
                .filter(StreamUtils.filterAndConsume(i -> i == 3, i -> {
                    assertEquals("must be 3", 3, (long) i);
                    counter.incrementAndGet();
                }))
                .forEach(integer -> {
                    assertEquals("must be 1", 1, (long) integer);
                    counter.incrementAndGet();
                });
        assertEquals("must be 3", 3, counter.get());

    }

}