package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.enums.Direction;
import javafx.geometry.Pos;

import java.util.Iterator;

public class Board implements IBoard {

    String boardName;
    ICell[][] board;

    // Assuming all boards are square for ease of use.
    // In the future we might make a MegaBoard or something.
    int boardWidth;
    int boardHeight;

    public Board(String name, String path) {
        boardName = name;
        board = new JSONBoardGenerator().generateJsonBoard(path);

        // Again, assuming it's square. Might break in the future.
        boardHeight = board.length;
        boardWidth = board[0].length;
    }

    public void generateBoard() {

    }

    /**
     * Method for displaying the cells in the GUIBoard
     */
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" | ");
                if (board[i][j] == null) {
                    System.out.print("empty");
                } else {
                    for (IPiece p : board[i][j].getPiecesInCell()) {
                        System.out.print(p.getName() + "-" + p.getPieceDirection() + " ");
                    }
                }
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
        return board[y][x];
    }

    /*
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
    } */

    public ICell getCellAt(Position pos) {
        //Byttet om disse, var board[pos.getX()][pos.getY()];
        return board[pos.getY()][pos.getX()];
    }

    public ICell getNextCell(Position pos, Direction dir) {
        ICell cell = new Cell();
        switch (dir) {
            case NORTH:
                cell = board[pos.getY() - 1][pos.getX()];
                break;
            case SOUTH:
                cell = board[pos.getY() + 1][pos.getX()];
                break;
            case EAST:
                cell = board[pos.getY()][pos.getX() + 1];
                break;
            case WEST:
                cell = board[pos.getY()][pos.getX() - 1];
                break;
        }
        return cell;
    }

    public boolean insideBoard(Position playerPos, Direction playerDir){
        Position posAfterMove = playerPos.changePos(playerDir);
        System.out.println("Pos after theoretical move " + posAfterMove.toString());
        if(posAfterMove.getY() >= boardHeight || posAfterMove.getY() < 0){
            return false;
        }
        if (posAfterMove.getX() >= boardWidth || posAfterMove.getX() < 0){
            return false;
        }
        return true;
    }

    public int getBoardWidth(){
        return boardWidth;
    }

    public int getBoardHeight(){
        return boardHeight;
    }



   /*public Iterator<ICell> iterator() {
        Iterator<ICell> it = new Iterator<ICell>() {

            private int currX = 0;
            private int currY = 0;

            @Override
            public boolean hasNext() {
                return currY >= board.length && currX >= board[currY].length;
            }

            @Override
            public ICell next() {
                    if(currX >= board[currY].length){
                        currY++;
                        currX = 0;
                    }

                return board[currY][currX];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }*/

}
