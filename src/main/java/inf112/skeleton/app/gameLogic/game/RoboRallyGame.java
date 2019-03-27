package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Flag;

import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.List;

public class RoboRallyGame {

    // The GUI.
    private MainGameScreen guiScreen;

    private int totalPlayers = 3;   // Total players in the game
    private Player[] players;       // Players in the game
    private int startHealth = 3;
    private String boardPath = "FlagBoard.json";

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Board board;

    public RoboRallyGame(MainGameScreen guiScreen) {

        this.guiScreen = guiScreen;
        //Testing with FlagBoard
        //this.board = new Board("Captain Hook", "DankBoard.json");
        this.board = new Board("Captain Hook", boardPath);
        //board.displayBoard();
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i+5, 7);
            players[i] = new Player(position, Direction.SOUTH, startHealth, this.board);
            //board.getCellAt(position).addPiece(players[i]);
            //board.addPiece(position, players[i]);
            System.out.println("player made!!");
            System.out.println(players[i].getPos().getX() + " " + players[i].getPos().getY());

        }
        playGame();
    }

    public void playGame(){
        this.deck.shuffleDeck();
        for (Player currentPlayer : players) {
            this.currentPlayer = currentPlayer;
        }
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
            Checker checker = new Checker(currentPlayer, card.getCardType().getAction(), board);
            checker.doAction();

            //for testin purpuss
            checker.checkForFlag();
        }
    }

    public Player[] getPlayers(){
        return this.players;
    }
    public Board getBoard(){return this.board;}

    public int getTotalPlayers() {
        return this.totalPlayers;
    }

    public int getStartHealth() {
        return this.startHealth;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public ProgramCardDeck getDeck() {
        return this.deck;
    }
}
