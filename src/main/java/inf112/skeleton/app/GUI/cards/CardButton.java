package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class CardButton extends Button {

    GUICard GUICard;

    public CardButton(GUICard GUICard, CardButtonStyle buttonStyle) {

        this.setStyle(buttonStyle);
        this.GUICard = GUICard;
        // Custom card texture
    }

    public GUICard getGUICard() {
        return this.GUICard;
    }

}
