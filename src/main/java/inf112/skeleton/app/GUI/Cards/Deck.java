package inf112.skeleton.app.GUI.Cards;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class Deck extends Table {

    public Deck(Skin skin){

        //this.setDebug(true);
        this.left().bottom();
        this.add(new Label("Pick three:", skin));
        for(int i = 0; i< 3; i++){
            this.add(new Card(skin));
        }

    }

}
