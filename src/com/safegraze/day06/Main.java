package com.safegraze.day06;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.safegraze.shared.SubmarineUtils;

public class Main {

    private static final short NUM_ITERATIONS = 256;

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day06-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        
        if (rawInputLines.size() != 1) {
            throw new RuntimeException("Expected one line of input!");
        }

        String[] fishTimerValues = rawInputLines.get(0).split(",");
        List<Short> fishTimersNumeric = Arrays.stream(fishTimerValues).map(Short::parseShort).toList();

        LanternfishSchool school = new LanternfishSchool(fishTimersNumeric);

        short iterations = NUM_ITERATIONS;
        while (iterations > 1) {
            school.advanceDay();
            iterations--;
        }
    
        System.out.println("Number of fish is: " + school.getNumberOfFish());
    }
}
