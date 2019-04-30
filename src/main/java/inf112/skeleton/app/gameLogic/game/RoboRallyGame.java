package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;
import inf112.skeleton.app.gameLogic.board.pieces.Gears;
import inf112.skeleton.app.gameLogic.board.pieces.Hole;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.PiecesToCheckFor;
import sun.awt.image.ImageWatched;

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

    private Checker checker;

    private PlayerActionWrapper playerActionQueue;

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.guiScreen = guiScreen;
        this.board = new Board("Captain Hook", boardPath);
        this.checker = new Checker(board);
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i + 5, 7);
            players[i] = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth);
            board.addPiece(position, players[i]);
            System.out.println("player made!!");
            System.out.println(players[i].getPos().getX() + " " + players[i].getPos().getY());
        }
        playGame();
    }

    public void playGame() {
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
     *
     * @param pickedProgramCards
     */
    public void postPick(List<ProgramCard> pickedProgramCards) {
        for (ProgramCard card : pickedProgramCards) {

            LinkedList<LinkedList<PlayerAction>> temp = checker.doAction(card.getCardType().getAction(), currentPlayer);
            System.out.println("Actions in actionList: ");
            for (LinkedList<PlayerAction> tempBig : temp) {
                System.out.println("----------");
                for (PlayerAction pa : tempBig) {
                    System.out.println("Player: " + pa.getPlayer().getName() + " Action: " + pa.getAction().getDescription());
                }

            }

            //for testin purpuss
            checker.checkForFlag(currentPlayer);
        }

        //Ligger på heilt feil sted. man må iterere over spillere i postPlay()
        LinkedList<LinkedList<PlayerAction>> boss = new LinkedList<>();
        boss.add(new LinkedList<PlayerAction>());
        for(IPiece piece : board.getCellAt(currentPlayer.getPos()).getPiecesInCell()){
            if(piece instanceof Conveyor){
                checker.move(piece.getPieceDirection(), currentPlayer, boss);
                break;
            }
            if(piece instanceof Gears){
                boss = checker.doAction(((Gears)piece).getAction(), currentPlayer);
                break;
            }
            if(piece instanceof Laser){
                //Sjekk antall laser i ruten ellerno
            }
        }
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }

}
