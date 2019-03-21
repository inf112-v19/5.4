package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.gameLogic.Player;

public class Stats extends Table {

    Sprite sprite;

    public Stats(Skin skin, Player player){

        sprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));

        //this.setDebug(true);

        this.add(new Label("HP:", skin));
        this.row();
        for(int i=0; i<player.getHealth(); i++){
            this.add(new Image(sprite.getTexture())).size(30).uniform();
        }

    }

}
