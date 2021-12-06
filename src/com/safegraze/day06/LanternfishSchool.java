package com.safegraze.day06;

import java.util.Arrays;
import java.util.List;

public class LanternfishSchool {

	final long[] numericSolution = new long[LanternfishUtils.TIMER_FOR_BIRTHED_FISH+1];

	public LanternfishSchool(List<Short> initialFishByTimer) {
		initialFishByTimer.stream().forEach(s -> numericSolution[s-1]++);
	}

	public void advanceDay() {
		//count number of fish reproducing today
		long numReproducing = numericSolution[0];

		//advance day for all existing fish
		for (int i = 1; i < numericSolution.length; i++) {
			numericSolution[i-1] = numericSolution[i];
		}

		//for number of fish that reproduced today, add them to the fish with the appropriate reproduction timer index
		numericSolution[LanternfishUtils.TIMER_AFTER_REPRODUCTION] += numReproducing;

		//all newly born fish are entered in the appropriate index
		numericSolution[LanternfishUtils.TIMER_FOR_BIRTHED_FISH] = numReproducing;
	}

	public long getNumberOfFish() {
		return Arrays.stream(numericSolution).sum();
	}
	
}
