package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        this.playerActionQueue = new PlayerActionWrapper();

        this.guiScreen = guiScreen;

        this.board = new Board("Captain Hook", boardPath);
        this.checker = new Checker(board);
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new Player[totalPlayers];
        for (int i = 0; i < players.length; i++) {
            Position position = new Position(i + 5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue
            players[i] = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue);
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

        for(List<ProgramCard> onePhaseProgramCards : allProgramCards){

            // Sorts all phase-cards.
            Collections.sort(onePhaseProgramCards);

            for(ProgramCard card: onePhaseProgramCards) {

                // All the actions originating from ONE card.
                List<List<PlayerAction>> cardActions = checker.doAction(card.getCardType().getAction(), currentPlayer);

                System.out.println("Actions in actionList: ");
                for (List<PlayerAction> tempBig : cardActions) {
                    System.out.println("----------");
                    for (PlayerAction pa : tempBig) {
                        System.out.println("Player: " + pa.getPlayer().getName() + " Action: " + pa.getAction().getDescription());
                    }

                }

                allActions.add(cardActions);

                // DO LASERSHOOTING AND CONVEYOR MOVING HERE

            }

        }


        this.guiScreen.getGUIBoard().doGUIActions(allActions);
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }


}
