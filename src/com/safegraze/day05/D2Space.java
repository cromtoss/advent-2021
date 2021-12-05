package com.safegraze.day05;

import java.util.Arrays;

public class D2Space {

    private final int[][] pointSpace;
    private int dimensionSize;

    public D2Space(int dimensionSize) {
        this.dimensionSize = dimensionSize;
        pointSpace = new int[dimensionSize][dimensionSize];
    }

    public int incrementSpace(int x, int y) {
        int currentValue = pointSpace[x][y];
        pointSpace[x][y] = currentValue + 1;
        return pointSpace[x][y];
    }

    public void addLineSpace(int[][] lineSpace) {
        Arrays.setAll(pointSpace, i -> {
            Arrays.setAll(pointSpace[i], j -> lineSpace[i][j] + pointSpace[i][j]);
            return pointSpace[i];
        });
    }

    public int getNumberInterestingSpaces() {
        int numInterestingSpaces = 0;
        for(int i = 0; i < dimensionSize; i++) {
            for(int j = 0; j < dimensionSize; j++) {
                if (pointSpace[i][j] > 1) {
                    numInterestingSpaces++;
                }
            }
        }

        return numInterestingSpaces;
    }

}
