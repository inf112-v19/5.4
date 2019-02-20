package inf112.skeleton.app.GUI.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TestCard extends Group {

    private Color color;
    BitmapFont font;


    private Label text;

    public TestCard() {

        color = new Color(1f,0f,0f,1f);
        font = new BitmapFont(Gdx.files.internal("bignoodle.fnt"));
        Label label = new Label("eta mat", new Label.LabelStyle(font, color));
        text = label;

        addActor(text);
    }
}