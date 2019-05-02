package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CardButton extends Button {

    GUICard GUICard;

    public CardButton(GUICard GUICard, CardButtonStyle buttonStyle){

        this.setStyle(buttonStyle);
        this.GUICard = GUICard;
        // Custom card texture
    }



    // Future change of skin
    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }*/

    public GUICard getGUICard(){
        return this.GUICard;
    }


}
