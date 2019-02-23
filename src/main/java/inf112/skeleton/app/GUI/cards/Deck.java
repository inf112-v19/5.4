package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class Deck extends Table {

    Skin skin;
    ButtonGroup buttonGroup;
    Button listenerButton;

    ArrayList<Card> assignedCards;
    ArrayList<Card> pickedCards;
    ArrayList<Card> drawCards;

    ArrayList<Cell> cardCells;

    int amountOfCardsAllowedToPick;
    int maxCards;
    boolean readyBool;

    Label cardsPickedDisplay;
    Label instructionLabel;
    String updateButtonString;

    public Deck(Skin skin){

        this.skin = skin;
        this.assignedCards = new ArrayList<Card>();
        this.drawCards = new ArrayList<Card>();
        this.pickedCards = new ArrayList<Card>();

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
        for (int i = 0; i < maxCards-2; i++) {
            Card card = new Card(skin);
            Button cardButton = card.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedCards.add(card);
        }

        Card testCard1 = new Card(skin);
        testCard1.setCardValues("120", "move 3");
        Card testCard2 = new Card(skin);
        testCard2.setCardValues("230", "move 1");
        ArrayList<Card> testCards = new ArrayList<>();
        testCards.add(testCard1);
        testCards.add(testCard2);
        for(Card tempCard : testCards){
            Button cardButton = tempCard.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedCards.add(tempCard);
        }



    }

    /**
     * Creates a test deck.
     */
    public void testDeck(){

        // Set instruction label.
        String instructions = "Pick " + amountOfCardsAllowedToPick + ":";
        instructionLabel.setText(instructions);
        updateButtonString = "READY";

        // Which cards to draw
        drawCards = assignedCards;

        drawDeck();

        this.cardsPickedDisplay = new Label(amountCardsPickedCounter(), skin);
        this.add(cardsPickedDisplay).pad(20);
    }

    /**
     * Pick which order your deck is in!
     */
    public void pickDeckOrder(){

        // DRAG AND DROP THESE BAD BOYS
        // Always clear first.
        this.clearChildren();
        pickedCards.clear();

        // Making label.
        String instructions = "Order your cards:";
        instructionLabel.setText(instructions);
        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());

        // Getting the clicked buttons, and fetch it's corresponding card.
        Array<CardButton> cardButtonArray = new Array<CardButton>(this.buttonGroup.getAllChecked());

        for(CardButton cardButton : cardButtonArray){
            this.pickedCards.add(cardButton.getCard());
        }

        // Uncheck buttons
        this.buttonGroup.uncheckAll();

        this.drawCards = this.pickedCards;

        // Add undo button
        this.updateButtonString = "UNDO";

        // DRAW THE THING
        drawDeck();


        for(Card tempCard : drawCards){
            addDragAndDropCard(tempCard);
        }



    }

    public void drawDeck(){

        // Always clear first.
        this.clearChildren();
        this.left().bottom();

        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());

        // Add new assignedCards to deck.
        ArrayList<Cell> cardCells = new ArrayList<Cell>();
        // Adds all cards to the Deck table.

        for(Card card : drawCards){
            Cell cardCell = this.add(card);
            cardCells.add(cardCell);
        }

        this.cardCells = cardCells;

        addActionButton(this, updateButtonString);
        this.add(listenerButton).left().center().size(listenerButton.getWidth(), listenerButton.getHeight());
    }



    /**
     * The ready/undo button. Checks for button press.
     *
     */
    public void addActionButton(final Deck deck, String text){

        this.listenerButton = new TextButton(text, skin);

        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("down");
                // What to do if ready:
                if(readyBool){
                    // CHANGE
                    readyBool = false;
                    deck.testDeck();

                    // Removes drag listeners :-)
                    for(Card card: drawCards){
                        for(EventListener ill : card.getListeners()){
                            card.removeListener(ill);
                        }
                    }

                }
                // What to do if not ready(undo):
                else{
                    readyBool = true;
                    deck.pickDeckOrder();

                }

            }
        });
    }


    public void addCardCounterUpdateListener(Button listenerButton){

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

    public void addDragAndDropCard(final Card listenerCard){

        listenerCard.addListener(new DragListener() {

            float oldX = listenerCard.getX();


            public void drag(InputEvent event, float x, float y, int pointer) {
                listenerCard.moveBy(x - listenerCard.getWidth() / 2, y-listenerCard.getHeight() / 2);
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                oldX= listenerCard.getX();
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                float nowX = listenerCard.getX();
                int pos = pickedCards.indexOf(listenerCard);

                System.out.println("OldX: " + oldX);
                System.out.println("NowX: " + nowX);

                System.out.println("DENNE I VARIABELEN: " + pos);

                int widthChange = (int) (nowX-oldX);
                System.out.println("WidthChange:" + widthChange);
                int positionChanges = widthChange/(int)listenerCard.getWidth();
                System.out.println("Position changes: " + positionChanges);
                System.out.println("Position: " + pos);


                boolean tooFarLeft = !(pos+positionChanges>=0);
                boolean moveLeft = (!tooFarLeft && widthChange<0);
                boolean tooFarRight = !(pos+positionChanges <= pickedCards.size()-1);
                boolean moveRight = (!tooFarRight && widthChange>0);


                if( moveLeft || moveRight ){
                    Card tempCard = drawCards.get(pos+positionChanges);
                    System.out.println("MOVE");
                    pickedCards.set(pos+positionChanges,listenerCard);
                    pickedCards.set(pos, tempCard);
                    oldX=nowX;
                }

                if(tooFarRight){
                    int maxPos = pickedCards.size()-1;
                    Card tempCard = drawCards.get(maxPos);
                    pickedCards.set(maxPos, listenerCard);
                    pickedCards.set(pos, tempCard);
                    oldX=nowX;
                }

                if(tooFarLeft){
                    int minPos = 0;
                    Card tempCard = drawCards.get(minPos);
                    pickedCards.set(minPos, listenerCard);
                    pickedCards.set(pos, tempCard);
                    oldX=nowX;
                }

                /*// Move left
                if(widthChange<0 && pos-positionChanges>=0){
                    System.out.println("MOVE TO DA LEFT");
                    Card tempCard = drawCards.get(pos+positionChanges);
                    pickedCards.set(pos+positionChanges,listenerCard);
                    pickedCards.set(pos, tempCard);
                    oldX=nowX;
                }

                // Move right
                if(widthChange>0 && pos+positionChanges <= pickedCards.size()-1 ){
                    System.out.println("MOVE TO DA RIGHT");
                    Card tempCard = drawCards.get(pos+positionChanges);
                    pickedCards.set(pos+positionChanges,listenerCard);
                    pickedCards.set(pos, tempCard);
                    oldX=nowX;
                }*/

                drawCards = pickedCards;
                drawDeck();

            }


        });
    }
}
