/**package inf112.skeleton.app.gameLogic;

import com.sun.jdi.request.DuplicateRequestException;
import com.sun.tools.internal.ws.wsdl.framework.DuplicateEntityException;
import inf112.skeleton.app.RoboRallyGame.Enum.Action;
import inf112.skeleton.app.gameLogic.enums.Rotation;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import org.lwjgl.Sys;

import java.util.*;

public class RoboRallyGame {
    private List<Player> playerList;
    private Queue<PlayerAction> actionList;

    public RoboRallyGame() {
        playerList = new ArrayList<>();
        actionList = new LinkedList<>();
    }

    public void addPlayerToList(){

    }

    public boolean actionListIsEmpty(){
        return actionList.isEmpty();
    }

    public boolean playerListIsEmpty(){
        return playerList.isEmpty();
    }

    public void doAction() {
        if (!actionListIsEmpty()) {
            PlayerAction action = actionList.poll();
            switch (action.getAction()) {
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

    public void addPlayerToList(Player player) throws DuplicateEntityException{
        if(!playerList.contains(player)){
            playerList.add(player);
        } else {
            System.out.println("This player already exists");
        }
    }

    public void addActionToList(PlayerAction action) {
        actionList.add(action);
    }

}
*/