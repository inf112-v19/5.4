package inf112.skeleton.app.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GUI.Pieces.Piece;
import inf112.skeleton.app.GUI.Pieces.Tile;


public class Board extends Table {

    float size;
    int xGridSize;
    int yGridSize;
    float unitSize;
    Tile[][] boardMap;

    public Board(float unitSize, int xGridSize, int yGridSize){

        this.size = unitSize;
        this.xGridSize = xGridSize;
        this.yGridSize = yGridSize;
        this.unitSize = unitSize;

        boardMap = new Tile[yGridSize][xGridSize];

        // Updates boardMap.
        for (int y = 0; y < yGridSize; y++) {
            for (int x = 0; x < xGridSize; x++) {
                boardMap[y][x] = new Tile();
            }
        }

        updateBoard();


    }

    public void addPiece(int x, int y, Piece piece){
        this.boardMap[y][x].addPiece(piece);
    }

    // Updates the actual table based on boardMap.
    public void updateBoard(){

        // Clears previous values.
        this.clearChildren();

        // FIlls in all values.
        for (int y = 0; y < yGridSize; y++) {
            for (int x = 0; x < xGridSize; x++) {
                //this.add(new Tile()).size(unitSize);
                this.add(boardMap[y][x]).size(unitSize);
            }
            this.row();
        }
    }

}
