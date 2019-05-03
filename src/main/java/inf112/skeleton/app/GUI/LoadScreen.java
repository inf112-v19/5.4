package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.GUI.stages.LabelHandler;
import inf112.skeleton.app.GUI.stages.PlayerNumberStage;

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

        Skin skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));

        LabelHandler labelHandler = new LabelHandler(skin);

        Label rrLabel = labelHandler.createNewLabel("LET'S ROBO RALLY!", 8);
        //rrLabel.setFontScale(3);

        Label playLabel = labelHandler.createNewLabel("PLAY", 8);
        Label exitLabel = labelHandler.createNewLabel("EXIT", 8);

        List<Label> labelList = new ArrayList<Label>();
        labelList.addAll(Arrays.asList(playLabel, exitLabel));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.add(rrLabel).expand().center().top().pad(200).colspan(2);
        mainTable.row();
        mainTable.add(playLabel).expand().center().top().pad(200);
        mainTable.add(exitLabel).expand().center().top().pad(200);

        stage.addActor(mainTable);


        for (Label currLabel : labelList) {
            labelHandler.addHoverStyle(currLabel);
        }

        LoadScreen screen = this;

        playLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setStage(new PlayerNumberStage(viewport, skin, labelHandler, screen, game));
            }
        });

        exitLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        TextField usernameTextField = new TextField("", skin);
        stage.addActor(usernameTextField);

        usernameTextField.getText();

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
