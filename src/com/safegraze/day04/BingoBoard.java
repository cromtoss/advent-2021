package com.safegraze.day04;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoBoard {

    public int getBoardNumber() {
        return boardNumber;
    }

    private final int boardNumber;
    private boolean isWinner = false;
    private int winningScore;
    private final Set<Integer> allNumbersOnBoard = new HashSet<>(BingoUtils.WIN_LIST_SIZE^2);
    private final List<BingoBoardWinCondition> winConditionList = new ArrayList<>(BingoUtils.WIN_LIST_SIZE*2);

    BingoBoard(int boardNumber, List<String> rawInput) {
        this.boardNumber = boardNumber;
        Map<Integer, List<Integer>> columnValueTracker = IntStream.range(1, BingoUtils.WIN_LIST_SIZE+1)
                .boxed()
                .collect(Collectors.toMap(i -> i, ArrayList::new));

        for (String boardRow : rawInput) {
            List<String> rowValues = Arrays.asList(boardRow.split(" "));
            List<Integer> rowNumbers = rowValues.stream().map(String::trim).map(Integer::parseInt).toList();
            allNumbersOnBoard.addAll(rowNumbers);
            winConditionList.add(new BingoBoardWinCondition(rowNumbers));
            for (int i = 0; i < rowNumbers.size(); i++) {
                columnValueTracker.get(i+1).add(rowNumbers.get(i));
            }
        }

        for (List<Integer> otherWinningSubsets : columnValueTracker.values()) {
            winConditionList.add(new BingoBoardWinCondition(otherWinningSubsets));
        }
    }

    void checkWinningStateOnNewDraw(List<Integer> calledNumbers) {
        if (isWinner) {
            return;
        }

        synchronized(this) {
            isWinner = winConditionList.parallelStream().anyMatch(wc -> wc.checkWinner(calledNumbers));
            if (isWinner) {
                List<Integer> numbersNotCalled = allNumbersOnBoard.parallelStream().filter(i -> !calledNumbers.contains(i)).toList();
                Integer sumNotCalled = numbersNotCalled.parallelStream().reduce(0, Integer::sum);
                winningScore = sumNotCalled * calledNumbers.get(calledNumbers.size() - 1);
            }
        }
    }

    int getScore(List<Integer> calledNumbers) {
        return winningScore;
    }

    synchronized boolean isWinner() {
        return isWinner;
    }

}
