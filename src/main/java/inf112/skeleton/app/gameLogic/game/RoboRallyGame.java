package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.MainGameScreen;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoboRallyGame {

    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers = 3;   // Total players in the game
    private List<Player> players;       // Players in the game
    private int startHealth = 3;
    private String boardPath = "FlagBoard.json";

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Board board;
    private List<LaserShooter> laserShooterList;

    private Checker checker;

    private PlayerActionWrapper playerActionQueue;

    public RoboRallyGame(MainGameScreen guiScreen) {
        this.playerActionQueue = new PlayerActionWrapper();
        this.laserShooterList = new ArrayList<>();

        this.guiScreen = guiScreen;

        this.board = new Board("Captain Hook", boardPath);
        this.checker = new Checker(board);
        this.deck = new ProgramCardDeck();  // Deck of cards in the game
        players = new ArrayList<Player>();
        for (int i = 0; i < totalPlayers; i++) {
            Position position = new Position(i + 5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue
            players.add(new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue));
            board.addPiece(position, players.get(i));
            System.out.println("player made!!");
            System.out.println(players.get(i).getPos().getX() + " " + players.get(i).getPos().getY());
        }
        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                for (IPiece piece : board.getCellAt(x, y).getPiecesInCell()) {
                    if (piece instanceof LaserShooter) {
                        laserShooterList.add( (LaserShooter) piece);
                        System.out.println("Added lasershooter in position: " + x + ", " + y);
                        break;
                    }
                }
            }
        }
        playGame();
    }

    public void playGame() {
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

                // Coneyors lol
                allActions.add(checker.doPiecesMoves(players));


                // DO LASERSHOOTING AND CONVEYOR MOVING HERE

            }

        }


        this.guiScreen.getGUIBoard().doGUIActions(allActions);
//        removeLasers();
        this.laserCalculation();

    }

    public void laserCalculation(){
        for (LaserShooter laserShooter : laserShooterList) {
            board.getCellAt(laserShooter.getPos()).addPiece(new Laser(laserShooter.getPieceDirection(), laserShooter));
            System.out.println("Placed laser in position: " + laserShooter.getPos().getX() + ", " + laserShooter.getPos().getY());
            switch (laserShooter.getPieceDirection()) {
                case WEST:
                    placeLaser(new Position(laserShooter.getPos().getX()-1, laserShooter.getPos().getY()), laserShooter.getPieceDirection(), laserShooter);
                    break;
                case SOUTH:
                    placeLaser(new Position(laserShooter.getPos().getX(), laserShooter.getPos().getY()+1), laserShooter.getPieceDirection(), laserShooter);
                    break;
                case EAST:
                    placeLaser(new Position(laserShooter.getPos().getX()+1, laserShooter.getPos().getY()), laserShooter.getPieceDirection(), laserShooter);
                    break;
                case NORTH:
                    placeLaser(new Position(laserShooter.getPos().getX(), laserShooter.getPos().getY()-1), laserShooter.getPieceDirection(), laserShooter);
                    break;
            }
        }
        for (Player player : players) {
            switch (player.getDirection()) {
                case WEST:
                    placeLaser(new Position(player.getPos().getX()-1, player.getPos().getY()), player.getDirection(), player.getLaserShooter());
                    break;
                case SOUTH:
                    placeLaser(new Position(player.getPos().getX(), player.getPos().getY()+1), player.getDirection(), player.getLaserShooter());
                    break;
                case EAST:
                    placeLaser(new Position(player.getPos().getX()+1, player.getPos().getY()), player.getDirection(), player.getLaserShooter());
                    break;
                case NORTH:
                    placeLaser(new Position(player.getPos().getX(), player.getPos().getY()-1), player.getDirection(), player.getLaserShooter());
                    break;
            }

        }
    }

    public void placeLaser(Position pos, Direction dir, LaserShooter laserShooter) {
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
                if (piece.getPieceDirection().oppositeDir() == dir) {
                    System.out.println("Laser hit wall in position: " + pos.getX() + ", " + pos.getY());

                    return;
                }
                if (piece.getPieceDirection() == dir) {
                    System.out.println("Placed laser in position: " + pos.getX() + ", " + pos.getY());
                    System.out.println("Laser hit wall in position: " + pos.getX() + ", " + pos.getY());
                    board.getCellAt(pos).addPiece(new Laser(dir, laserShooter));
                    return;
                }
            }
        }
        System.out.println("Placed laser in position: " + pos.getX() + ", " + pos.getY());
        board.getCellAt(pos).addPiece(new Laser(dir, laserShooter));
        switch (dir) {
            case NORTH: placeLaser(new Position(pos.getX(), pos.getY()-1), dir, laserShooter); break;
            case EAST: placeLaser(new Position(pos.getX()+1, pos.getY()), dir, laserShooter); break;
            case SOUTH: placeLaser(new Position(pos.getX(), pos.getY()+1), dir, laserShooter); break;
            case WEST: placeLaser(new Position(pos.getX()-1, pos.getY()), dir, laserShooter); break;
        }
    }

    public void removeLasers(){
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

    public List<Player> getPlayers() {
        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }


}
