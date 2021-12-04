package com.safegraze.day03;

import java.util.HashMap;
import java.util.List;

public class BinaryLogCounter {

    private static final String OXYGEN_MAP_KEY = "oxygen";
    private static final String CARBON_MAP_KEY = "carbon";

    private final HashMap<String, List<String>> lifeSupportMap = new HashMap<>(2);

    private final long[] countOfZeroes;
    private final long[] countOfOnes;

    private BinaryLogCounter(List<String> rawInput, int size) {
        countOfZeroes = new long[size];
        countOfOnes = new long[size];

        lifeSupportMap.put(OXYGEN_MAP_KEY, rawInput);
        lifeSupportMap.put(CARBON_MAP_KEY, rawInput);
    }

    public static BinaryLogCounter make(List<String> rawBinaryLogData) {
        int logEntrySize = rawBinaryLogData.get(0).length();
        BinaryLogCounter counter = new BinaryLogCounter(rawBinaryLogData, logEntrySize);

        for (int index = 0; index < logEntrySize; index++) {
            final int effectivelyFinal = index;
            counter.countOfZeroes[index] = rawBinaryLogData.parallelStream().filter(x -> Character.valueOf(x.charAt(effectivelyFinal)).equals('0')).count();
            counter.countOfOnes[index] = rawBinaryLogData.parallelStream().filter(x -> Character.valueOf(x.charAt(effectivelyFinal)).equals('1')).count();
        }

        return counter;
    }

    public int[] getGammaEpsilon() {
        int[] rates = new int[2];
        int size = countOfZeroes.length;

        StringBuilder gammaBinaryAsString = new StringBuilder(size);
        StringBuilder epsilonBinaryAsString = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            if (countOfZeroes[i] > countOfOnes[i]) {
                gammaBinaryAsString.append('0');
                epsilonBinaryAsString.append('1');
            } else if (countOfOnes[i] > countOfZeroes[i]) {
                gammaBinaryAsString.append('1');
                epsilonBinaryAsString.append('0');
            } else {
                throw new RuntimeException("Per requirements, number of zeroes and ones are not expected to be equal.");
            }
        }

        rates[0] = Integer.parseInt(gammaBinaryAsString.toString(), 2);
        rates[1] = Integer.parseInt(epsilonBinaryAsString.toString(), 2);

        return rates;
    }

    public synchronized int[] getLifeSupportRating() {
        int size = countOfZeroes.length;

        for(int index = 0; index < size; index++) {
            filterCandidates(index);
        }

        if (lifeSupportMap.get(OXYGEN_MAP_KEY).size() != 1 || lifeSupportMap.get(CARBON_MAP_KEY).size() != 1) {
            throw new RuntimeException("Single values were not found when filtering life support data per rules!");
        }

        int oxygenValue = Integer.parseInt(lifeSupportMap.get(OXYGEN_MAP_KEY).get(0), 2);
        int carbonValue = Integer.parseInt(lifeSupportMap.get(CARBON_MAP_KEY).get(0), 2);

        return new int[] {oxygenValue, carbonValue};
    }

    private void filterCandidates(int index) {
        List<String> currentOxygenCandidates = lifeSupportMap.get(OXYGEN_MAP_KEY);
        List<String> currentCarbonCandidates = lifeSupportMap.get(CARBON_MAP_KEY);

        if (currentOxygenCandidates.size() > 1) {
            lifeSupportMap.put(OXYGEN_MAP_KEY, makeFilteredList(currentOxygenCandidates, index, true));
        }

        if (currentCarbonCandidates.size() > 1) {
            lifeSupportMap.put(CARBON_MAP_KEY, makeFilteredList(currentCarbonCandidates, index, false));
        }
    }

    private static List<String> makeFilteredList(List<String> input, int index, boolean oxygenCheck) {
        List<String> candidatesWithZeros = input.parallelStream().filter(x -> Character.valueOf(x.charAt(index)).equals('0')).toList();
        List<String> candidatesWithOnes = input.parallelStream().filter(x -> Character.valueOf(x.charAt(index)).equals('1')).toList();

        if (oxygenCheck && candidatesWithOnes.size() >= candidatesWithZeros.size()) {
            return candidatesWithOnes;
        } else if (oxygenCheck) {
            return candidatesWithZeros;
        } else if (candidatesWithZeros.size() <= candidatesWithOnes.size()) {
            return candidatesWithZeros;
        } else {
            return candidatesWithOnes;
        }
    }
}
