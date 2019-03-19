package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.gameLogic.ProgramCard;


// GUICard needs to turn into a Stack that hold two tables or actors - one for the button, and a table that hold the text and image.

public class GUICard extends Stack {

    private int priorityValue;
    private Label priorityValueLabel;
    private String cardAction;
    Label cardActionLabel; // Should be changed to picture in the future.
    Table buttonTextLabels;
    Button cardButton;
    CardButtonStyle cardButtonStyle;
    Skin skin;

    ProgramCard pgCard;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Button getButton(){return this.cardButton;};

    public GUICard(Skin skin, ProgramCard pgCard, CardButtonStyle cardButtonStyle){

            this.skin = skin;
            this.cardButtonStyle = cardButtonStyle;
            this.buttonTextLabels = new Table();
            this.pgCard = pgCard;

            setCardValues(pgCard.getPriority(),pgCard.getCardType().getDescription());

            // Trying to make newlines
            /*cardActionLabel.setWrap(true);
            cardActionLabel.pack();
            cardActionLabel.setWidth(this.getWidth());
            cardActionLabel.pack();
            cardActionLabel.setWidth(this.getWidth());*/

            cardActionLabel.setColor(Color.WHITE);

            // The main button added to the Stack.
            this.cardButton = new CardButton(this, cardButtonStyle);
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
        // The GUICard's priority value and action added to display.
        buttonTextLabels.add(priorityValueLabel).width(this.getWidth()).padLeft(22);
        buttonTextLabels.row();
        buttonTextLabels.add(cardActionLabel).width(this.getWidth()).padLeft(15);
    }
    private void getCardValues() {
        //; GAME LOGIC INSERT

    }

    public void setCardValues(int priorityValue, String cardAction){
        this.priorityValueLabel = new Label(Integer.toString(priorityValue),skin);
        this.cardActionLabel = new Label( cardAction, skin);
        this.priorityValueLabel.setColor(Color.BLUE);

        this.cardActionLabel.setColor(Color.BLUE);
        this.cardActionLabel.setWrap(true);

        updateCard();

    }

    public ProgramCard getProgramCard(){
        return this.pgCard;
    }

}