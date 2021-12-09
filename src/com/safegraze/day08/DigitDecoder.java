package com.safegraze.day08;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DigitDecoder {

	private static final String ALL_CHARS = "abcdefg";

	public List<String> inputs;
	public List<String> toDecode;
	private Map<Segment, Segment> decoderMap;

	public DigitDecoder(List<String> inputs, List<String> toDecode) {
		this.inputs = inputs;
		this.toDecode = toDecode;
		decoderMap = buildDecoderMap(inputs);
	}

	public String decode() {
		StringBuilder output = new StringBuilder();

		//for each value to decode
		for (String encoded : toDecode) {

			//convert each encoded value's characters to a segment, then decode that segment
			Set<Segment> decodedSegments = EnumSet.noneOf(Segment.class);
			for (char c : encoded.toCharArray()) {
				Segment codedSegment = Segment.valueByLabel(String.valueOf(c));
				decodedSegments.add(decoderMap.get(codedSegment));
			}

			//get the segmented digit associated with these decoded segments, and append it to the string
			for (SegmentedDigit digit : SegmentedDigit.values()) {
				if (digit.getSegments().equals(decodedSegments)) {
					output.append(digit.getLabel());
				}
			}			
		}

		return output.toString();
	}

	private static Map<Segment, Segment> buildDecoderMap(List<String> inputs) {

		final HashMap<Segment, Segment> decoderMap = new HashMap<>(7);

		List<String> sixLetterScrambles = inputs.stream().filter(s -> s.length() == 6).toList();
		List<String> fiveLetterScrambles = inputs.stream().filter(s -> s.length() == 5).toList();
		String fourLetterScramble = inputs.stream().filter(s -> s.length() == 4).toList().get(0);
		String threeLetterScramble = inputs.stream().filter(s -> s.length() == 3).toList().get(0);
		String twoLetterScramble = inputs.stream().filter(s -> s.length() == 2).toList().get(0);

		String a = stripFrom(threeLetterScramble, twoLetterScramble);
		String bAndD = stripFrom(fourLetterScramble, twoLetterScramble);
		String cAndF = twoLetterScramble;
		String eAndG = stripFrom(ALL_CHARS, bAndD.concat(cAndF).concat(a));

		String commonLetters = commonLetters(fiveLetterScrambles.get(0), fiveLetterScrambles.get(1), fiveLetterScrambles.get(2));
		String dAndG = stripFrom(commonLetters, a);
		String d = retain(bAndD, dAndG);
		String b = stripFrom(bAndD, d);
		String g = stripFrom(dAndG, d);
		String e = stripFrom(eAndG, g);

		String f = appearsMostOftenIn(String.valueOf(cAndF.charAt(0)), String.valueOf(cAndF.charAt(1)), sixLetterScrambles);
		String c = stripFrom(cAndF, f);

		decoderMap.put(Segment.valueByLabel(a), Segment.A);
		decoderMap.put(Segment.valueByLabel(b), Segment.B);
		decoderMap.put(Segment.valueByLabel(c), Segment.C);
		decoderMap.put(Segment.valueByLabel(d), Segment.D);
		decoderMap.put(Segment.valueByLabel(e), Segment.E);
		decoderMap.put(Segment.valueByLabel(f), Segment.F);
		decoderMap.put(Segment.valueByLabel(g), Segment.G);
		
		return decoderMap;
	}

	private static String commonLetters(String one, String two, String three) {
		StringBuilder builder = new StringBuilder();

		for (Character c : one.toCharArray()) {
			if (two.contains(String.valueOf(c)) && three.contains(String.valueOf(c))) {
				builder.append(c);
			}
		}

		return builder.toString();
	}

	private static String stripFrom(String toStrip, String stripValues) {
		StringBuilder builder = new StringBuilder();
		
		for (Character c : toStrip.toCharArray()) {
			if (!stripValues.contains(String.valueOf(c))) {
				builder.append(c);
			}
		}

		return builder.toString();
	}

	private static String retain(String toStrip, String retainValues) {
		StringBuilder builder = new StringBuilder();
		
		for (Character c : toStrip.toCharArray()) {
			if (retainValues.contains(String.valueOf(c))) {
				builder.append(c);
			}
		}

		return builder.toString();		
	}

	/*
	 * WARNING: an ugly implementation very specific to this problem; doesn't do quite what it claims!
	 * In reality this is all about determining which of the two provided letters appears more often in the stringsToCheck
	 */
	private static String appearsMostOftenIn(String letterOne, String letterTwo, List<String> stringsToCheck) {
		int oneCount = 0;
		int twoCount = 0;

		for (String word : stringsToCheck) {
			for (Character c : word.toCharArray()) {
				if (letterOne.equals(String.valueOf(c))) {
					oneCount++;
				} else if (letterTwo.equals(String.valueOf(c))) {
					twoCount++;
				}
			}
		}

		return (oneCount > twoCount) ? letterOne : letterTwo;
	}

	

	

	
}
