package inf112.skeleton.app.Game.board;

import inf112.skeleton.app.Game.Enum.Direction;

public interface IPiece {
    /**
     * Returns the type of Piece
     * @return ICell type of Piece
     */
    IPiece getType();

    /**
     * Returns the name of the Piece
     * @return String name of Piece
     */
    String getName();

    /**
     * Returns the symbol that represents this Piece
     * @return char symbol representation
     */
    char getSymbol();

    /**
     * Returns the direction of the Piece
     * @return Direction
     */
    Direction getRotation();
}
