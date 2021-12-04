package com.safegraze.day04;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    public static List<BingoBoard> makeBoardsFromRawInput(List<String> rawBoardsInput) {
        List<BingoBoard> bingoBoards = new ArrayList<>();

        if (rawBoardsInput.size() % BingoUtils.WIN_LIST_SIZE != 0) {
            throw new RuntimeException("Boards input does not create an even number of boards!");
        }

        for (int i = 0; i < rawBoardsInput.size(); i = i + BingoUtils.WIN_LIST_SIZE) {
            List<String> subList = rawBoardsInput.subList(i, i+BingoUtils.WIN_LIST_SIZE);
            bingoBoards.add(new BingoBoard(i, subList));
        }

        return bingoBoards;
    }
}
