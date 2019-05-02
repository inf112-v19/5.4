package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;

public class Game {

    public void playGame(Player currentPlayer, ProgramCardDeck programCards){
        prePlay(currentPlayer);
        play();
        postPlay();
    }

    /**
     * First phase in the game
     * Here the player will get to draw and pick cards
     */
    private void prePlay(Player currentPlayer) {
        int damageTokens = currentPlayer.getDamageTokens();
        int cardsToDraw = 9;
        cardsToDraw -= damageTokens;

        for (int i = 0; i < cardsToDraw; i++) {
            // TODO take cards from deck and assign them to the player
        }




    }

    /**
     * Second phase in the game
     * Here the game will execute the cards the player picked, check death, flags and conveyor
     */
    private void play() {

    }

    private void postPlay() {

    }
}
