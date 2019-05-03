package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.gameLogic.Player;

import java.util.List;

public interface ICell {
    /**
     * Returns the type of cell
     *
     * @return ICell type of cell
     */
    ICell getType();

    /**
     * Returns the name of the Cell
     *
     * @return String name of Cell
     */
    String getName();

    /**
     * Returns the symbol that represents this cell
     *
     * @return char symbol representation
     */
    char getSymbol();

    public void addPiece(IPiece piece);

    List<IPiece> getPiecesInCell();

    void removePlayer(Player player);
}
