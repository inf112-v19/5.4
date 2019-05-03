package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.LoadScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AIOrPlayerStage extends Stage {

    public AIOrPlayerStage(Viewport viewport, Skin skin, LabelHandler labelHandler, LoadScreen screen, Game game) {

        super(viewport);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        //mainTable.setDebug(true);

        Label hostLabel = labelHandler.createNewLabel("PvE",5);
        Label joinLabel = labelHandler.createNewLabel("PvP",5);
        //List<Label> labels = labelHandler.createNewLabels( new ArrayList<Label>(Arrays.asList(["HOST","JOIN"]));
        List<Label> labelList = new ArrayList<Label>();
        labelList.addAll(Arrays.asList(hostLabel, joinLabel));

        //labelHandler.addHoverStyle(labelList);


        mainTable.add(hostLabel).pad(30);
        mainTable.add(joinLabel).pad(30);
        this.addActor(mainTable);

        labelHandler.addHoverStyle(hostLabel);

        for(Label currLabel : labelList){
            labelHandler.addHoverStyle(currLabel);
        }

        hostLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setStage(new PlayerNumberStage(viewport, skin, labelHandler, screen, game, false));
            }
        });

        joinLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setStage(new PlayerNumberStage(viewport, skin, labelHandler, screen, game, true));
            }
        });

        Gdx.input.setInputProcessor(this);

    }
}
