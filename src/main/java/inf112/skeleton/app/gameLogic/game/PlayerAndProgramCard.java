package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;

import java.io.Serializable;

public class PlayerAndProgramCard implements Comparable<PlayerAndProgramCard>, Serializable {

    Player player;
    ProgramCard pgCard;

    public PlayerAndProgramCard(ProgramCard pgCard, Player player){
        this.pgCard = pgCard;
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    public ProgramCard getPgCard(){
        return this.pgCard;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public int compareTo(PlayerAndProgramCard otherCard){
        Integer int1 = this.pgCard.getPriority();
        Integer int2 = otherCard.getPgCard().getPriority();
        return int1.compareTo(int2);
    }

}
