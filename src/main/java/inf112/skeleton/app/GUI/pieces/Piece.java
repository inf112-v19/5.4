package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {

    Sprite sprite;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        System.out.println("Piece Draw");
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }
}
