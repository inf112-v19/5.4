package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class LabelHandler {

    Label.LabelStyle hoverStyle;
    Label.LabelStyle labelStyle;
    Color fontColor;
    Color hoverColor;
    BitmapFont font;

    public LabelHandler(Skin skin){

        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        this.font = font;
        this.fontColor = new Color(1f, 1f, 0.4f, 1f);
        this.hoverColor = new Color(1f, 1f, 0.8f, 1f);
        this.labelStyle = new Label.LabelStyle(font, fontColor);
        this.hoverStyle = new Label.LabelStyle(font, hoverColor);
    }

    public Label createNewLabel(String text) {

        //BitmapFont font = new BitmapFont();
        //font.setColor(fontColor);
        return createNewLabel(text, 5);
    }

    public Label createNewLabel(String text, int scale) {
        //BitmapFont font = new BitmapFont();
        Label.LabelStyle newLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        //newLabelStyle.font.getData().setScale(scale);
        Label newLabel = new Label(text, newLabelStyle);
        newLabel.setFontScale(scale);
        newLabel.setColor(fontColor);
        return newLabel;
    }

    public List<Label> createNewLabels(String[] labelTexts){
        List<Label> returnLabels = new ArrayList<>();
        for(String text : labelTexts){
            returnLabels.add(createNewLabel(text));
        }
        return returnLabels;
    }

    public void addHoverStyle(Label currLabel){
        currLabel.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                currLabel.setColor(hoverColor);

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                currLabel.setColor(fontColor);
            }
        });
    }

    public void addHoverStyle(List<Label> labels){
        for(Label label : labels){
            addHoverStyle(label);
        }
    }

}
