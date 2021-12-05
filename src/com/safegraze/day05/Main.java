package com.safegraze.day05;

import com.safegraze.shared.SubmarineUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    private static final int MATRIX_DIM_SIZE = 1000;

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day05-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        List<Line> measurableLines = LineReader.make(rawInputLines);

        D2Space space = new D2Space(MATRIX_DIM_SIZE);
        for (Line l : measurableLines) {
            space.addLineSpace(l.generateLineIn2DSpace(MATRIX_DIM_SIZE));
        }

        System.out.println("Number of interesting spaces is: " + space.getNumberInterestingSpaces());
    }
}
