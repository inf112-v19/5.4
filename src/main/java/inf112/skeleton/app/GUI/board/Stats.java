package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import inf112.skeleton.app.gameLogic.Player;

import java.util.ArrayList;
import java.util.List;

public class Stats extends Table {

    Sprite heartSprite;
    List<Image> hearties;
    int maxToxens = 3;

    public Stats(Skin skin, Player player){

        hearties = new ArrayList<>();
        this.setDebug(true);
        heartSprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));

        //this.setDebug(true);

        this.add(new Label("DAMAGE TOKENS:", skin)).colspan(3);
        this.row();
        for(int i=0; i<maxToxens; i++){ //player.getHealth()
            Image heartie = new Image(heartSprite.getTexture());
            Cell heartieCell = this.add(heartie).size(30).uniform();
            heartie.setVisible(false);
            hearties.add(heartie);
        }

        for(int i=0; i<player.getHealth(); i++) { //
            hearties.get(i).setVisible(true);
        }
    }

}
