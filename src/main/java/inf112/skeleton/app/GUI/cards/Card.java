package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;

// Card needs to turn into a Stack that hold two tables or actors - one for the button, and a table that hold the text and image.

public class Card extends Stack {

    private Label priorityValue;
    private Label cardAction; // Should be changed to picture in the future.
    private Button cardButton;
    Skin skin;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Button getButton(){return this.cardButton;};

    public Card(Skin skin){

            this.skin = skin;

            getCardValues();

            // The main button added to the Stack.
            this.cardButton = new CardButton(this,skin);
            this.add(cardButton);

            Table buttonTextLabels = new Table();
            buttonTextLabels.setFillParent(true);
            //buttonTextLabels.setDebug(true);
            buttonTextLabels.defaults().expandX().right();

            // The card's priority value and action added to display.
            buttonTextLabels.add(priorityValue).padRight(22);
            buttonTextLabels.row();
            buttonTextLabels.add(cardAction).padRight(15);

            // Click throughable
            buttonTextLabels.setTouchable(Touchable.disabled);

            this.add(buttonTextLabels);

        }

    private void getCardValues() {
        // GAME LOGIC INSERT
        this.priorityValue = new Label("1000", skin);
        this.cardAction = new Label("MOVE 5", skin);
    }

}