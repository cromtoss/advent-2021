package com.safegraze.day05;

import java.util.List;

public class LineUtils {

    public static boolean isStraightLine(int x1, int y1, int x2, int y2) {
        return ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2));
    }

    public static boolean isStraightLine(List<Integer> input) {
        return isStraightLine(input.get(0), input.get(1), input.get(2), input.get(3));
    }

    public static boolean isLine(int x1, int x2, int y1, int y2) {
        return (x1 != x2 && y1 != y2);
    }

    public static boolean isLine(List<Integer> input) {
        return isLine(input.get(0), input.get(1), input.get(2), input.get(3));
    }

}
