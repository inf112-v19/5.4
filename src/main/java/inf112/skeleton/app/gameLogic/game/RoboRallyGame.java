package inf112.skeleton.app.gameLogic.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.board.GUIBoard;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.LaserCalculator;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.board.pieces.LaserShooter;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.*;

public class RoboRallyGame {

    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers = 3;   // Total players in the game
    private List<Player> players;       // Players in the game
    private int startHealth = 3;
    private String boardPath = "Board1.json";
    private LaserCalculator laserCalculator;

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Board board;

    private Checker2 checker2;

    public RoboRallyGame(MainGameScreen guiScreen) {

        this.guiScreen = guiScreen;
        this.board = new Board("Captain Hook", boardPath);
        this.checker2 = new Checker2(board);

        this.deck = new ProgramCardDeck();  // Deck of cards in the game

        players = new ArrayList<Player>();
        for (int i = 0; i < totalPlayers; i++) {
            Position position = new Position(i+5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue
            players.add(new Player(Integer.toString(i), position, Direction.SOUTH, startHealth));
            board.addPiece(position, players.get(i));
            System.out.println("player made!!");
        }

        board.displayBoard();

        this.laserCalculator = new LaserCalculator(board, players);

        playGame();
    }

    public void playGame(){
        this.deck.shuffleDeck();
        this.currentPlayer = players.get(0);
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

    public void postPick(List<ProgramCard> pickedProgramCards){

        List<List<ProgramCard>> allCards = new ArrayList<>();

        for (ProgramCard currCard : pickedProgramCards){
            List<ProgramCard> currcards = new ArrayList<ProgramCard>(){{add(currCard);}};
            allCards.add(currcards);
        }

        this.executeCards(allCards);
    }

    public void executeCards(List<List<ProgramCard>> allProgramCards) {

        // All innermost actions: Actions that are do be executed in paralell.
        // One layer outside: all actions originating from ONE card, e.g MOVE 3.
        // Outermost layer: all the actions from all the cards.
        List<List<List<PlayerAction>>> allActions = new ArrayList<>();
        List<Action> laserAnimations = new ArrayList<>();
        List<List<PlayerAction>> conveyorActions = new ArrayList<>();

        for (List<ProgramCard> onePhaseProgramCards : allProgramCards) {

            // Sorts all phase-cards.
            Collections.sort(onePhaseProgramCards);

            for (ProgramCard card : onePhaseProgramCards) {

                // All the actions originating from ONE card.

                List<List<PlayerAction>> cardActions = checker2.doCard(card, currentPlayer);
                System.out.println("ALL ACTIONS LMOA");
                System.out.println(cardActions);

                System.out.println("Actions in actionList: ");
                for (List<PlayerAction> tempBig : cardActions) {
                    System.out.println("----------");
                    for (PlayerAction pa : tempBig) {
                        System.out.println("Player: " + pa.getPlayer().getName() + " Action: " + pa.getAction().getDescription());
                    }

                }


                allActions.add(cardActions);

            }

            // Conveyors actions for one round, added

            conveyorActions.add(checker2.doPiecesMoves(players));
            System.out.println("These are the conveyor moves INSIDE THE THING");
            System.out.println(conveyorActions);

            SequenceAction laserAnimation = this.guiScreen.getGUIBoard().getLaserAnimations(this.laserCalculator.laserCalculation());
            laserAnimations.add(laserAnimation);

        }
        System.out.println("These are the conveyor moves");
        System.out.println(conveyorActions);
        this.guiScreen.getGUIBoard().doGUIActions(allActions, laserAnimations, conveyorActions);
        postExecution();
    }

    private void postExecution() {
        List<Player> deadPlayers = this.board.getDeadPlayers();
        for(Player currPlayer : deadPlayers){
            this.guiScreen.getGUIBoard().respawnPlayer(currPlayer);
        }
    }


    public List<Player> getPlayers(){
        return this.players;
    }
    public Board getBoard(){return this.board;}


}
