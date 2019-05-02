package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.Player;

import java.util.*;


public class TestGame {
    private List<Player> playerList;
    private Queue<Action> actionList;

    public TestGame() {
        playerList = new ArrayList<>();
        actionList = new LinkedList<>();
    }

    public void addPlayerToList() {

    }

    public boolean actionListIsEmpty() {
        return actionList.isEmpty();
    }

    public boolean playerListIsEmpty() {
        return playerList.isEmpty();
    }

    public void doAction() {
        if (!actionListIsEmpty()) {
            Action action = actionList.poll();
            switch (action.getActionType()) {
                case DAMAGE:
                    action.getPlayer().takeDamage(action.getAmount());
                    break;
                case MOVE:
                    action.getPlayer().move(action.getAmount());
                    break;
                case TURN:
                    if (action.getRotation() != null) {
                        action.getPlayer().rotate(action.getRotation());
                    }
                    break;
            }
        } else {
            System.out.println("actionList Empty");
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayerToList(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
        } else {
            System.out.println("This player already exists");
        }
    }

    public void addActionToList(Action action) {
        actionList.add(action);
    }

}
