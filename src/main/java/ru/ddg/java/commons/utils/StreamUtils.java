package ru.ddg.java.commons.utils;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class StreamUtils {
    public static <T> Predicate<T> filterAndConsume(Predicate<T> condition, Consumer<T> consumer) {
        return condition.negate().or(t -> {
            consumer.accept(t);
            return false;
        });
    }
}
