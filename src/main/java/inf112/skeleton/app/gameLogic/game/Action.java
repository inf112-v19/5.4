package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Rotation;


public class Action {
    private Player player;
    private ActionType actionType;
    private int amount;
    private Rotation rotation;

    public Action(Player player, ProgramCard programCard) {
        this.player = player;
        this.actionType = programCard.getCardType().getActionType();
        this.amount = programCard.getCardType().getMoveDistance();
        this.rotation = programCard.getCardType().getRotation();
    }

    public Action(Player player, ActionType actionType, int amount) {
        this.player = player;
        this.actionType = actionType;
        this.amount = amount;
        this.rotation = null;
    }

    public Action(Player player, ActionType actionType, Rotation rotation) {
        this.player = player;
        this.actionType = actionType;
        this.amount = 0;
        this.rotation = rotation;
    }


    public Rotation getRotation() {
        return rotation;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmount() {
        return amount;
    }
}
