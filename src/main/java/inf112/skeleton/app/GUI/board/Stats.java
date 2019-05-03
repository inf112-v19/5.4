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

    Sprite heartSprite;
    Sprite dtSprite;
    List<Image> hearties;
    Cell healthRow;
    Cell dtRow;
    Skin skin;
    Label hpLabel;
    Label dtLabel;

    public Stats(Skin skin, Player player) {
        //hearties = new ArrayList<>();

        //this.setDebug(true);
        this.skin = skin;
        this.defaults().size(40).left();
        heartSprite = new Sprite(new Texture(Gdx.files.internal("board/heart.png")));
        dtSprite = new Sprite(new Texture(Gdx.files.internal("board/damageToken.png")));

        //this.setDebug(true);

        this.hpLabel = new Label("HEALTH", skin);
        hpLabel.setAlignment(Align.left);
        hpLabel.setColor(Color.GREEN);
        hpLabel.setFontScale(2f);


        this.dtLabel = new Label("DAMAGE TOKENS:", skin);
        dtLabel.setAlignment(Align.left);
        dtLabel.setColor(Color.ORANGE);
        dtLabel.setFontScale(2f);
        //dtLabel.getStyle().font.getData().

        updateStats(player);

    }

    public void updateStats(Player player) {

        this.clearChildren();

        int health = player.getHealth();
        int damagetokens = player.getDamageTokens();

        /*Label hpLabel = new Label("HEALTH", skin);
        hpLabel.setAlignment(Align.left);
        hpLabel.setColor(Color.PURPLE);
        hpLabel.setFontScale(2f);

        Label dtLabel = new Label("DAMAGE TOKENS:", skin);
        dtLabel.setAlignment(Align.left);
        dtLabel.setFontScale(2f);*/

        this.add(hpLabel).colspan(3).expandX();
        this.row();

        for (int i = 0; i < health; i++) { //player.getHealth()
            Image heartie = new Image(heartSprite.getTexture());
            this.add(heartie).uniform();
            //hearties.add(heartie);
        }
        this.row();

        this.add(dtLabel).colspan(10).expandX().size(60, 60);
        this.row();

        System.out.println("AMOUNT OF DAMAGE TOKENS " + damagetokens);
        for (int i = 0; i < damagetokens; i++) {
            Image stonie = new Image(dtSprite.getTexture());
            this.add(stonie).uniform();
        }

    }

}

