package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GUISpawnPlatform extends GUIPiece {
    TextureAtlas textureAtlas;

    public GUISpawnPlatform(int platformNumber) {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/firstLaserTestImage.png")));
        this.textureAtlas = new TextureAtlas("board/spawnPlatforms.txt");

        sprite.setOrigin(getWidth() / 2, getHeight() / 2);

        this.changeSprite(platformNumber);


    }

    public void changeSprite(int platformNumber) {
        String spriteString = "";

        switch (platformNumber) {
            case 1:
                spriteString += "spawnPlatform1";
                break;
            case 2:
                spriteString += "spawnPlatform2";
                break;
            case 3:
                spriteString += "spawnPlatform3";
                break;
            case 4:
                spriteString += "spawnPlatform4";
                break;
            case 5:
                spriteString += "spawnPlatform5";
                break;
            case 6:
                spriteString += "spawnPlatform6";
                break;
            case 7:
                spriteString += "spawnPlatform7";
                break;
            case 8:
                spriteString += "spawnPlatform8";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }

}
