package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;

public class GUIWall extends Piece {

    public GUIWall(Wall wall) {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/wall_tile.png")));
        switch (wall.getRotation()) {
            case WEST:

        }


    }

}
