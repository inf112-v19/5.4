package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;

public class PlayerAndProgramCard {

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

}
