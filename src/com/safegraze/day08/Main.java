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
		
		List<String> segmentOutputValues = new ArrayList<>(rawInputLines.size());
		for (String rawLine : rawInputLines) {
			String[] rawSplitted = rawLine.split("\\s+\\|\\s+");
			String rawOutputValues = rawSplitted[1].trim();
			segmentOutputValues.addAll(Arrays.asList(rawOutputValues.split(" ")));
		}

		List<String> uniqueLengthOutputDigits = segmentOutputValues.stream().filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7).toList();
		System.out.println("Result is: " + uniqueLengthOutputDigits.size());
	}

	
	
}
