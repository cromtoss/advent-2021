package com.safegraze.day05;

import java.util.List;

public class DiagonalLine implements Line {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public DiagonalLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public DiagonalLine(List<Integer> points) {
        this(points.get(0), points.get(1), points.get(2), points.get(3));
    }

    @Override
    public int[][] generateLineIn2DSpace(int arraySize) {
        int[][] matrix = new int[arraySize][arraySize];

        // matrix[x2][y2] covered by while loop code
        matrix[x1][y1]++;

        int walkingX1 = x1;
        int walkingY1 = y1;

        while (walkingX1 != x2 && walkingY1 != y2) {
            if (walkingX1 != x2) {
                if (walkingX1 > x2) {
                    walkingX1--;
                } else {
                    walkingX1++;
                }
            }
            if (walkingY1 != y2) {
                if (walkingY1 > y2) {
                    walkingY1--;
                } else {
                    walkingY1++;
                }
            }

            matrix[walkingX1][walkingY1]++;
        }

        return matrix;
    }

}
