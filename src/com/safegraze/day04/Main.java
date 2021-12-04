package com.safegraze.day04;

import com.safegraze.shared.SubmarineUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day04-boards.txt");
        List<String> rawInputLines = SubmarineUtils.parseRawInput(path);
        List<BingoBoard> bingoBoards = BoardFactory.makeBoardsFromRawInput(rawInputLines);

        Path drawnNumbersPath = FileSystems.getDefault().getPath("/home/cromtoss/repo/advent-2021/day04-draws.txt");
        List<String> rawDrawLines = SubmarineUtils.parseRawInput(drawnNumbersPath);
        List<String> drawStrings = Arrays.asList(rawDrawLines.get(0).split(","));
        List<Integer> drawnNumbers = drawStrings.stream().map(String::trim).map(Integer::parseInt).toList();

        List<Integer> drawRunningLog = new ArrayList<>(drawnNumbers.size());

        for(Integer draw : drawnNumbers) {
            drawRunningLog.add(draw);
            for(BingoBoard board : bingoBoards) {
                if (board.isWinner()) {
                    continue; //skip this board, already noted it won.
                }
                board.checkWinningStateOnNewDraw(drawRunningLog);
                if (board.isWinner()) {
                    System.out.println("New winner board is: " + board.getBoardNumber() + " and score is: " + board.getScore(drawRunningLog));
                }
            }
        }
    }
}
