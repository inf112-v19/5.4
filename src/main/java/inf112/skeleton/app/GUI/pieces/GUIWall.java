package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameLogic.board.pieces.Wall;

public class GUIWall extends Piece {

    public GUIWall(Wall wall) {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/wall.png")));
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
