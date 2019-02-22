package inf112.skeleton.app.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GUI.pieces.Piece;
import inf112.skeleton.app.GUI.pieces.Tile;


public class Board extends Table {


    // How many X and Y tiles are generated.
    int xGridSize;
    int yGridSize;

    // Size of each square (currently even, i.e unitSize 64 means 64x64 px).
    float unitSize;


    Tile[][] boardMap;

    public Board(float unitSize, int xGridSize, int yGridSize){

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
        System.out.println("Added piece!");
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
