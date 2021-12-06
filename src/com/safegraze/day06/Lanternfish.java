package com.safegraze.day06;

public class Lanternfish {

	private short timerToReproduce = LanternfishUtils.TIMER_FOR_BIRTHED_FISH;
	private int numTimesReproduced = 0;

	public Lanternfish() {}

	public Lanternfish(short timerOverride) {
		this.timerToReproduce = (short) timerOverride;
	}

	public short getTimerValue() {
		return timerToReproduce;
	}

	public int getTimesReproduced() {
		return numTimesReproduced;
	}

	public boolean isTimeToReproduce() {
		return (timerToReproduce == 0);
	}

	public void advanceDay() {
		if (timerToReproduce == 0) {
			timerToReproduce = LanternfishUtils.TIMER_AFTER_REPRODUCTION;
			numTimesReproduced++;
		} else {
			timerToReproduce--;
		}
	}
	
}
