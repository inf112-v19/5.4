package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CardButton extends Button {

    GUICard GUICard;

    public CardButton(GUICard GUICard, Skin skin){
        super(skin);
        this.GUICard = GUICard;
    }

    public GUICard getGUICard(){
        return this.GUICard;
    }


}
