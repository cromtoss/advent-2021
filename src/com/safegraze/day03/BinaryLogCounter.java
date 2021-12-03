package com.safegraze.day03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BinaryLogCounter {

    private static final String OXYGEN_MAP_KEY = "oxygen";
    private static final String CARBON_MAP_KEY = "carbon";

    private List<String> trimmedLogData;
    private HashMap<String, List<String>> lifeSupportMap = new HashMap<>(2);
    private final List<Integer> numberOfZeroesPerPosition;
    private final List<Integer> numberOfOnesPerPosition;

    private BinaryLogCounter(int size) {
        numberOfZeroesPerPosition = new ArrayList<Integer>(Collections.nCopies(size, 0));
        numberOfOnesPerPosition = new ArrayList<Integer>(Collections.nCopies(size, 0));
    }

    public static BinaryLogCounter make(List<String> rawBinaryLogData) {
        int logEntrySize = rawBinaryLogData.get(0).trim().length();
        BinaryLogCounter counter = new BinaryLogCounter(logEntrySize);
        counter.trimmedLogData = new ArrayList<>(rawBinaryLogData.size());

        for (int i = 0; i < rawBinaryLogData.size(); i++) {
            String logEntry = rawBinaryLogData.get(i);
            String trimmedEntry = logEntry.trim();
            counter.trimmedLogData.add(trimmedEntry);
            char[] logPositionMeasurements = trimmedEntry.toCharArray();
            for (int j = 0; j < logPositionMeasurements.length; j++) {
                char positionalMeasurement = logPositionMeasurements[j];
                if (positionalMeasurement == '0') {
                    int update = counter.numberOfZeroesPerPosition.get(j) + 1;
                    counter.numberOfZeroesPerPosition.set(j, update);
                } else if (positionalMeasurement == '1') {
                    int update = counter.numberOfOnesPerPosition.get(j) + 1;
                    counter.numberOfOnesPerPosition.set(j, update);
                } else {
                    throw new RuntimeException("Unexpected character in binary log entry: " + positionalMeasurement);
                }
            }
        }

        return counter;
    }

    public List<Integer> getNumberOfZeroesPerPosition() {
        return numberOfZeroesPerPosition;
    }

    public List<Integer> getNumberOfOnesPerPosition() {
        return numberOfOnesPerPosition;
    }

    public int[] getRates() {
        int[] rates = new int[2];
        int size = numberOfZeroesPerPosition.size();

        StringBuilder gammaBinaryAsString = new StringBuilder(size);
        StringBuilder epsilonBinaryAsString = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            if (numberOfZeroesPerPosition.get(i) > numberOfOnesPerPosition.get(i)) {
                gammaBinaryAsString.append('0');
                epsilonBinaryAsString.append('1');
            } else if (numberOfOnesPerPosition.get(i) > numberOfZeroesPerPosition.get(i)) {
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

    public int[] getLifeSupportRating() {
        int[] rates = new int[2];
        int size = numberOfZeroesPerPosition.size();

        initializeMap();
        for(int index = 0; index < size; index++) {
            filterCandidates(index);
        }

        if (lifeSupportMap.get(OXYGEN_MAP_KEY).size() != 1 || lifeSupportMap.get(CARBON_MAP_KEY).size() != 1) {
            throw new RuntimeException("Single values were not found when filtering life support data per rules!");
        }

        rates[0] = Integer.parseInt(lifeSupportMap.get(OXYGEN_MAP_KEY).get(0), 2);
        rates[1] = Integer.parseInt(lifeSupportMap.get(CARBON_MAP_KEY).get(0), 2);

        return rates;
    }

    /**
     * Terrible coding with mutable internal state; think about immutable approaches.
     */
    private void initializeMap() {
        lifeSupportMap.put(OXYGEN_MAP_KEY, trimmedLogData);
        lifeSupportMap.put(CARBON_MAP_KEY, trimmedLogData);
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
