package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class PlayerAction {
    private Player player;
    private Action action;
    private Direction playerCurrentDir;

    public PlayerAction(Player player, Action action) {
        this.player = player;
        this.action = action;
        this.playerCurrentDir = player.getDirection();
    }

    public Action getAction() {
        return action;
    }

    public Player getPlayer() {
        return player;
    }

    public Direction getPlayerCurrentDir(){return playerCurrentDir;}
}
