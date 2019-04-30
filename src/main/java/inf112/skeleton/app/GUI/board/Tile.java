package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import inf112.skeleton.app.GUI.pieces.GUIPiece;

public class Tile extends Stack {


    public Tile() {

        //this.guiPieces = new ArrayList<>();
        BaseTile baseTile = new BaseTile();
        this.addPiece(baseTile);
    }

    public void addPiece(GUIPiece guiPiece) {
        //guiPieces.add(guiPiece);
        this.add(guiPiece);
    }

    public void removePiece(GUIPiece GUIPiece) {

        //guiPieces.remove(GUIPiece);
        this.removeActor(GUIPiece);
    }

    public void makeTileInvisible() {
        for (Actor child : this.getChildren()) {
            child.setVisible(false);
        }
    }

    public void lightUpTile() {
        for (Actor child : this.getChildren()) {
            child.setColor(Color.ORANGE);
            System.out.println(child + " has been colored!");
        }

    }

    public void resetTileColor() {
        for (Actor child : this.getChildren()) {
            child.setColor(Color.WHITE);
        }
    }
}
