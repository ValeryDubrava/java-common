package ru.ddg.java.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClassPathResourceUtils {

    public static String asString(final String fileName) throws IOException {
        final InputStream stream = asInputStream(fileName);
        return IOUtils.toString(stream, Charset.defaultCharset());
    }

    public static InputStream asInputStream(final String fileName) throws IOException {
        final ClassPathResource resource = new ClassPathResource(fileName);
        return resource.getInputStream();
    }
}
