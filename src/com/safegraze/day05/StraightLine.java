package com.safegraze.day05;

import java.util.List;

public class StraightLine implements Line {

    private enum Axis { X, Y };

    private final Axis fixedAxis;
    private final int fixedValue;

    private final int minFlexValueInclusive;
    private final int maxFlexValueInclusive;

    public StraightLine(List<Integer> allCoordinates) {
        this(allCoordinates.get(0), allCoordinates.get(1), allCoordinates.get(2), allCoordinates.get(3));
    }

    public StraightLine(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            fixedAxis = Axis.X;
            fixedValue = x1;
            minFlexValueInclusive = Math.min(y1, y2);
            maxFlexValueInclusive = Math.max(y1, y2);
        } else if (y1 == y2) {
            fixedAxis = Axis.Y;
            fixedValue = y1;
            minFlexValueInclusive = Math.min(x1, x2);
            maxFlexValueInclusive = Math.max(x1, x2);
        } else {
            throw new IllegalArgumentException("Line was not measurable!");
        }
    }

    @Override
    public int[][] generateLineIn2DSpace(int arraySize) {

        int[][] matrix = new int[arraySize][arraySize];

        if (fixedAxis == Axis.X) {
            for (int i = minFlexValueInclusive; i <= maxFlexValueInclusive; i++) {
                matrix[fixedValue][i]++;
            }
        } else {
            for (int i = minFlexValueInclusive; i <= maxFlexValueInclusive; i++) {
                matrix[i][fixedValue]++;
            }
        }

        return matrix;
    }

}
