package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.gameLogic.ProgramCard;
import inf112.skeleton.app.gameLogic.ProgramCardDeck;

import java.util.ArrayList;

public class GUIDeck extends Table {

    Skin skin;
    ButtonGroup buttonGroup;
    Button listenerButton;

    ArrayList<GUICard> assignedGUICards;
    ArrayList<GUICard> pickedGUICards;
    ArrayList<GUICard> drawGUICards;

    ArrayList<Cell> cardCells;

    int amountOfCardsAllowedToPick;
    int maxCards;
    boolean readyBool;

    Label cardsPickedDisplay;
    Label instructionLabel;
    String updateButtonString;

    public GUIDeck(Skin skin){

        this.skin = skin;
        this.assignedGUICards = new ArrayList<GUICard>();
        this.pickedGUICards = new ArrayList<GUICard>();
        this.drawGUICards = new ArrayList<GUICard>();

        this.amountOfCardsAllowedToPick = 5;
        this.maxCards = 9;

        this.instructionLabel = new Label("Pick 5:", skin);
        instructionLabel.setWrap(true);

        // Max 5 checked!
        buttonGroup = new ButtonGroup();
        buttonGroup.setMaxCheckCount(5);
        buttonGroup.setMinCheckCount(0);

        // Default GUICard position
        this.defaults().left().size(140,200).expandY();

        createCards();

        testDeck();


    }

    public void createCards() {

        ProgramCardDeck pgDeck = new ProgramCardDeck();
        pgDeck.shuffleDeck();

        for (int i = 0; i < maxCards; i++) {
            ProgramCard pgCard = pgDeck.getTopCard();
            GUICard guiCard = new GUICard(skin, pgCard);
            Button cardButton = guiCard.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedGUICards.add(guiCard);
        }

        /*for (int i = 0; i < maxCards-2; i++) {
            GUICard GUICard = new GUICard(skin);
            Button cardButton = GUICard.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedGUICards.add(GUICard);
        }

        GUICard testGUICard1 = new GUICard(skin);
        testGUICard1.setCardValues("120", "move 3");
        GUICard testGUICard2 = new GUICard(skin);
        testGUICard2.setCardValues("230", "move 1");
        ArrayList<GUICard> testGUICards = new ArrayList<>();
        testGUICards.add(testGUICard1);
        testGUICards.add(testGUICard2);
        for(GUICard tempGUICard : testGUICards){
            Button cardButton = tempGUICard.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedGUICards.add(tempGUICard);
        }*/



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
        drawGUICards = assignedGUICards;

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
        pickedGUICards.clear();

        // Making label.
        String instructions = "Order your cards:";
        instructionLabel.setText(instructions);
        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());

        // Getting the clicked buttons, and fetch it's corresponding GUICard.
        Array<CardButton> cardButtonArray = new Array<CardButton>(this.buttonGroup.getAllChecked());

        for(CardButton cardButton : cardButtonArray){
            this.pickedGUICards.add(cardButton.getGUICard());
        }

        // Uncheck buttons
        this.buttonGroup.uncheckAll();

        this.drawGUICards = this.pickedGUICards;

        // Add undo button
        this.updateButtonString = "UNDO";

        // DRAW THE THING
        drawDeck();


        for(GUICard tempGUICard : drawGUICards){
            addDragAndDropCard(tempGUICard);
        }



    }

    public void drawDeck(){

        // Always clear first.
        this.clearChildren();
        this.left().bottom();

        this.add(instructionLabel).size(instructionLabel.getWidth(),instructionLabel.getHeight());

        // Add new assignedGUICards to deck.
        ArrayList<Cell> cardCells = new ArrayList<Cell>();
        // Adds all cards to the GUIDeck table.

        for(GUICard GUICard : drawGUICards){
            Cell cardCell = this.add(GUICard);
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
    public void addActionButton(final GUIDeck GUIDeck, String text){

        this.listenerButton = new TextButton(text, skin);

        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("down");
                // What to do if ready:
                if(readyBool){
                    // CHANGE
                    readyBool = false;
                    GUIDeck.testDeck();

                    // Removes drag listeners :-)
                    for(GUICard GUICard : drawGUICards){
                        for(EventListener ill : GUICard.getListeners()){
                            GUICard.removeListener(ill);
                        }
                    }

                }
                // What to do if not ready(undo):
                else{
                    readyBool = true;
                    GUIDeck.pickDeckOrder();

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

    public void addDragAndDropCard(final GUICard listenerGUICard){

        listenerGUICard.addListener(new DragListener() {

            float oldX = listenerGUICard.getX();


            public void drag(InputEvent event, float x, float y, int pointer) {
                listenerGUICard.moveBy(x - listenerGUICard.getWidth() / 2, y- listenerGUICard.getHeight() / 2);
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                oldX= listenerGUICard.getX();

                // Making sure the dragged GUICard is at the front. This appearently resets. 30 to be safe.
                System.out.println(listenerGUICard.getZIndex());
                listenerGUICard.setZIndex(30);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                float nowX = listenerGUICard.getX();
                int pos = pickedGUICards.indexOf(listenerGUICard);

                System.out.println("OldX: " + oldX);
                System.out.println("NowX: " + nowX);

                System.out.println("DENNE I VARIABELEN: " + pos);

                int widthChange = (int) (nowX-oldX);
                System.out.println("WidthChange:" + widthChange);
                int positionChanges = widthChange/(int) listenerGUICard.getWidth();
                System.out.println("Position changes: " + positionChanges);
                System.out.println("Position: " + pos);


                boolean tooFarLeft = !(pos+positionChanges>=0);
                boolean moveLeft = (!tooFarLeft && widthChange<0);
                boolean tooFarRight = !(pos+positionChanges <= pickedGUICards.size()-1);
                boolean moveRight = (!tooFarRight && widthChange>0);


                if( moveLeft || moveRight ){
                    GUICard tempGUICard = drawGUICards.get(pos+positionChanges);
                    System.out.println("MOVE");
                    pickedGUICards.set(pos+positionChanges, listenerGUICard);
                    pickedGUICards.set(pos, tempGUICard);
                    oldX=nowX;
                }

                if(tooFarRight){
                    int maxPos = pickedGUICards.size()-1;
                    GUICard tempGUICard = drawGUICards.get(maxPos);
                    pickedGUICards.set(maxPos, listenerGUICard);
                    pickedGUICards.set(pos, tempGUICard);
                    oldX=nowX;
                }

                if(tooFarLeft){
                    int minPos = 0;
                    GUICard tempGUICard = drawGUICards.get(minPos);
                    pickedGUICards.set(minPos, listenerGUICard);
                    pickedGUICards.set(pos, tempGUICard);
                    oldX=nowX;
                }

                /*// Move left
                if(widthChange<0 && pos-positionChanges>=0){
                    System.out.println("MOVE TO DA LEFT");
                    GUICard tempCard = drawGUICards.get(pos+positionChanges);
                    pickedGUICards.set(pos+positionChanges,listenerGUICard);
                    pickedGUICards.set(pos, tempCard);
                    oldX=nowX;
                }

                // Move right
                if(widthChange>0 && pos+positionChanges <= pickedGUICards.size()-1 ){
                    System.out.println("MOVE TO DA RIGHT");
                    GUICard tempCard = drawGUICards.get(pos+positionChanges);
                    pickedGUICards.set(pos+positionChanges,listenerGUICard);
                    pickedGUICards.set(pos, tempCard);
                    oldX=nowX;
                }*/

                drawGUICards = pickedGUICards;
                drawDeck();

            }


        });
    }
}
