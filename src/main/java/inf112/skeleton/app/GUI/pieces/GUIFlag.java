package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GUIFlag extends Piece {

    public GUIFlag() {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/number_1_flag.png")));
    }
}
