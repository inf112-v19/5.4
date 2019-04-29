package inf112.skeleton.app.gameLogic.game;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerActionWrapper {
    private LinkedList<LinkedList<PlayerAction>> playerActionList;

    public PlayerActionWrapper(){
        playerActionList = new LinkedList<>();
    }

    public void addElementToCurrent(PlayerAction playerAction){
        playerActionList.getLast().add(playerAction);
    }

    public void addElement(){
        playerActionList.add(new LinkedList<PlayerAction>());
    }

    public int size(){
        return playerActionList.size();
    }

    public void viewActionList(){
        for(Queue<PlayerAction> fromQueue : playerActionList){
            System.out.println("----------");
            for(PlayerAction pa : fromQueue) {
                System.out.println("Player:" + pa.getPlayer().getName() + " - Action: " + pa.getAction().getDescription());
            }
        }
    }

    public LinkedList<PlayerAction> getFirstList(){
        LinkedList<PlayerAction> temp = playerActionList.getFirst();
        playerActionList.removeFirst();
        return temp;
    }
}
