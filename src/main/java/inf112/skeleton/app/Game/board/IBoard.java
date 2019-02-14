package inf112.skeleton.app.Game.board;

import java.util.List;

public interface IBoard {

    /**
     * Prints a basic representation of the elements in the board.
     */
    void displayBoard();

    /**
     * A method for returning the board name.
     * @return The name of the board
     */
    String getBoardName();

    /**
     * A method for returning the boardList
     * @return The list containing the board elements
     */
    List<List<IPiece>> getBoard();

}