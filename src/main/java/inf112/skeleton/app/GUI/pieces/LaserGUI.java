package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class LaserGUI extends GUIPiece {

    public LaserGUI(Direction dir) {
        sprite = new Sprite(new Texture(Gdx.files.internal("board/firstLaserTestImage.png")));
        sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        System.out.println("YYEEHAW");
//        System.out.println(dir);
        switch (dir) {
            case NORTH:
                this.setRotation(90f);
                break;
            case WEST:
                break;
            case SOUTH:
                this.setRotation(90f);
                break;
            default:
                break;
        }


    }

}