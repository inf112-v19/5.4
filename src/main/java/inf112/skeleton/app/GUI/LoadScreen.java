package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoadScreen implements Screen {


    OrthographicCamera camera;
    ExtendViewport viewport;
    Stage stage;
    GUIMain game;

    public LoadScreen(GUIMain game) {

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 800, 480);

        viewport = new ExtendViewport(1200, 1200, camera);

        stage = new Stage(viewport);

        this.game = game;

        BitmapFont font = new BitmapFont();
        font.getData().setScale(8, 8);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, new Color(1f, 1f, 0.4f, 1f));
        Label.LabelStyle hoverStyle = new Label.LabelStyle(font, new Color(1f, 1f, 0.8f, 1f));

        Label rrLabel = new Label("LET'S ROBO RALLY!", labelStyle);
        Label playLabel = new Label("PLAY", labelStyle);
        Label exitLabel = new Label("EXIT", labelStyle);

        List<Label> labelList = new ArrayList<Label>();
        labelList.addAll(Arrays.asList(playLabel, exitLabel));


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.add(rrLabel).expand().center().top().pad(200).colspan(2);
        mainTable.row();
        mainTable.add(playLabel).expand().center().top().pad(200);
        mainTable.add(exitLabel).expand().center().top().pad(200);

        stage.addActor(mainTable);


        for (Label currLabel : labelList){
            currLabel.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    //super.enter(event, x, y, pointer, fromActor);
                    currLabel.setStyle(hoverStyle);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    currLabel.setStyle(labelStyle);
                }
            });
        }

        playLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/yeahEcho.mp3"));

                MainGameScreen mainGameScreen = new MainGameScreen();
                game.setScreen(mainGameScreen);

                sound.play(0.5f);
                dispose();
            }
        });

        exitLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        //camera.update();

        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

        /*if (Gdx.input.isTouched()) {

            Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/yeahEcho.mp3"));

            MainGameScreen mainGameScreen = new MainGameScreen();
            game.setScreen(mainGameScreen);

            sound.play(0.5f);
            dispose();
        }*/


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
