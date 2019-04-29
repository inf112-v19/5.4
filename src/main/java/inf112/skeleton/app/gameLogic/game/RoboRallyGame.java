package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoboRallyGame {

    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers = 3;   // Total players in the game
    private Player[] players;       // Players in the game
    private int startHealth = 3;
    private String boardPath = "FlagBoard.json";

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Board board;

    private PlayerActionWrapper playerActionQueue;

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.playerActionQueue = new PlayerActionWrapper();

        this.guiScreen = guiScreen;
        //Testing with FlagBoard
        //this.board = new Board("Captain Hook", "DankBoard.json");
        this.board = new Board("Captain Hook", boardPath);
        //board.displayBoard();
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i+5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue
            players[i] = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, this.board, playerActionQueue);
            board.addPiece(position, players[i]);
            System.out.println("player made!!");
            System.out.println(players[i].getPos().getX() + " " + players[i].getPos().getY());
        }
        playGame();
    }

    public void playGame(){
        this.deck.shuffleDeck();
        this.currentPlayer = players[0];
//        for (Player currentPlayer : players) {
//            this.currentPlayer = currentPlayer;
//        }
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
        //this.currentPlayer =currentPlayer;
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

            currentPlayer.getChecker().doAction(card.getCardType().getAction());
            System.out.println("Actions in actionList: ");
            playerActionQueue.viewActionList();
            System.out.println("Length of actionList: " + playerActionQueue.size());
            //for testin purpuss
            currentPlayer.getChecker().checkForFlag();
            //System.out.println("FIRST ACTION IN QUEUE: " + playerActionQueue.getElement().getAction().getDescription());
        }
    }

    public Player[] getPlayers(){
        return this.players;
    }
    public Board getBoard(){return this.board;}


}
