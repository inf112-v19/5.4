package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import inf112.skeleton.app.GUI.pieces.Piece;

public class Tile extends Stack {


    public Tile () {

        class BaseTile extends Actor {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal("board/base_tile.png")));

            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(sprite, getX(),getY(), getWidth(), getHeight());
            }
        }

        this.add(new BaseTile());
        //this.add(new Robot());
    }

    public void addPiece(Piece piece) {
        // REMOVE
        this.add(piece);
    }

    public void removePiece(Piece piece){
        this.removeActor(piece);
    }
}
