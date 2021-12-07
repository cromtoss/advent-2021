package com.safegraze.day07;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.safegraze.shared.SubmarineUtils;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day07-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        
        if (rawInputLines.size() != 1) {
            throw new RuntimeException("Expected one line of input!");
        }

        String[] crabValues = rawInputLines.get(0).split(",");
        int[] crabPositions = Arrays.stream(crabValues).mapToInt(Integer::parseInt).toArray();

		// initialize array indicating count of crabs at each index
		int maxPosition = Arrays.stream(crabPositions).max().getAsInt();
		int[] crabsAtIndex = new int[maxPosition+1];
		Arrays.stream(crabPositions).forEach(i -> crabsAtIndex[i]++);

		// determine the cost at each index by multiplying the number of crabs at each position
		// by the cost to move from that position to costPerIndex's index value
		int[] costPerIndex = new int[maxPosition+1];
		for (int i = 0; i < maxPosition + 1; i++) {
			final int index = i;
			costPerIndex[i] = IntStream.range(0, costPerIndex.length).map(j -> crabsAtIndex[j] * Math.abs(j - index)).reduce(0, Integer::sum);
		}

		int minCost = Arrays.stream(costPerIndex).min().getAsInt();
		System.out.println("Answer is: " + minCost);

    }
	
}
