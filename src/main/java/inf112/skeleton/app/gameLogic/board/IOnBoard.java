package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;

public interface IOnBoard {

    /**
     * Returns the type of this board element
     * @return IOnBoard type of board element
     */
    IOnBoard getType();

    /**
     * Returns the name of the board element
     * @return String name of board element
     */
    String getName();

    /**
     * Returns the symbol that represents this board element
     * @return char symbol representation
     */
    char getSymbol();

    /**
     * Returns the direction of the board element.
     * @return Direction
     */
    Direction getRotation();
    /**
     * Returns the action this board element does.
     * @return Action type of action
     */
    ActionType getActionType();

}
