package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.activeCells.Conveyor;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {

    String boardName;
    ICell[][] board;

    public Board(String name, String path) {
        boardName = name;
        board = new JSONBoardGenerator().generateJsonBoard(path);
    }

    private ICell[][] brett;

    public void generateBoard() {

    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" | " + brett[i][j]);
            }
            System.out.print(" | ");
            System.out.println();
        }
    }

    @Override
    public String getBoardName() {
        return boardName;
    }

    @Override
    public ICell[][] getBoard() {
        return board;
    }

    @Override
    public ICell getCellAt(int x, int y) {
        return board[x][y];
    }
}
