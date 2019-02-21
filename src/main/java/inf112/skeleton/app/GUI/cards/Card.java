package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.*;

// Card needs to turn into a Stack that hold two tables or actors - one for the button, and a table that hold the text and image.

public class Card extends Stack {

    private Label text;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Card(Skin skin){
            // Table stuff
            //this.setDebug(true);
            //final Button btn = new Button("500 \n \n R 180", skin);
            final Button btn = new Button(skin);
            this.add(btn);

            Table textStuff = new Table();
            Label priority = new Label("500", skin);
            textStuff.add(priority).top().pad(10);

            Label cardAction = new Label("R 180", skin);
            textStuff.add(cardAction).bottom().pad(20);

            this.add(textStuff);



            //Label priority = new Label("500", skin);
            //Label description = new Label("Move 5", skin);




            //this.add(btn).bottom().size(120,200);
            /* this.add(priority);
            this.row();
            this.row();
            this.add(description);*/
        }

}