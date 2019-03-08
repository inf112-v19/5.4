package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CardButton extends Button {

    GUICard GUICard;
    //Sprite sprite;

    public CardButton(GUICard GUICard, Skin skin){
        super(skin);
        this.GUICard = GUICard;
     // Custom card texture
       //this.sprite = new Sprite(new Texture(Gdx.files.internal("roborally_programCard.png")));
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
