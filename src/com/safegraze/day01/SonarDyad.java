package com.safegraze.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SonarDyad {

    private final int currentReading;
    private final int nextReading;

    public SonarDyad(int currentReading, int nextReading) {
        this.currentReading = currentReading;
        this.nextReading = nextReading;
    }

    public boolean isDeeper() {
        return (nextReading > currentReading);
    }

    public static List<SonarDyad> sonarDyads(Path path) {
        List<String> values = null;
        try {
            values = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Cannot continue!");
        }

        List<SonarDyad> dyads = new ArrayList<>(values.size());
        for (int i = 0; i < values.size() - 1; i++) {
            final int currentValue = Integer.parseInt(values.get(i));
            final int nextValue = Integer.parseInt(values.get(i+1));
            dyads.add(new SonarDyad(currentValue, nextValue));
        }

        return dyads;
    }
}
