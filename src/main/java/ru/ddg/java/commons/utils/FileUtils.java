package ru.ddg.java.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {
    public static String storeWithRandomName(final InputStreamSource source, final String folder, final String ext) throws IOException {
        return storeWithRandomName(source.getInputStream(), folder, ext);
    }

    public static String storeWithRandomName(final InputStream inputStream, final String folder, final String ext) throws IOException {
        while (true) {
            final String storedName = UUID.randomUUID() + "." + ext;
            final Path destination = Paths.get(folder, storedName);
            if (Files.notExists(destination)) {
                copyInputStreamToFile(inputStream, Files.createFile(destination).toFile());
                return storedName;
            }
        }
    }
}
