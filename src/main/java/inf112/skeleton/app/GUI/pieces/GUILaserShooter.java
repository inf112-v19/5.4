package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.gameLogic.board.pieces.LaserShooter;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class GUILaserShooter extends GUIPiece {

    TextureAtlas textureAtlas;


    public GUILaserShooter(LaserShooter laserShooter) {

        sprite = new Sprite(new Texture(Gdx.files.internal("board/laserShooter.png")));
        this.textureAtlas = new TextureAtlas("board/laserShooters.txt");
        sprite.setOrigin(getWidth() / 2, getHeight() / 2);

        this.changeSprite(laserShooter.getDamage());


        switch (laserShooter.getPieceDirection()) {
            case WEST:
                this.setRotation(-90f);
                break;
            case NORTH:
                this.setRotation(180f);
                break;
            case EAST:
                this.setRotation(90f);
                break;
        }
    }
    public void changeSprite(int damage) {
        String spriteString = "";

        switch (damage) {
            case 1:
                spriteString += "laserShooter";
                break;
            case 2:
                spriteString += "doubleLaserShooter";
                break;
            case 3:
                spriteString += "trippleLaserShooter";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }
}
