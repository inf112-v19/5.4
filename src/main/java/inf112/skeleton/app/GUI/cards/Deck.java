package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

public class Deck extends Table {

    Skin skin;
    ButtonGroup buttonGroup;
    Button actionButton;
    Array<Card> cards;

    int amountOfCardsAllowedToPick;
    int maxCards;
    boolean readyBool;

    Label cardsPickedDisplay;
    Label instructionLabel;
    int pickedCardsCounter;

    public Deck(Skin skin){

        this.skin = skin;
        this.cards = new Array<Card>();
        this.amountOfCardsAllowedToPick = 5;
        this.maxCards = 9;
        this.instructionLabel = new Label("Pick 5:", skin);
        instructionLabel.setWrap(true);

        // Max 5 checked!
        buttonGroup = new ButtonGroup();
        buttonGroup.setMaxCheckCount(5);
        buttonGroup.setMinCheckCount(0);

        // Default card position
        this.defaults().left().size(140,200).expandY();

        createCards();

        testDeck();


    }

    public void createCards() {
        for (int i = 0; i < maxCards; i++) {
            Card card = new Card(skin);
            Button cardButton = card.getButton();
            this.addButtonListener(cardButton);
            buttonGroup.add(cardButton);

            this.cards.add(card);
        }
    }

    /**
     * Creates a test deck.
     */
    public void testDeck(){

        // Always clear first.
        this.clearChildren();
        this.left().bottom();

        // Set instruction label.
        String instructions = "Pick " + amountOfCardsAllowedToPick + ":";
        instructionLabel.setText(instructions);
        // Just fixes size.
        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());

        // Adds all cards to the Deck table.
        for(Card card : cards){
            this.add(card);
        }

        this.cardsPickedDisplay = new Label(amountCardsPickedCounter(), skin);
        this.add(cardsPickedDisplay).pad(20);
        addActionButton(this, "READY");
        this.add(actionButton).left().center().size(actionButton.getWidth(),actionButton.getHeight());
    }

    public void pickDeck(){}
    public void orderDeck(){

        // Always clear first.
        this.clearChildren();

        String instructions = "Order your cards:";
        instructionLabel.setText(instructions);
        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());



        Array<CardButton> cardButtonArray = new Array<CardButton>(this.buttonGroup.getAllChecked());
        this.buttonGroup.uncheckAll();

        for(CardButton cardButton : cardButtonArray){
            this.add(cardButton.card);
        }

        addActionButton(this, "UNDO");
        this.add(actionButton).left().center().size(actionButton.getWidth(),actionButton.getHeight());
    }


    public void addActionButton(final Deck deck, String text){

        this.actionButton = new TextButton(text, skin);

        actionButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                if(readyBool){
                    // CHANGE
                    readyBool = false;
                    deck.testDeck();
                }
                else{
                    readyBool = true;
                    deck.orderDeck();

                }
                return true;
            }
        });
    }

    public void addButtonListener(Button listenerButton){

        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                cardsPickedDisplay.setText(amountCardsPickedCounter());
            }
        });

    }

    public String amountCardsPickedCounter(){
        return String.format("Picked: %d/%d", buttonGroup.getAllChecked().size, amountOfCardsAllowedToPick);
    }
}
