/*package inf112.skeleton.app.GUI.board;

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
    List<Image> hearties;
    List<Cell> healthCells;
    List<Cell> dtCells;

    public Stats(Skin skin, Player player) {


        hearties = new ArrayList<>();

        this.defaults().size(40).left();
        heartSprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));

        //this.setDebug(true);

        Label hpLabel = new Label("HEALTH", skin);
        hpLabel.setAlignment(Align.left);
        hpLabel.setColor(Color.PURPLE);
        hpLabel.setFontScale(2f);

        this.add(hpLabel).colspan(3).expandX();
        this.row();

        for(int i = 0; i < 3; i++){
            Cell healthCell = this.add().uniform();
            healthCells.add(healthCell);
        }


        // Adds row.
        this.row();

        Label dtLabel = new Label("DAMAGE TOKENS:", skin);
        dtLabel.setAlignment(Align.left);
        dtLabel.setFontScale(2f);

        this.add(dtLabel).colspan(10).expandX().size(60, 60);
        this.row();


        for(int i = 0; i < 10; i++){
            Cell dtCell = this.add().uniform();
            dtCells.add(dtCell);
        }

    }

    public void updateStats(Player player){


        int health = player.getHealth();
        int damagetokens = player.getDamageTokens();

        this.clearChildren();

        for (int i = 0; i < health; i++) { //player.getHealth()
            Image heartie = new Image(heartSprite.getTexture());
            dtCells.set()
            heartie.setVisible(true);
            //hearties.add(heartie);
        }

        for (int i = 0; i < 8; i++) {
            Image stonie = new Image(heartSprite.getTexture());
            Cell stonieCell = this.add(stonie).uniform();
        }

    }


}*/

