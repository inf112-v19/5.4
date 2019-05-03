package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class GUIConveyor extends GUIPiece {

    TextureAtlas textureAtlas;


    public GUIConveyor(Direction dir, int speed) {

        sprite = new Sprite(new Texture(Gdx.files.internal("board/conveyor.png")));
        this.textureAtlas = new TextureAtlas("board/conveyors.txt");

        sprite.setOrigin(getWidth() / 2, getHeight() / 2);

        this.changeSprite(speed);

        switch (dir) {
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
    public void changeSprite(int speed) {
        String spriteString = "";

        switch (speed) {
            case 1:
                spriteString += "conveyor";
                break;
            case 2:
                spriteString += "doubleConveyor";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }
}
