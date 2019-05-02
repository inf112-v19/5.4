package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.SnapshotArray;
import inf112.skeleton.app.GUI.pieces.GUIPiece;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Stack {


    public Tile () {

        //this.guiPieces = new ArrayList<>();
        BaseTile baseTile = new BaseTile();
        this.addPiece(baseTile);
    }

    public void addPiece(GUIPiece guiPiece) {
        //guiPieces.add(guiPiece);
        this.add(guiPiece);
    }

    public void removePiece(GUIPiece GUIPiece){

        //guiPieces.remove(GUIPiece);
        this.removeActor(GUIPiece);
    }

    public void makeTileInvisible(){
        for(Actor child : this.getChildren()){
            child.setVisible(false);
        }
    }

    public void lightUpTile(){
        for(Actor child : this.getChildren()){
            child.setColor(Color.ORANGE);
            System.out.println(child + " has been colored!");
        }

    }

    public void resetTileColor() {
        for(Actor child : this.getChildren()){
            child.setColor(Color.WHITE);
        }
    }
}
