package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

// Card needs to turn into a Stack that hold two tables or actors - one for the button, and a table that hold the text and image.

public class Card extends Stack {

    private Label priorityValue;
    private Label cardAction; // Should be changed to picture in the future.
    Table buttonTextLabels;
    private Button cardButton;
    Skin skin;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Button getButton(){return this.cardButton;};

    public Card(Skin skin){

            this.skin = skin;
            this.buttonTextLabels = new Table();


            setCardValues("500", "R 180");

            // The main button added to the Stack.
            this.cardButton = new CardButton(this,skin);
            this.add(cardButton);

            buttonTextLabels.setFillParent(true);
            //buttonTextLabels.setDebug(true);
            buttonTextLabels.defaults().expandX().right();

            // Refresh the cards display values.
            updateCard();

            // Click throughable
            buttonTextLabels.setTouchable(Touchable.disabled);

            this.add(buttonTextLabels);



        }

    private void updateCard(){
        // Always wipe first
        buttonTextLabels.clearChildren();
        // The card's priority value and action added to display.
        buttonTextLabels.add(priorityValue).padRight(22);
        buttonTextLabels.row();
        buttonTextLabels.add(cardAction).padRight(15);
    }
    private void getCardValues() {
        //; GAME LOGIC INSERT

    }

    public void setCardValues(String priorityValue, String cardAction){
        this.priorityValue = new Label(priorityValue, skin);
        this.cardAction = new Label( cardAction, skin);

        updateCard();

    }

}