package inf112.skeleton.app.GUI.pieces;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseAsset extends GUIPiece {

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }

    public BaseAsset() {


    }

}
