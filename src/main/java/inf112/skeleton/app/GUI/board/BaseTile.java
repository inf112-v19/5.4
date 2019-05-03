package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.GUI.pieces.GUIPiece;

class BaseTile extends GUIPiece {

    public BaseTile() {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/base_tile.png")));
    }
}