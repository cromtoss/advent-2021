package com.safegraze.day06;

import java.util.ArrayList;
import java.util.List;

public class LanternfishSchool {

	final List<Lanternfish> school;

	public LanternfishSchool(List<Short> initialFishByTimer) {

		school = new ArrayList<>(initialFishByTimer.size()^2);
		initialFishByTimer.stream().map(Lanternfish::new).forEach(school::add);
	}

	public void advanceDay() {
		//count number of fish reproducing today
		long numReproducing = school.stream().filter(f -> f.isTimeToReproduce()).count();

		//advance day for all existing fish
		school.forEach(Lanternfish::advanceDay);

		while (numReproducing > 0) {
			school.add(new Lanternfish());
			numReproducing--;
		}
	}

	public int getNumberOfFish() {
		return school.size();
	}
	
}
