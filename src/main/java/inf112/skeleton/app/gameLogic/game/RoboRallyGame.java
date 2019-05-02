package inf112.skeleton.app.gameLogic.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import inf112.skeleton.app.GUI.MainGameScreen;
import inf112.skeleton.app.GUI.board.GUIBoard;
import inf112.skeleton.app.GUI.player.Position;
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
    private String boardPath = "FlagBoard.json";
    private List<LaserShooter> laserShooterList;

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Board board;

    private Checker checker;

    private PlayerActionWrapper playerActionQueue;

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.playerActionQueue = new PlayerActionWrapper();
        this.laserShooterList = new ArrayList<>();

        this.guiScreen = guiScreen;
        //Testing with FlagBoard
        //this.board = new Board("Captain Hook", "DankBoard.json");
        this.board = new Board("Captain Hook", boardPath);
        this.checker = new Checker(board);
        //board.displayBoard();
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new ArrayList<>();
        for (int i = 0; i < totalPlayers; i++) {
            Position position = new Position(i+5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue
            players.add(new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue));
            board.addPiece(position, players.get(i));
            System.out.println("player made!!");
        }

        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                for (IPiece piece : board.getCellAt(x, y).getPiecesInCell()) {
                    if (piece instanceof LaserShooter) {
                        laserShooterList.add((LaserShooter) piece);
                        System.out.println("Added lasershooter in position: " + x + ", " + y);
                        break;
                    }
                }
            }
        }
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

        for (List<ProgramCard> onePhaseProgramCards : allProgramCards) {

            // Sorts all phase-cards.
            Collections.sort(onePhaseProgramCards);

            for (ProgramCard card : onePhaseProgramCards) {

                // All the actions originating from ONE card.
                List<List<PlayerAction>> cardActions = checker.doAction(card.getCardType().getAction(), currentPlayer);

                System.out.println("Actions in actionList: ");
                for (List<PlayerAction> tempBig : cardActions) {
                    System.out.println("----------");
                    for (PlayerAction pa : tempBig) {
                        System.out.println("Player: " + pa.getPlayer().getName() + " Action: " + pa.getAction().getDescription());
                    }

                }


                System.out.println("HEIJEG ER HER NÅÅÅ");
                allActions.add(cardActions);

                // Coneyors lol
                //allActions.add(checker.doPiecesMoves(players));


                // DO LASERSHOOTING AND CONVEYOR MOVING HERE


                //allActions.add(new ArrayList<List<SequenceAction>>(){{
                //new ArrayList<SequenceAction>(){{add(laserAnimation);}};
                //}});


            }

            System.out.println("AAAAAAAAAAAAAAH");
            System.out.println(laserAnimations);
            SequenceAction laserAnimation = this.guiScreen.getGUIBoard().getLaserAnimations(this.laserCalculation());
            laserAnimations.add(laserAnimation);

        }
        this.guiScreen.getGUIBoard().doGUIActions(allActions, laserAnimations);

    }

    public List<Laser> laserCalculation() {
        List<Laser> lasers = new ArrayList<>();
        for (LaserShooter laserShooter : laserShooterList) {
//            board.getCellAt(laserShooter.getPos()).addPiece(new Laser(laserShooter.getPieceDirection(), laserShooter));
            placeLaser(new Position(laserShooter.getPos().getX(), laserShooter.getPos().getY()), laserShooter.getPieceDirection(), laserShooter, lasers);

        }
        for (Player player : players) {
            switch (player.getDirection()) {
                case WEST:
                    placeLaser(new Position(player.getPos().getX() - 1, player.getPos().getY()), player.getDirection(), player.getLaserShooter(), lasers);
                    break;
                case SOUTH:
                    placeLaser(new Position(player.getPos().getX(), player.getPos().getY() + 1), player.getDirection(), player.getLaserShooter(), lasers);
                    break;
                case EAST:
                    placeLaser(new Position(player.getPos().getX() + 1, player.getPos().getY()), player.getDirection(), player.getLaserShooter(), lasers);
                    break;
                case NORTH:
                    placeLaser(new Position(player.getPos().getX(), player.getPos().getY() - 1), player.getDirection(), player.getLaserShooter(), lasers);
                    break;
            }

        }
        System.out.println("Number of lasers placed: " + lasers.size());

        // Add lasers to GUI
        return lasers;
    }


    /**
     *
     * @param pos, the position to place a laser
     * @param dir, the direction
     * @param laserShooter
     */
    public void placeLaser(Position pos, Direction dir, LaserShooter laserShooter, List<Laser> laserPositions) {
        if (pos.getX() >= board.getBoardWidth() || pos.getY() >= board.getBoardHeight() || pos.getY() < 0 || pos.getX() < 0) {
            System.out.println("Laser out of board");
            return;
        }
        for (IPiece piece : board.getCellAt(pos).getPiecesInCell()) {
            if (piece instanceof Player) {
                System.out.println("Player health before being shot: " + ((Player) piece).getDamageTokens());
                ((Player) piece).takeDamage(laserShooter.getDamage());
                System.out.println("Hit a player!");
                System.out.println("Player health after being shot: " + ((Player) piece).getDamageTokens());
                return;
            }
            if (piece instanceof Wall) {
                if (piece.getPieceDirection() == dir) {
                    System.out.println("Placed laser in position: " + pos.getX() + ", " + pos.getY());
                    System.out.println("Laser hit wall in position: " + pos.getX() + ", " + pos.getY());
//                    board.getCellAt(pos).addPiece(new Laser(dir, laserShooter));
                    laserPositions.add(laserShooter.createNewLaser(pos));
                    return;
                }
            }
        }
        System.out.println("Placed laser in position: " + pos.getX() + ", " + pos.getY());
//        board.getCellAt(pos).addPiece(new Laser(dir, laserShooter));
        laserPositions.add(laserShooter.createNewLaser(pos));
        switch (dir) {
            case NORTH:
                placeLaser(new Position(pos.getX(), pos.getY() - 1), dir, laserShooter, laserPositions);
                break;
            case EAST:
                placeLaser(new Position(pos.getX() + 1, pos.getY()), dir, laserShooter, laserPositions);
                break;
            case SOUTH:
                placeLaser(new Position(pos.getX(), pos.getY() + 1), dir, laserShooter, laserPositions);
                break;
            case WEST:
                placeLaser(new Position(pos.getX() - 1, pos.getY()), dir, laserShooter, laserPositions);
                break;
        }
    }

    public void removeLasers() {
        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                for (IPiece piece : board.getCellAt(x, y).getPiecesInCell()) {
                    if (piece instanceof Laser) {
                        board.getCellAt(x, y).getPiecesInCell().remove(piece);
                    }
                }
            }
        }
    }

    public List<Player> getPlayers(){
        return this.players;
    }
    public Board getBoard(){return this.board;}


}
