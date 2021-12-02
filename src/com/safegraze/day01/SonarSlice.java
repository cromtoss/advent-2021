package com.safegraze.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SonarSlice {

    private final int currentReading;
    private final int secondReading;
    private final int thirdReading;
    private final int fourthReading;

    public SonarSlice(int currentReading, int secondReading, int thirdReading, int fourthReading) {
        this.currentReading = currentReading;
        this.secondReading = secondReading;
        this.thirdReading = thirdReading;
        this.fourthReading = fourthReading;
    }

    private int currentSliceCalculation() {
        return currentReading + secondReading + thirdReading;
    }

    private int nextSliceCalculation() {
        return secondReading + thirdReading + fourthReading;
    }

    public boolean isDeeper() {
        return (nextSliceCalculation() > currentSliceCalculation());
    }

    public static List<SonarSlice> makeSlices(Path path) {
        List<String> values = null;
        try {
            values = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Cannot continue!");
        }

        // wasn't obvious how to coax this into streams, and certainly no obvious performance benefit [O(n)]
        List<SonarSlice> slices = new ArrayList<>(values.size());
        for (int i = 0; i < values.size() - 3; i++) {
            final int currentValue = Integer.parseInt(values.get(i));
            final int nextValue = Integer.parseInt(values.get(i+1));
            final int thirdValue = Integer.parseInt(values.get(i+2));
            final int fourthValue = Integer.parseInt(values.get(i+3));
            slices.add(new SonarSlice(currentValue, nextValue, thirdValue, fourthValue));
        }

        return slices;
    }
}
