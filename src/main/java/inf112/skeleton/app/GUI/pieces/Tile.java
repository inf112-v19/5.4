package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

import java.util.Iterator;
import java.util.LinkedList;

public class Tile extends Stack {
    private LinkedList<Piece> tilePieces;

    public Tile () {
        this.tilePieces = new LinkedList<>();

        this.updateTile();

        //this.add(new BaseTile());
        //this.add(new Robot());
    }

    /**
     * Adds a piece to linked list
     * @param piece the piece that is added to the linked list
     */
    public void addPiece(Piece piece) {
        tilePieces.add(piece);
        updateTile();

    }


    public void updateTile(){

        class BaseTile extends Actor {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal("board/normal_tile.png")));
            //Sprite sprite = new Sprite(new Texture(Gdx.files.internal("bots/rolobot-alpha/rolobot-alpha.png")));


            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(sprite, getX(),getY(), getWidth(), getHeight());
            }
        }

        this.clearChildren();
        this.add(new BaseTile());
        for(Piece iterPiece : tilePieces){
            this.add(iterPiece);
        }

    }

    /**
     * @return
     *      an iterator over the pieces in the linked list
     */
    public Iterator<Piece> getPieces() {
        return tilePieces.iterator();
    }
}
