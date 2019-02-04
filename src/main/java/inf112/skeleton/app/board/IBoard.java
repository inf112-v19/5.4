package inf112.skeleton.app;

public interface IBoard {

    /**
     * A method for generating the game board.
     * @param height The height of the board
     * @param width The width of the board
     */
    void generateBoard(int height, int width);

    /**
     * Prints out the board
     * @param brett 2D array to be printed.
     */
    void displayBoard(char[][] brett);

}