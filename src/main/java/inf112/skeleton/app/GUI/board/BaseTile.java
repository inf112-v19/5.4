package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

class BaseTile extends Actor {
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("board/base_tile.png")));

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(),getY(), getWidth(), getHeight());
    }
}