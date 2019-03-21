package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GUIPiece extends Actor {

    public Sprite sprite;
    float rotation;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite,getX(),getY(),getWidth()/2,getHeight()/2,getWidth(),getHeight(),getScaleX(),getScaleY(),rotation);
    }

    public void setRotation(float rot){
        this.rotation = rot;
    }
}
