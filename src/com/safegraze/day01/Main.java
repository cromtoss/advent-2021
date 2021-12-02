package com.safegraze.day01;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //open file
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day01-input.txt");
        List<SonarSlice> slices = SonarSlice.makeSlices(path);
        long result = slices.parallelStream().filter(SonarSlice::isDeeper).count();

        System.out.println("The result is: " + result);
    }
}
