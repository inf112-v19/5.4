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

    List<GUIPiece> guiPieces;

    public Tile () {

        this.guiPieces = new ArrayList<>();
        BaseTile baseTile = new BaseTile();
        this.addPiece(baseTile);
    }

    public void addPiece(GUIPiece guiPiece) {
        guiPieces.add(guiPiece);
        this.add(guiPiece);
    }

    public void removePiece(GUIPiece GUIPiece){

        guiPieces.remove(GUIPiece);
        this.removeActor(GUIPiece);
    }

    public void makeTileInvisible(){
        for(Actor child : this.getChildren()){
            child.setVisible(false);
        }
    }

    public void lightUpTile(){
        for(Actor child : this.getChildren()){
            //child.setColor(0,1,0,1);
            //child.setColor(new Color(0,1,0,1));
        }

        for(GUIPiece guiPiece : guiPieces){
            guiPiece.getSprite().setColor(Color.YELLOW);
            guiPiece.getSprite().setColor(0,1,0,1);
            guiPiece.getSprite().setColor(new Color(0,1,0,1));
            //guiPiece.getColor().set(0,1,0,1);
        }
    }
}
