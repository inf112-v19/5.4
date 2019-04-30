package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.gameLogic.enums.Direction;

public interface IPiece {
    /**
     * Returns the type of GUIPiece
     *
     * @return ICell type of GUIPiece
     */
    IPiece getType();

    /**
     * Returns the name of the GUIPiece
     *
     * @return String name of GUIPiece
     */
    String getName();

    /**
     * Returns the symbol that represents this GUIPiece
     *
     * @return char symbol representation
     */
    char getSymbol();

    /**
     * Returns the direction of the GUIPiece
     *
     * @return Direction
     */
    Direction getPieceDirection();

    /**
     * Each game piece has a GUI friend.
     *
     * @return GUIPiece
     */
    GUIPiece getGUIPiece();
}
