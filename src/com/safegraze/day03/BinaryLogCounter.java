package com.safegraze.day03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryLogCounter {

    private final List<Integer> numberOfZeroesPerPosition;
    private final List<Integer> numberOfOnesPerPosition;

    private BinaryLogCounter(int size) {
        numberOfZeroesPerPosition = new ArrayList<Integer>(Collections.nCopies(size, 0));
        numberOfOnesPerPosition = new ArrayList<Integer>(Collections.nCopies(size, 0));
    }

    public static BinaryLogCounter make(List<String> rawBinaryLogData) {

        int logEntrySize = rawBinaryLogData.get(0).trim().length();
        BinaryLogCounter counter = new BinaryLogCounter(logEntrySize);

        for (int i = 0; i < rawBinaryLogData.size(); i++) {
            String logEntry = rawBinaryLogData.get(i);
            char[] logPositionMeasurements = logEntry.trim().toCharArray();
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
}
