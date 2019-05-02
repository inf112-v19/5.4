package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GUIHole extends GUIPiece {

    public GUIHole() {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/hole.png")));
    }
}
