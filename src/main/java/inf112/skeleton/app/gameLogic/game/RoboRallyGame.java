package inf112.skeleton.app.gameLogic.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.LaserCalculator;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.pieces.SpawnPlatform;
import inf112.skeleton.app.gameLogic.enums.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoboRallyGame {

    private CardPicker cardPicker;
    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers;
    private List<Player> players;
    private int startHealth = 3;
    private String boardPath = "Board1.json";
    private LaserCalculator laserCalculator;

    private ProgramCardDeck deck;
    private Player currentPlayer;

    private Board board;
    private List<SpawnPlatform> spawnPlatforms;

    private Checker2 checker2;

    public RoboRallyGame(MainGameScreen guiScreen, int numberOfPlayers) {
        this.totalPlayers = numberOfPlayers;
        this.guiScreen = guiScreen;
        this.board = new Board("Captain Hook", boardPath);
        this.spawnPlatforms = board.getSpawnPlatforms();
        this.checker2 = new Checker2(board);
        this.deck = new ProgramCardDeck();

        players = new ArrayList<>();

        pveGenerator();
        this.deck = new ProgramCardDeck();  // Deck of cards in the game

        players = new ArrayList<Player>();
        if (guiScreen.getIsPvpMatch()) {
            pvpGenerator();
        }
        else {
            pveGenerator();
        }


        this.laserCalculator = new LaserCalculator(board, players);
        board.sortBoard();

        this.cardPicker = new CardPicker(players, this);

        playGame();
    }

    public void playGame() {
        this.deck.shuffleDeck();
        this.currentPlayer = players.get(0);
    }

    /**
     * First phase in the game
     * Here the player will get to draw and pick cards
     */
    public void prePlay() {


        // TODO take cards from deck and assign them to the player
        this.cardPicker.startCardPicking();

    }

    /**
     * Second phase in the game
     * Here the game will execute the cards the player picked, check death, flags and conveyor
     */
    private void play() {

    }

    private void postPlay() {

    }

    public void executeCards(List<List<PlayerAndProgramCard>> allProgramCards) {

        this.guiScreen.getDisplayLog().clearLog();

        // All innermost actions: Actions that are do be executed in paralell.
        // One layer outside: all actions originating from ONE card, e.g MOVE 3.
        // Outermost layer: all the actions from all the cards.
        List<List<List<PlayerAction>>> allActions = new ArrayList<>();
        List<Action> laserAnimations = new ArrayList<>();
        List<List<PlayerAction>> conveyorActions = new ArrayList<>();

        for (List<PlayerAndProgramCard> onePhaseProgramCards : allProgramCards) {

            // Sorts all phase-cards.
            Collections.sort(onePhaseProgramCards);

            List<List<PlayerAction>> onePhaseActionsList = new ArrayList<>();

            for (PlayerAndProgramCard playerAndProgramCard : onePhaseProgramCards) {

                currentPlayer = playerAndProgramCard.getPlayer();
                ProgramCard card = playerAndProgramCard.getPgCard();

                // All the actions originating from ONE card.
                List<List<PlayerAction>> cardActions = checker2.doCard(card, currentPlayer);

                onePhaseActionsList.addAll(cardActions);

                checker2.checkForFlag(players);

            }
            allActions.add(onePhaseActionsList);

            // Conveyors actions for one round, added
            conveyorActions.add(checker2.doPiecesMoves(players));

            SequenceAction laserAnimation = this.guiScreen.getGUIBoard().getLaserAnimations(this.laserCalculator.laserCalculation());
            laserAnimations.add(laserAnimation);



        }
        this.guiScreen.getGUIBoard().doGUIActions(allActions, laserAnimations, conveyorActions, getPostExecutionAction());
    }

    private RunnableAction getPostExecutionAction() {

        return new RunnableAction() {
            @Override
            public void run() {

                guiScreen.updateStats(currentPlayer);

                //List<Player> deadPlayers = board.getDeadPlayers();
                for (Player currPlayer : players) {
                    if(currPlayer.isDead()){
                        currPlayer.setAlive();
                        guiScreen.getGUIBoard().respawnPlayer(currPlayer);
                    }
                }

                Player possiblyWinningPlayer = checker2.someoneHasWon(players);
                if(possiblyWinningPlayer != null){
                    gameOver(possiblyWinningPlayer);
                }

                cardPicker.startCardPicking();
            }
        };
    }

    private void gameOver(Player player) {
        this.guiScreen.gameOver(player);
    }


    public List<Player> getPlayers() {
        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }


    public CardPicker getCardPicker() {
        return this.cardPicker;
    }

    public void pvpGenerator() {
        for (int i = 0; i < totalPlayers; i++) {
            Position position = new Position(0,0);
            for(SpawnPlatform spawnPlatform : spawnPlatforms){
                if(spawnPlatform.getPlatformNumber() == (i+1)){
                    position = spawnPlatform.getPosition();
                    break;
                }
            }
            players.add(new Player(Integer.toString(i+1), position, Direction.NORTH, startHealth, false));
            board.addPiece(position, players.get(i));
        }
    }

    public void pveGenerator() {
        for (int i = 0; i < totalPlayers; i++) {

            if (i == 0) {
                Position position = new Position(0,0);
                for(SpawnPlatform spawnPlatform : spawnPlatforms){
                    if(spawnPlatform.getPlatformNumber() == (i+1)){
                        position = spawnPlatform.getPosition();
                        break;
                    }
                }
                players.add(new Player(Integer.toString(i+1), position, Direction.NORTH, startHealth, false));
                board.addPiece(position, players.get(i));
            }

            else {
                Position position = new Position(0,0);
                for(SpawnPlatform spawnPlatform : spawnPlatforms){
                    if(spawnPlatform.getPlatformNumber() == (i+1)){
                        position = spawnPlatform.getPosition();
                        break;
                    }
                }
                players.add(new Player(Integer.toString(i+1), position, Direction.NORTH, startHealth, true));
                board.addPiece(position, players.get(i));
            }
        }
    }


}
