package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CardButton extends Button {

    Card card;

    public CardButton(Card card, Skin skin){
        super(skin);
        this.card = card;
    }


}
