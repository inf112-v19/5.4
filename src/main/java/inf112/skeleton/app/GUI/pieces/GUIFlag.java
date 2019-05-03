package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GUIFlag extends GUIPiece {
    TextureAtlas textureAtlas;


    public GUIFlag(int number) {

        sprite = new Sprite(new Texture(Gdx.files.internal("board/flag_1.png")));

        this.textureAtlas = new TextureAtlas("board/flags.txt");

        sprite.setOrigin(getWidth() / 2, getHeight() / 2);

        this.changeSprite(number);
    }

    public void changeSprite(int number) {
        String spriteString = "";

        switch (number) {
            case 1:
                spriteString += "flag_1_16bit";
                break;
            case 2:
                spriteString += "flag_2_16bit";
                break;
            case 3:
                spriteString += "flag_3_16bit";
                break;
            case 4:
                spriteString += "flag_4_16bit";
                break;
            case 5:
                spriteString += "flag_5_16bit";
                break;
            case 6:
                spriteString += "flag_6_16bit";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }
}
