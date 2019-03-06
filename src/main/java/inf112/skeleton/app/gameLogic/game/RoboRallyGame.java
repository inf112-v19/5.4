package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.enums.CardType;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.HashMap;
import java.util.List;

public class RoboRallyGame {

    // The GUI.
    private MainGameScreen guiScreen;

    private int totalPlayers = 2;   // Total players in the game
    public Player[] players;       // Players in the game
    private int startHealth = 10;
    public ProgramCardDeck deck;
    private Player currentPlayer;
    private Direction startDirection = Direction.NORTH;
    private HashMap<CardType, Integer> registerCards;   // cards to be used in the register

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.guiScreen = guiScreen;
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];

        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i, 0);
            players[i] = new Player(position, startDirection, startHealth);
        }
        playGame();
    }

//    public RoboRallyGame() {
//        this.deck = new ProgramCardDeck();  // Deck of cards in the game
//        players = new Player[totalPlayers];
//        for (int i = 0; i < players.length; i++) {
//            Position position = new Position(i, 0);
//            players[i] = new Player(position, startDirection, startHealth);
//        }
//    }

    public void playGame(){
        this.deck.shuffleDeck();
        for (Player currentPlayer : players) {
            this.currentPlayer = currentPlayer;
        }
        play();
//        postPlay();
    }

    /**
     * First phase in the game
     * Here the players will get to draw and pick cards
     */
    public void prePlay() {
        int damageTokens = currentPlayer.getDamageTokens();
        int cardsToDraw = 9;            // All players starts with the opportunity to draw nine cards
        cardsToDraw -= damageTokens;    // The player looses one card for each damage token

    }

    /**
     * Second phase in the game
     * Here the game will execute the cards the player picked, check death, flags and conveyor
     */

    public void play() {
        // get all the cards for each register
        this.registerCards = new HashMap<>();
        for (Player player : players) {
            ProgramCard card = player.getPlayerCard();
            int cardPriority = card.getPriority();      // Each card has a priority, the higher value, more priority
            CardType cardType = card.getCardType();
            this.registerCards.put(cardType, cardPriority);
        }
        while (this.registerCards.size() > 0) {
            int highestPriority = 0;
            for (int i = 0; i < registerCards.size(); i++) {
                if (registerCards.get(i) > highestPriority) highestPriority = registerCards.get(i);
            }

        }

        this.registerCards.clear();     // clear the list, make it ready for next register.
    }

    private void postPlay() {

    }

    /**
     * Atm just does actions.
     * @param pickedProgramCards cards that's picked
     */
    public void postPick(List<ProgramCard> pickedProgramCards) {
        for(ProgramCard card: pickedProgramCards){
            System.out.println("Inni postpick gang");
            currentPlayer.setRobot(guiScreen.gimmeRobotTest());
            currentPlayer.doAction(card.getCardType().getAction().getActionType());
        }
    }
}
