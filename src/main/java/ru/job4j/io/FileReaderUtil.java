package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class FileReaderUtil {
    private final File file;

    public FileReaderUtil(File file) {
        this.file = new File(file.getPath());
    }

    String getContent(Predicate<Character> filter) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = reader.read()) != -1) {
                char character = (char) data;
                if (filter.test(character)) {
                    output.append(character);
                }
            }
            return output.toString();
        }
    }

    public String getContent() throws IOException {
        return getContent(c -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent(c -> c < 0x80);
    }
}
