package com.safegraze.day08;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.safegraze.shared.SubmarineUtils;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day08-input.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
		
		List<String> decodedValues = new ArrayList<>(rawInputLines.size());
		for (String rawLine : rawInputLines) {
			String[] rawSplitted = rawLine.split("\\s+\\|\\s+");

			List<String> observedInputs = Arrays.asList(rawSplitted[0].trim().split(" "));
			List<String> stringsToDecode = Arrays.asList(rawSplitted[1].trim().split(" "));
			DigitDecoder lineDecoder = new DigitDecoder(observedInputs, stringsToDecode);
			decodedValues.add(lineDecoder.decode());
		}

		long sum = decodedValues.stream().map(Integer::parseInt).reduce(0, Integer::sum);

		System.out.println("Sum of lines decoded: " + sum);

	}

	
	
}
