package inf112.skeleton.app.GUI.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

import java.util.Iterator;
import java.util.LinkedList;

public class Tile extends Stack {
    private LinkedList<Piece> linkedList;

    public Tile () {
        this.linkedList = new LinkedList<>();

        class BaseTile extends Actor {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal("board/normal_tile.png")));

            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(sprite, getX(),getY(), getWidth(), getHeight());
            }
        }

        this.add(new BaseTile());
        //this.add(new Robot());
    }

    /**
     * Adds a piece to linked list
     * @param piece the piece that is added to the linked list
     */
    public void addPiece(Piece piece) {
        linkedList.add(piece);
    }

    /**
     * @return
     *      an iterator over the pieces in the linked list
     */
    public Iterator<Piece> getPieces() {
        return linkedList.iterator();
    }
}
