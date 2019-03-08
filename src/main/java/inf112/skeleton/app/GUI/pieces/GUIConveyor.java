package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameLogic.board.pieces.Conveyor;

public class GUIConveyor extends Piece {

    public GUIConveyor(Conveyor conveyor) {

        sprite = new Sprite(new Texture(Gdx.files.internal("board/conveyor.png")));
        sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        switch (conveyor.getRotation()) {
            case WEST:
                this.setRotation(90f);
                break;
            case SOUTH:
                this.setRotation(180f);
                break;
            case EAST:
                this.setRotation(-90);
                break;
        }
    }
}
