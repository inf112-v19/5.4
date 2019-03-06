package inf112.skeleton.app.gameLogic.board;

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
}
