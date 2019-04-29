package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.SnapshotArray;
import inf112.skeleton.app.GUI.pieces.GUIPiece;

public class Tile extends Stack {


    public Tile () {

        this.add(new BaseTile());
    }

    public void addPiece(GUIPiece GUIPiece) {
        // REMOVE
        this.add(GUIPiece);
    }

    public void removePiece(GUIPiece GUIPiece){
        this.removeActor(GUIPiece);
    }

    public void makeTileInvisible(){
        for(Actor child : this.getChildren()){
            child.setVisible(false);
        }
    }
}
