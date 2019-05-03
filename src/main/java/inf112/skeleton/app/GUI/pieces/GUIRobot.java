package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class GUIRobot extends GUIPiece {
    String path = "bots/";

    public GUIRobot(int robotNumber) {

        switch (robotNumber) {
            case 0:
                path += "rolobot-alpha/rolobot-alpha.png";
                break;
            case 1:
                path += "sputnik/yellow_robot.png";
                break;
        }

        sprite = new Sprite(new Texture(Gdx.files.internal(path)));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }
}
