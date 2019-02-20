package inf112.skeleton.app.Game.board;

public interface ICell {
    /**
     * Returns the type of cell
     * @return ICell type of cell
     */
    ICell getType();

    /**
     * Returns the name of the Cell
     * @return String name of Cell
     */
    String getName();

    /**
     * Returns the symbol that represents this cell
     * @return char symbol representation
     */
    char getSymbol();

}