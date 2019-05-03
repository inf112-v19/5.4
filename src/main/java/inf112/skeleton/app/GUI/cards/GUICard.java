package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
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

    public Button getButton() {
        return this.cardButton;
    }

    ;

    public GUICard(Skin skin, ProgramCard pgCard, CardButtonStyle cardButtonStyle) {

        this.skin = skin;
        this.cardButtonStyle = cardButtonStyle;
        this.buttonTextLabels = new Table();
        this.pgCard = pgCard;

        setCardValues(pgCard.getPriority(), pgCard.getCardType().getDescription());


        cardActionLabel.setColor(Color.WHITE);

        // The main button added to the Stack.
        this.cardButton = new CardButton(this, cardButtonStyle);
        this.add(cardButton);

        buttonTextLabels.setFillParent(false);
        buttonTextLabels.top().right();

        // Refresh the cards display values.
        updateCard();

        // Click throughable
        buttonTextLabels.setTouchable(Touchable.disabled);

        this.add(buttonTextLabels);

    }

    // This needs to be run whenever the card's content changes.
    private void updateCard() {
        // Always wipe first
        buttonTextLabels.clearChildren();
        // The GUICard's priority value and action added to display.

        buttonTextLabels.left();

        // Tuned to be top right.
        buttonTextLabels.add(priorityValueLabel).top().right().padRight(24).padTop(15);
        buttonTextLabels.row();

        // Tuned to be in the middle.
        int textAreaWidth = 96;
        buttonTextLabels.add(cardActionLabel).width(textAreaWidth).expandY().center().padLeft(24).padRight(20).padBottom(20);

    }

    private void getCardValues() {
        //; GAME LOGIC INSERT

    }

    public void setCardValues(int priorityValue, String cardAction) {
        this.priorityValueLabel = new Label(Integer.toString(priorityValue), skin);
        this.priorityValueLabel.setAlignment(Align.center);
        this.priorityValueLabel.setFontScale(1.2f);
        this.cardActionLabel = new Label(cardAction, skin);
        this.cardActionLabel.setAlignment(Align.center);

        this.priorityValueLabel.setColor(Color.FIREBRICK);

        this.cardActionLabel.setColor(Color.BLUE);
        this.cardActionLabel.setWrap(true);

        updateCard();

    }

    public ProgramCard getProgramCard() {
        return this.pgCard;
    }

}