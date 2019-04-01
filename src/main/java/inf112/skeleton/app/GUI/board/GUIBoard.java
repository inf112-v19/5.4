package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GUI.pieces.GUIPiece;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.board.Board;
import inf112.skeleton.app.gameLogic.board.ICell;
import inf112.skeleton.app.gameLogic.board.IPiece;

import java.util.List;


public class GUIBoard extends Table {


    // How many X and Y tiles are generated.
    int xGridSize;
    int yGridSize;
    int boardHeight;

    // Size of each square (currently even, i.e unitSize 64 means 64x64 px).
    float unitSize;

    Tile[][] boardMap;


    // Currently the height of the board should always be 900px.

    public GUIBoard(int xGridSize, int yGridSize){

        this.boardHeight = 900;

        this.xGridSize = xGridSize;
        this.yGridSize = yGridSize;


        // Probably a stupid way to do it.
        this.unitSize = boardHeight/yGridSize;
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
     * @param board
     */
    public GUIBoard(Board board){

        this(board.getBoardHeight(), board.getBoardWidth());

        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();



        for(int y=0; y<boardHeight; y++){
            for(int x = 0; x<boardWidth; x++ ){
                System.out.println(x + " --- " + y);
                ICell currCell = board.getCellAt(x, y);
                if(currCell != null){
                    //ICell currCell = board.getCellAt(2, 0);
                    List<IPiece> pieces = currCell.getPiecesInCell();
                    for(IPiece currPiece : pieces){
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
     * @param x
     * @param y
     * @param GUIPiece
     */
    public void addGUIPiece(int x, int y, GUIPiece GUIPiece){
        Tile localTile = this.boardMap[y][x];
        localTile.addPiece(GUIPiece);
        Cell pieceCell = this.getCell(localTile);
        pieceCell.clearActor();
        pieceCell.setActor(localTile);

    }

    public void removePiece(int x, int y, GUIPiece GUIPiece){
        this.boardMap[y][x].removePiece(GUIPiece);
    }


    // Updates the actual table based on boardMa=.
    public void updateBoard(){

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

    public void addPlayers(Player[] players){
        for(Player currPlayer : players){
            System.out.println("Added guiplayer at " + currPlayer.getPos().toString());
            this.addGUIPiece(currPlayer.getPos().getX(),currPlayer.getPos().getY(), currPlayer.getRobot());
        }
    }

}
