package inf112.skeleton.app.GUI.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GUIPiece extends Image {

    public Sprite sprite;
    float rotation;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        //batch.setColor(Color.BLUE);
        batch.draw(sprite, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), getScaleX(), getScaleY(), rotation);

    }

    public void setRotation(float rot) {
        this.rotation = rot;
    }
}
