package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.gameLogic.Player;


import java.util.ArrayList;
import java.util.List;

public class Stats extends Table {

    private DisplayLog displayLog;
    Sprite heartSprite;
    Sprite dtSprite;
    List<Image> hearties;
    Cell healthRow;
    Cell dtRow;
    Skin skin;
    Label hpLabel;
    Label dtLabel;

    public Stats(Skin skin, Player player, DisplayLog displayLog) {
        this.skin = skin;
        this.setDebug(true);
        this.defaults().size(40).left();
        heartSprite = new Sprite(new Texture(Gdx.files.internal("board/heart.png")));
        dtSprite = new Sprite(new Texture(Gdx.files.internal("board/damageToken.png")));

        this.displayLog = displayLog;

        this.hpLabel = new Label("HEALTH", skin);
        hpLabel.setAlignment(Align.left);
        hpLabel.setColor(Color.GREEN);
        hpLabel.setFontScale(2f);


        this.dtLabel = new Label("DAMAGE TOKENS:", skin);
        dtLabel.setAlignment(Align.left);
        dtLabel.setColor(Color.ORANGE);
        dtLabel.setFontScale(2f);

        updateStats(player);

    }

    public void updateStats(Player player) {

        this.clearChildren();

        int health = player.getHealth();
        int damagetokens = player.getDamageTokens();

        this.add(hpLabel).colspan(3).expandX();
        this.row();

        for (int i = 0; i < health; i++) { //player.getHealth()
            Image heartie = new Image(heartSprite.getTexture());
            this.add(heartie).uniform();
        }
        this.row();

        this.add(dtLabel).colspan(10).expandX().size(60, 60);
        this.row();
        for (int i = 0; i < damagetokens; i++) {
            Image stonie = new Image(dtSprite.getTexture());
            this.add(stonie).uniform();
        }

        this.row();

        Label logLabel = new Label(displayLog.getLog(), skin);
        logLabel.setAlignment(Align.top);
        this.add(logLabel);

    }

}

