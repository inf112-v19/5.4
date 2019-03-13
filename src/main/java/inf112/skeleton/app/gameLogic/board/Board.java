package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Direction;

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

    /**
     * Method for displaying the cells in the Board
     */
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" | " + board[i][j]);
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
    /**
     * Method for retrieving a Cell from a specific coordinate on the board
     */
    public ICell getCellAt(int x, int y) {
        return board[x][y];
    }

    public ICell getNextCell(int x, int y, Direction dir) {
        ICell cell = new Cell();
        switch (dir) {
            case NORTH:
                cell = board[x][y + 1];
                break;
            case SOUTH:
                cell = board[x][y - 1];
                break;
            case EAST:
                cell = board[x + 1][y];
                break;
            case WEST:
                cell = board[x - 1][y];
                break;
        }
        return cell;
    }

    public ICell getCellAt(Position pos) {
        return board[pos.getX()][pos.getY()];
    }

    public ICell getNextCell(Position pos, Direction dir) {
        ICell cell = new Cell();
        switch (dir) {
            case NORTH:
                cell = board[pos.getX()][pos.getY() + 1];
                break;
            case SOUTH:
                cell = board[pos.getX()][pos.getY() - 1];
                break;
            case EAST:
                cell = board[pos.getX() + 1][pos.getY()];
                break;
            case WEST:
                cell = board[pos.getX() - 1][pos.getY()];
                break;
        }
        return cell;
    }
}
