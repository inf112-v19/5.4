package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.gameLogic.enums.Direction;

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
            for (int j = 0; j < board[i].length; j++) {
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
                cell = board[x][y+1];
                break;
            case SOUTH:
                cell = board[x][y-1];
                break;
            case EAST:
                cell = board[x+1][y];
                break;
            case WEST:
                cell = board[x-1][y];
                break;
        }
        return cell;
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
