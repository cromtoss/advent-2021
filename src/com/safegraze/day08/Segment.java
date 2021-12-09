package com.safegraze.day08;

public enum Segment {
	A("a"), B("b"), C("c"), D("d"), E("e"), F("f"), G("g");

	private final String label;

	private Segment(String label) {
		this.label = label;
	}

	public static Segment valueByLabel(String input) {
		for (Segment e : values()) {
			if (e.label.equals(input)) {
				return e;
			}
		}
		return null;
	}	
}
