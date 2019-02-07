package inf112.skeleton.app.Game.board;

public interface IBoard {

    /**
     * A method for generating the game board.
     */
    void generateBoard();

    /**
     * Prints out the board
     * @param brett 2D array to be printed.
     */
    void displayBoard(char[][] brett);

}