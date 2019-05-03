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
import java.util.Stack;

public class RoboRallyGame {

    // The GUI.
    MainGameScreen guiScreen;

    private int totalPlayers = 3;   // Total players in the game
    private List<Player> players;       // Players in the game
    private int startHealth = 3;
    private String boardPath = "FlagBoard.json";

    private ProgramCardDeck deck;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private Player player3;
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

        // loop for generating players and assigning player 1,2 and 3.
        for (int i = 0; i < totalPlayers; i++) {
            if (i == 0){
                Position position = new Position(i + 5, 7);
                player1 = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue);
                players.add(player1);
                board.addPiece(position, player1);
                System.out.println(player1.getPos().getX() + " " + player1.getPos().getY());
            }
            if (i == 1) {
                Position position = new Position(i + 5, 7);
                player2 = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue);
                players.add(player2);
                board.addPiece(position, player2);
                System.out.println(player2.getPos().getX() + " " + player2.getPos().getY());
            }
            if (i == 2) {
                Position position = new Position(i + 5, 7);
                player3 = new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue);
                players.add(player3);
                board.addPiece(position, player3);
                System.out.println(player3.getPos().getX() + " " + player3.getPos().getY());
            }
        }
        /**
        for (int i = 0; i < totalPlayers; i++) {
            Position position = new Position(i + 5, 7);
            //String name, Position pos, Direction dir, int health, Board board, Queue<PlayerAction> playerActionQueue

            players.add(new Player(Integer.toString(i), position, Direction.SOUTH, startHealth, playerActionQueue));
            board.addPiece(position, players.get(i));
            System.out.println("player made!!");
            System.out.println(players.get(i).getPos().getX() + " " + players.get(i).getPos().getY());
        }
         */

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

        // TODO take cards from deck and assign them to the players
        //this.currentPlayer =currentPlayer;
        for (int i = 0; i < totalPlayers; i++) {
            List<ProgramCard> cards = deck.drawXCards(cardsToDraw);
            Stack<ProgramCard> playerCards = null;
            for (ProgramCard p : cards) {
                playerCards.push(p);
            }
            this.currentPlayer.setPlayerDeck(playerCards);
            this.guiScreen.pickCardPhase(cards);
            nextPlayer();
        }
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
        /**
        server.addReadyPlayer();
        server.readyPlayers += 1;

        if(readyPlayers == 4){
            server.executeCards();
        }
         */

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

    /**
     * Method for switching players.
     */
    public void nextPlayer() {
        if (currentPlayer==player1) {
            this.currentPlayer = this.player2;
        }
        if (currentPlayer==player2) {
            this.currentPlayer = this.player3;
        }
        if (currentPlayer==player3) {
            this.currentPlayer = this.player1;
        }
    }
}
