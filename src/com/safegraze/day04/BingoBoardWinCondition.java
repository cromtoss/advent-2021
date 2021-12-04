package com.safegraze.day04;

import java.util.ArrayList;
import java.util.List;

public class BingoBoardWinCondition {

    final List<Integer> listToWin;
    boolean isWinner = false;

    public BingoBoardWinCondition(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Win condition must contain expected non-null, non-empty content!");
        }

        listToWin = new ArrayList<>(input);
    }

    public boolean checkWinner(List<Integer> calledNumbers) {
        isWinner = calledNumbers.containsAll(listToWin);
        return isWinner;
    }

    public boolean isWinner() {
        return isWinner;
    }

}
