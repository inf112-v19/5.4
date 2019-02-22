package inf112.skeleton.app.gameLogic.board.activeCells;

import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.board.IActiveCell ;

public class Gears implements IActiveCell {

    private ActionType actionType;

    public Gears(ActionType rotation){
        this.actionType = rotation;
    }

    @Override
    public IActiveCell getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Gear";
    }

    @Override
    public char getSymbol() {
        return '*';
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }
}
