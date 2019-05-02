package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GUIRepair extends Piece {

    public GUIRepair() {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/repair_tile.png")));
    }
}
