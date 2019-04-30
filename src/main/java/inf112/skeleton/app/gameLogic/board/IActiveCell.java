package inf112.skeleton.app.gameLogic.board;

import inf112.skeleton.app.gameLogic.enums.Action;

public interface IActiveCell extends ICell {

    /**
     * Returns the action this cell does
     *
     * @return PlayerAction type of action
     */
    Action getAction();

}
