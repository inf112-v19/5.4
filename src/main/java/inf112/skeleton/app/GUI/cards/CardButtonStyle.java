package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class CardButtonStyle extends Button.ButtonStyle {

    public CardButtonStyle() {


        //Button.ButtonStyle buttonStyle = new Button.ButtonStyle(uSD, dSD, dSD);
        super(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("roborally_programCard.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("roborally_programCard_selected.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("roborally_programCard_selected.png")))));
    }
}
