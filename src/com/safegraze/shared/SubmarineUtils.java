package com.safegraze.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SubmarineUtils {

    private SubmarineUtils() {
    }

    public static List<String> parseRawInput(Path path) {
        final List<String> values;
        try {
            values = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Cannot continue! Error during read!");
        }

        if (values.isEmpty()) {
            throw new RuntimeException("No data found!");
        }
        return values;
    }
}
