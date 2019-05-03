package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HostStage extends Stage {

    public HostStage(Viewport viewport, Skin skin) {
        super(viewport);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        BitmapFont textLabelFont = new BitmapFont();
        textLabelFont.getData().setScale(8);

        Label.LabelStyle textLabelStyle = new Label.LabelStyle(textLabelFont, new Color(1f, 1f, 0.4f, 1f));
        Label textLabel = new Label("WAITING FOR PLAYERS...", textLabelStyle);

        mainTable.add(textLabel);

        this.addActor(mainTable);

        Gdx.input.setInputProcessor(this);

    }

}
