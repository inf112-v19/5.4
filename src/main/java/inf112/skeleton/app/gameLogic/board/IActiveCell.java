package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.Game.Enum.ActionType;

public interface IActiveCell extends ICell{

    /**
     * Returns the action this cell does
     * @return Action type of action
     */
    ActionType getActionType();

}
