package ru.ddg.java.commons.services;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @author Max
 */
public interface CsvSevice {
    /**
     * Write line
     *
     * @param writer        writer
     * @param values        list of Strings
     * @throws IOException
     */
    void writeLine(Writer writer, List<String> values) throws IOException;

    /**
     * Write line
     *
     * @param writer        writer
     * @param values        list of Strings
     * @param separator     custom separator
     * @throws IOException
     */
    void writeLine(Writer writer, List<String> values, char separator) throws IOException;
}
