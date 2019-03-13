package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.List;

public class RoboRallyGame {

    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers = 2;   // Total players in the game
    private Player[] players;       // Players in the game
    private int startHealth = 10;
    private ProgramCardDeck deck;
    private Player currentPlayer;

    public RoboRallyGame(MainGameScreen guiScreen) {

        this.guiScreen = guiScreen;

        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i, 0);
            players[i] = new Player(position, Direction.NORTH, startHealth);
        }

        playGame();
    }

    public void playGame(){
        this.deck.shuffleDeck();
        for (Player currentPlayer : players) {
            this.currentPlayer = currentPlayer;
        }
//        play();
//        postPlay();
    }

    /**
     * First phase in the game
     * Here the player will get to draw and pick cards
     */
    public void prePlay() {
        int damageTokens = currentPlayer.getDamageTokens();
        int cardsToDraw = 9;
        cardsToDraw -= damageTokens;

        List<ProgramCard> cards = deck.drawXCards(cardsToDraw);

        // TODO take cards from deck and assign them to the player
        this.currentPlayer =currentPlayer;
        this.guiScreen.pickCardPhase(cards);
    }

    /**
     * Second phase in the game
     * Here the game will execute the cards the player picked, check death, flags and conveyor
     */
    private void play() {

    }

    private void postPlay() {

    }

    /**
     * Atm just does actions.
     * @param pickedProgramCards
     */
    public void postPick(List<ProgramCard> pickedProgramCards) {
        for(ProgramCard card: pickedProgramCards){

            currentPlayer.setRobot(guiScreen.gimmeRobotTest());
            currentPlayer.doAction(card.getCardType().getAction());
        }
    }
}
