package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.LoadScreen;
import inf112.skeleton.app.GUI.MainGameScreen;

import static com.badlogic.gdx.utils.Align.center;

public class PlayerNumberStage extends Stage{

    public PlayerNumberStage(Viewport viewport, Skin skin, LabelHandler labelHandler, LoadScreen screen, Game game,
                             boolean isPvPMatch){
        super(viewport);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        BitmapFont textLabelFont = new BitmapFont();
        textLabelFont.getData().setScale(8);
        BitmapFont buttonLabelFont = new BitmapFont();
        buttonLabelFont.getData().setScale(4);

        Label.LabelStyle textLabelStyle = new Label.LabelStyle(textLabelFont, new Color(1f, 1f, 0.4f, 1f));
        Label.LabelStyle buttonLabelStyle = new Label.LabelStyle(buttonLabelFont, new Color(1f, 1f, 0.8f, 1f));

        String labelString = new String("ENTER NUMBER OF ");
        if(isPvPMatch){
            labelString += "PLAYERS: ";
        }
        else{
            labelString += "BOTS: ";
        }
        Label textLabel = new Label(labelString, textLabelStyle);

        TextField usernameTextField = new TextField("", skin);
        usernameTextField.setAlignment(center);
        usernameTextField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        Label enterLabel = new Label("ENTER", buttonLabelStyle);
        TextButton btn = new TextButton("", skin);
        btn.setLabel(enterLabel);

        usernameTextField.getStyle().font.getData().setScale(4);
        btn.getStyle().font.getData().setScale(2);

        mainTable.add(textLabel).center().top().padTop(300);
        mainTable.row();
        mainTable.add(usernameTextField).center().top().height(100).width(550).padTop(50).fill();
        mainTable.row();
        mainTable.add(btn).expand().top().padTop(50);

        this.addActor(mainTable);

        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/yeahEcho.mp3"));

                //usernameTextField.getText();
                int numPlayers;
                try {
                    numPlayers = Integer.parseInt(usernameTextField.getText());
                    if(!isPvPMatch){
                        numPlayers += 1;
                    }
                }
                catch (NumberFormatException e){
                    numPlayers = 1;

                    System.out.println("defaulting to " + numPlayers + " players");

                }

                MainGameScreen mainGameScreen = new MainGameScreen(numPlayers, isPvPMatch);
                game.setScreen(mainGameScreen);

                sound.play(0.5f);
                dispose();
            }
        });

        Gdx.input.setInputProcessor(this);
    }



}
