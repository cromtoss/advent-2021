package com.safegraze.day02;

import com.safegraze.shared.SubmarineUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day02-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        LocationManager manager = LocationManager.make(rawInputLines);

        System.out.println("Current distance is: " + manager.getCurrentHorizontalDistance());
        System.out.println("Current depth is: " + manager.getCurrentDepth());

        long product = (long) manager.getCurrentHorizontalDistance() * (long) manager.getCurrentDepth();
        System.out.println("Current product is: " + product);
    }

}
