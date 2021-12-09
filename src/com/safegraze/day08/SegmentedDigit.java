package com.safegraze.day08;

import java.util.EnumSet;
import java.util.Set;


public enum SegmentedDigit {

	ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9");

	private final String label;

	private SegmentedDigit(String label) {
		this.label = label;
	}

	public Set<Segment> getSegments() {
		return getSegmentsFor(this);
	}

	public String getLabel() {
		return label;
	}

	public static SegmentedDigit valueByLabel(String input) {
		for (SegmentedDigit e : values()) {
			if (e.label.equals(input)) {
				return e;
			}
		}
		return null;
	}	

	private static EnumSet<Segment> getSegmentsFor(SegmentedDigit sd) {
		EnumSet<Segment> segments = EnumSet.noneOf(Segment.class);
		switch (sd) {
			case ZERO:
				segments.add(Segment.A);
				segments.add(Segment.B);
				segments.add(Segment.C);
				segments.add(Segment.E);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;
			case ONE:
				segments.add(Segment.C);
				segments.add(Segment.F);
				break;
			case TWO:
				segments.add(Segment.A);
				segments.add(Segment.C);
				segments.add(Segment.D);
				segments.add(Segment.E);
				segments.add(Segment.G);
				break;
			case THREE:
				segments.add(Segment.A);
				segments.add(Segment.C);
				segments.add(Segment.D);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;			
			case FOUR:
				segments.add(Segment.B);
				segments.add(Segment.C);
				segments.add(Segment.D);
				segments.add(Segment.F);
				break;
			case FIVE:
				segments.add(Segment.A);
				segments.add(Segment.B);
				segments.add(Segment.D);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;		
			case SIX:
				segments.add(Segment.A);
				segments.add(Segment.B);
				segments.add(Segment.D);
				segments.add(Segment.E);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;	
			case SEVEN:
				segments.add(Segment.A);
				segments.add(Segment.C);
				segments.add(Segment.F);
				break;	
			case EIGHT:
				segments.add(Segment.A);
				segments.add(Segment.B);
				segments.add(Segment.C);
				segments.add(Segment.D);
				segments.add(Segment.E);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;	
			case NINE:
				segments.add(Segment.A);
				segments.add(Segment.B);
				segments.add(Segment.C);
				segments.add(Segment.D);
				segments.add(Segment.F);
				segments.add(Segment.G);
				break;															
		}

		return segments;
	}
	
}
