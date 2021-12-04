package com.safegraze.day03;

import com.safegraze.shared.SubmarineUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day03-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        BinaryLogCounter logCounter = BinaryLogCounter.make(rawInputLines);

        int[] gammaEpsilon = logCounter.getGammaEpsilon();
        System.out.println("Current gamma is: " + gammaEpsilon[0]);
        System.out.println("Current epsilon is: " + gammaEpsilon[1]);

        long gammaProduct = (long) gammaEpsilon[0] * (long) gammaEpsilon[1];
        System.out.println("Current gamma-epsilon product is: " + gammaProduct);

        int[] rates = logCounter.getLifeSupportRating();
        System.out.println("Current oxygen value is: " + rates[0]);
        System.out.println("Current carbon value is: " + rates[1]);

        long product = (long) rates[0] * (long) rates[1];
        System.out.println("Current oxygen-carbon product is: " + product);
    }
}
