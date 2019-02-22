package inf112.skeleton.app.gameLogic.board.activeCells;

import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.board.IActiveCell;

public class Conveyor implements IActiveCell {

    private Direction direction;
    private ActionType actionType;

    public Conveyor(Direction dir, ActionType speed){
        this.direction = dir;
        this.actionType = speed;
    }

    @Override
    public IActiveCell getType() {
        return null;
    }

    @Override
    public String getName() {
        return "Conveyor";
    }

    @Override
    public char getSymbol() {
        switch (this.direction) {
            case NORTH: return '↑';
            case SOUTH: return '↓';
            case EAST: return '→';
            default: return '←';
        }
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }
}
