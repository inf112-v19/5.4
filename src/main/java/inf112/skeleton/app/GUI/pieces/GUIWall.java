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
        sprite.setOrigin(getWidth()/2, getHeight()/2);
        switch (wall.getRotation()) {
            case WEST:
                this.setRotation(90f); break;
            case SOUTH:
                this.setRotation(180f); break;
            case EAST:
                this.setRotation(-90); break;
        }


    }

}
