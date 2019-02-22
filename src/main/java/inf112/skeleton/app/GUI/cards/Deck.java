package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;

public class Deck extends Table {

    Skin skin;
    ButtonGroup buttonGroup;
    Button actionButton;
    boolean readyBool;

    public Deck(Skin skin){

        this.skin = skin;

        // Max 5 checked!
        buttonGroup = new ButtonGroup();
        buttonGroup.setMaxCheckCount(5);
        buttonGroup.setMinCheckCount(0);

        // Default card position
        this.defaults().left().size(140,200);

        testDeck();


    }

    public void testDeck(){

        // Always clear first.
        this.clearChildren();

        this.left().bottom();
        Label pickLabel = new Label("Pick 5:", skin);
        this.add(pickLabel).size(pickLabel.getWidth(),pickLabel.getHeight());
        for(int i = 0; i< 9; i++){
            Card card = new Card(skin);
            buttonGroup.add(card.getButton());
            this.add(card).bottom();
        }

        addActionButton(this, "READY");
        this.add(actionButton).left().center().size(actionButton.getWidth(),actionButton.getHeight());
    }

    public void pickDeck(){}
    public void orderDeck(){

        // Always clear first.
        this.clearChildren();

        Array<CardButton> cardButtonArray = this.buttonGroup.getAllChecked();

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
}
