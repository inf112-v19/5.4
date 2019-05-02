package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class CardButton extends Button {

    GUICard GUICard;

    public CardButton(GUICard GUICard, CardButtonStyle buttonStyle) {

        this.setStyle(buttonStyle);
        this.GUICard = GUICard;
        // Custom card texture
    }


    // Future change of skin
    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }*/

    public GUICard getGUICard() {
        return this.GUICard;
    }


}
