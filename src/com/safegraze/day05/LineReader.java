package com.safegraze.day05;

import java.util.ArrayList;
import java.util.List;

public class LineReader {

    public static List<Line> make(List<String> rawInput) {

        List<Line> validLines = new ArrayList<>(rawInput.size());

        for (String input : rawInput) {
            List<Integer> allCoordinates = new ArrayList<>(4);
            String[] points = input.split(" -> ");
            for (String point : points) {
                String[] coords = point.trim().split(",");
                for (String coord: coords) {
                    allCoordinates.add(Integer.parseInt(coord));
                }
            }

            if (LineUtils.isStraightLine(allCoordinates)) {
                validLines.add(new StraightLine(allCoordinates));
            } else if (LineUtils.isLine(allCoordinates)) {
                validLines.add(new DiagonalLine(allCoordinates));
            }
        }

        return validLines;
    }
}
