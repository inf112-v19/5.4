package inf112.skeleton.app.GUI.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.gameLogic.Player;

public class GameOverStage extends Stage {
    public GameOverStage(Viewport viewport, Skin skin, Player player) {
        super(viewport);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        LabelHandler labelHandler = new LabelHandler(skin);
        String labelString = new String("PLAYER " + player.getName() + " WON!!!");
        Label gameOverLabel = labelHandler.createNewLabel(labelString, 10);

        mainTable.add(gameOverLabel);

        this.addActor(mainTable);
    }
}
