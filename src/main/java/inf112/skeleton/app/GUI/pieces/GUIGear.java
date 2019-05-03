package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameLogic.enums.Rotation;

public class GUIGear extends GUIPiece {

    public GUIGear(Rotation rotation) {
        switch (rotation) {
            case L:
                sprite = new Sprite(new Texture(Gdx.files.internal("board/left_gear.png")));
                break;
            case R:
                sprite = new Sprite(new Texture(Gdx.files.internal("board/right_gea.png")));

        }
    }
}
