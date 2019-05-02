package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.gameLogic.board.pieces.Laser;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class LaserGUI extends GUIPiece {

    TextureAtlas textureAtlas;

    public LaserGUI(Laser laser) {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/firstLaserTestImage.png")));
        this.textureAtlas = new TextureAtlas("board/lasers.txt");

        sprite.setOrigin(getWidth() / 2, getHeight() / 2);

        this.changeSprite(laser.getDamage());

        switch (laser.getPieceDirection()) {
            case NORTH:
                this.setRotation(90f);
                break;
            case SOUTH:
                this.setRotation(90f);
                break;
            default:
                break;
        }


    }

    public void changeSprite(int damage) {
        String spriteString = "";

        switch (damage) {
            case 1:
                spriteString += "firstLaserTest";
                break;
            case 2:
                spriteString += "doubleLaser";
                break;
            case 3:
                spriteString += "trippleLaser";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }

}