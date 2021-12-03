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

//        int[] rates = logCounter.getRates();
//        System.out.println("Current gamma is: " + rates[0]);
//        System.out.println("Current epsilon is: " + rates[1]);

        int[] rates = logCounter.getLifeSupportRating();
        System.out.println("Current oxygen value is: " + rates[0]);
        System.out.println("Current carbon value is: " + rates[1]);

        long product = (long) rates[0] * (long) rates[1];
        System.out.println("Current product is: " + product);
    }
}
