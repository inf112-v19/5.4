package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.GUI.player.Position;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.pieces.IPiece;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.PlayerAction;
import javafx.geometry.Pos;

import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class GUIBoard extends Table {


    // How many X and Y tiles are generated.
    int xGridSize;
    int yGridSize;
    int boardHeight;

    // Size of each square (currently even, i.e unitSize 64 means 64x64 px).
    float unitSize;

    Tile[][] boardMap;
    Board gamelogicBoard;

    // Currently the height of the board should always be 900px.

    public GUIBoard(int xGridSize, int yGridSize) {

        this.boardHeight = 900;

        this.xGridSize = xGridSize;
        this.yGridSize = yGridSize;


        // Probably a stupid way to do it.
        this.unitSize = boardHeight / yGridSize;
        //this.unitSize = 900/xGridSize;

        boardMap = new Tile[yGridSize][xGridSize];

        // Updates boardMap.
        for (int y = 0; y < yGridSize; y++) {
            for (int x = 0; x < xGridSize; x++) {
                boardMap[y][x] = new Tile();
            }
        }

        updateBoard();
    }

    /**
     * This constructor makes a GUIBoard based on a game logic Board.
     *
     * @param board
     */
    public GUIBoard(Board board) {

        this(board.getBoardHeight(), board.getBoardWidth());
        this.gamelogicBoard = board;

        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();


        for(int y=0; y<boardHeight; y++){
            for(int x = 0; x<boardWidth; x++ ){
                ICell currCell = board.getCellAt(x, y);
                if (currCell != null) {
                    //ICell currCell = board.getCellAt(2, 0);
                    List<IPiece> pieces = currCell.getPiecesInCell();
                    for (IPiece currPiece : pieces) {
                        //does not add to guiPlayer to avoid duplicates
                        if (currPiece instanceof Player) {
                            continue;
                        }
                        // Finds the appropriate GUIPiece for the board piece.
                        this.addGUIPiece(x, y, currPiece.getGUIPiece());
                    }
                }

            }

        }
    }

    /**
     * This function takes the specific Tile in the table, adds the GUIPiece to the Tile,
     * then re-adds the Tile to the Table.
     *
     * @param x
     * @param y
     * @param GUIPiece
     */
    public void addGUIPiece(int x, int y, GUIPiece GUIPiece) {
        Tile localTile = this.boardMap[y][x];
        //this.addActor(localTile.getX(), localTile.getY(), GUIPiece);
        localTile.addPiece(GUIPiece);
        Cell pieceCell = this.getCell(localTile);
        pieceCell.clearActor();
        pieceCell.setActor(localTile);

    }

    public void removeGUIPiece(int x, int y, GUIPiece GUIPiece) {
        this.boardMap[y][x].removePiece(GUIPiece);
    }


    // Updates the actual table based on boardMa=.
    public void updateBoard() {

        // Clears previous values.
        this.clearChildren();

        // FIlls in all values.
        for (int y = 0; y < yGridSize; y++) {
            for (int x = 0; x < xGridSize; x++) {
                this.add(boardMap[y][x]).size(unitSize);
            }
            this.row();
        }
    }

    /**
     * Only adds players at the correct graphical position.
     * Has override issues, move code to MainGameScreen.
     *
     * @param players
     */
    public void addPlayers(List<Player> players) {
        for (Player currPlayer : players) {
            System.out.println("Added guiplayer at " + currPlayer.getPos().toString());
            this.addGUIPiece(currPlayer.getPos().getX(), currPlayer.getPos().getY(), currPlayer.getRobot());
        }

        /*System.out.println("AAAAAAAAAAAAAAAAAAAAAH");
        System.out.println(this.getChildren());
        SnapshotArray<Actor> children = this.getChildren();
        for(Actor actor : children){
            System.out.println(actor);
        }*/
    }

    public void doGUIActions(List<List<List<PlayerAction>>> allPlayerActions, List<Action> laserAnimations,
                             List<List<PlayerAction>> conveyorActions, RunnableAction postExectionAction) {

        this.addAction(new AnimationController().getAllActionsSequenced(allPlayerActions,
                laserAnimations,conveyorActions, postExectionAction));
    }

    public float[] getPiecePos(int x, int y) {
//
//        float returnX = boardMap[y][x].getX();
//        float returnY = boardMap[y][x].getY();
        return new float[]{boardMap[y][x].getX(), boardMap[y][x].getY()};
    }

    public Vector2 getCoords(int x, int y) {
        return new Vector2(boardMap[y][x].getX(), boardMap[y][x].getY());
    }

    /* addAction(sequence(moveAction, new DelayAction(1), new RunnableAction() {
                    @Override
                    public void run() {
                        System.out.println("COMPLETE!");
                    }
                })); */

    public void makeBoardInvisible() {
        for (int y = 0; y < yGridSize; y++) {
            for (int x = 0; x < xGridSize; x++) {
                boardMap[y][x].makeTileInvisible();
            }
        }
    }

    public void lightUpTile(int x, int y) {
        boardMap[y][x].lightUpTile();
    }

    public void resetTileColor(int x, int y) {
        boardMap[y][x].resetTileColor();
    }

    public SequenceAction getLaserAnimations(List<Laser> lasers){

        DelayAction laserDelayAction = new DelayAction(2);
        DelayAction postDelayAction = new DelayAction(1);

        RunnableAction addLasersAnimation = new RunnableAction(){
            @Override
            public void run() {

                System.out.println(lasers.size());
                for(Laser laser : lasers){

                    GUIPiece guiPiece = laser.getGUIPiece();

                    Position currPos = laser.getPosition();
                    int x = currPos.getX();
                    int y = currPos.getY();
                    addGUIPiece(x, y, guiPiece);

                }

            }
        };

        RunnableAction removeLasersAnimation = new RunnableAction(){
            @Override
            public void run() {
                for(Laser laser : lasers){

                    GUIPiece guiPiece = laser.getGUIPiece();

                    int x = laser.getPosition().getX();
                    int y = laser.getPosition().getY();
                    removeGUIPiece(x, y, guiPiece);

                }
            }
        };

        return new SequenceAction(addLasersAnimation, laserDelayAction, removeLasersAnimation, postDelayAction);
    }


    public void respawnPlayer(Player player) {

        Position respawnPoint = player.getRespawnPoint().getPos();

        this.gamelogicBoard.changePlayerPos(player, respawnPoint);

        int playerX = player.getPos().getX();
        int playerY = player.getPos().getY();
        int rpx = respawnPoint.getX();
        int rpy = respawnPoint.getY();

        GUIPiece guiPiece = player.getRobot();
        this.removeGUIPiece(playerX,playerY,guiPiece);
        this.addGUIPiece(rpx,rpy,guiPiece);

    }
}
