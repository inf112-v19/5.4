package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.List;
import java.util.Stack;

public class RoboRallyGame {

    // The GUI.
    private MainGameScreen guiScreen;

    private int totalPlayers = 2;   // Total players in the game
    public Player[] players;       // Players in the game
    private int startHealth = 10;
    public ProgramCardDeck deck;
    private Player currentPlayer;
    private Direction startDirection = Direction.NORTH;

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.guiScreen = guiScreen;

        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i, 0);
            players[i] = new Player(position, startDirection, startHealth);
        }
    }

    public RoboRallyGame() {
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i, 0);
            players[i] = new Player(position, startDirection, startHealth);
        }
    }

    public void playGame(){
        this.deck.shuffleDeck();
        for (Player currentPlayer : players) {
            this.currentPlayer = currentPlayer;
            prePlay(currentPlayer);
        }
//        play();
//        postPlay();
    }

    /**
     * First phase in the game
     * Here the players will get to draw and pick cards
     * @param currentPlayer The player in the game
     */
    private void prePlay(Player currentPlayer) {
        int damageTokens = currentPlayer.getDamageTokens();
        int cardsToDraw = 9;            // All players starts with the opportunity to draw nine cards
        cardsToDraw -= damageTokens;    // The player looses one card for each damage token

        chooseCards(currentPlayer, cardsToDraw);     // Deal cards to the player


//        List<ProgramCard> cards = deck.drawXCards(cardsToDraw);

//        this.currentPlayer = currentPlayer;
//        this.guiScreen.pickCardPhase(cards);

        }

    private void chooseCards(Player currentPlayer, int cardsToDraw) {
//        playerPickCards(currentPlayer);                 // Each player picks the cards and arrange them
        for (int i = 0; i < cardsToDraw; i++) {
            currentPlayer.addProgramCard(this.deck.getTopCard());
        }
        playerPickCards(currentPlayer);
    }

    // TODO each player have to pick the cards to play, and arrange them in the order the player wants to
    private void playerPickCards(Player currentPlayer) {
        Stack<ProgramCard> playerDeck = currentPlayer.returnDeck();
        for (ProgramCard card : playerDeck) {
            currentPlayer.doAction(card.getCardType().getAction().getActionType());
        }
    }


    /**
     * Second phase in the game
     * Here the game will execute the cards the player picked, check death, flags and conveyor
     */
    private void play() {

//        for (Player player : players) {
//            player.popCard();
//            break;
//        }
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
