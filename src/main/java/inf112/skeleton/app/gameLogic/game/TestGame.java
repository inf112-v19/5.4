package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import java.util.*;


public class TestGame {
    private List<Player> playerList;
    private Queue<Action> actionList;

    public TestGame() {
        playerList = new ArrayList<>();
        actionList = new LinkedList<>();
    }

    public void doAllActions() {
        while (actionListIsNotEmpty()) {
            doAction(actionList.poll());
        }
    }

    public void doAction(Action action) {
        switch (action.getActionType().getActionTypeType()) {
            case MOVE:
                action.getPlayer().move(action.getActionType().getValue());
                break;
            case DAMAGE:
                action.getPlayer().takeDamage(action.getActionType().getValue());
                break;
            case ROTATE:
                action.getPlayer().rotate(action.getActionType().getRotation());
                break;
        }
    }

    public void addActionToList(Action action) {
        actionList.add(action);
    }

    public boolean actionListIsNotEmpty() {
        return !actionList.isEmpty();
    }

    public boolean playerListIsNotEmpty() {
        return !playerList.isEmpty();
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


}
