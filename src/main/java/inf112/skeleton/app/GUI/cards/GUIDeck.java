package inf112.skeleton.app.GUI.cards;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.gameLogic.ProgramCard;

import java.util.ArrayList;
import java.util.List;

public class GUIDeck extends Table {

    Skin skin;
    ButtonGroup buttonGroup;
    Button listenerButton;
    Button doneButton;
    CardButtonStyle cardButtonStyle;

    ArrayList<GUICard> assignedGUICards;
    ArrayList<GUICard> pickedGUICards;
    ArrayList<GUICard> drawGUICards;
    List<ProgramCard> pgCards;

    ArrayList<Cell> cardCells;
    ArrayList<Button> actionButtons;

    int amountOfCardsAllowedToPick;
    int maxCards;

    boolean readyBool;
    boolean goBool;


    Label cardsPickedDisplay;
    Label instructionLabel;
    String updateButtonString;


    public GUIDeck(Skin skin, List<ProgramCard> pgCards, Button doneButton) {

        this.skin = skin;

        this.cardButtonStyle = new CardButtonStyle();
        this.assignedGUICards = new ArrayList<GUICard>();
        this.pickedGUICards = new ArrayList<GUICard>();
        this.drawGUICards = new ArrayList<GUICard>();

        this.actionButtons = new ArrayList<>();
        this.doneButton = doneButton;

        this.amountOfCardsAllowedToPick = 5;
        this.maxCards = 9;

        this.instructionLabel = new Label("Pick 5:", skin);
        instructionLabel.setWrap(true);

        // Max 5 checked!
        buttonGroup = new ButtonGroup();
        buttonGroup.setMaxCheckCount(5);
        buttonGroup.setMinCheckCount(0);

        // Default GUICard position
        this.defaults().left().size(140, 200).expandY();

        this.pgCards = pgCards;

        createGUICards();

        // USED FOR TESTING
        //createGUICards(pgDeck);

    }

    public void createGUICards() {

        /*ProgramCardDeck pgDeck = new ProgramCardDeck();
        pgDeck.shuffleDeck();
        List<ProgramCard> cards = pgDeck.drawXCards(maxCards);
        this.pgCards = cards; */

        System.out.println(this.pgCards + " HEI JEG HETER CREATE GUICARDS");
        for (ProgramCard card : this.pgCards) {
            GUICard guiCard = new GUICard(skin, card, this.cardButtonStyle);
            Button cardButton = guiCard.getButton();
            this.addCardCounterUpdateListener(cardButton);
            buttonGroup.add(cardButton);
            this.assignedGUICards.add(guiCard);
        }
    }

    /**
     *
     */

    /**
     * Creates a the "card picking" setup.
     */
    public void pickCardsSetup() {

        // You can click the cards!
        setClickingDisabled(false);

        // Set instruction label.
        String instructions = "Pick " + amountOfCardsAllowedToPick + ":";
        instructionLabel.setText(instructions);
        updateButtonString = "READY";

        // Which cards to draw
        drawGUICards = assignedGUICards;

        // Adds the action buttons on the far right.
        addActionButton(this, updateButtonString);
        this.actionButtons.add(this.listenerButton);

        drawDeck();

        this.cardsPickedDisplay = new Label(amountCardsPickedCounter(), skin);
        this.add(cardsPickedDisplay).pad(20);
    }


    /**
     * Pick which order your deck is in!
     */
    public void orderDeckSetup() {

        // DRAG AND DROP THESE BAD BOYS
        // Always clear first.
        this.clearChildren();
        pickedGUICards.clear();

        // Makes sure one can't click the cards.
        setClickingDisabled(true);

        // Making label.
        String instructions = "Order your cards:";
        instructionLabel.setText(instructions);

        // Getting the clicked buttons, and fetch it's corresponding GUICard.
        Array<CardButton> cardButtonArray = new Array<CardButton>(this.buttonGroup.getAllChecked());

        for (CardButton cardButton : cardButtonArray) {
            this.pickedGUICards.add(cardButton.getGUICard());
        }

        // Uncheck buttons
        this.buttonGroup.uncheckAll();

        this.drawGUICards = this.pickedGUICards;

        // Add undo button
        this.updateButtonString = "UNDO";
        // Adds the action buttons on the far right.
        addActionButton(this, updateButtonString);
        this.actionButtons.add(this.listenerButton);

        // Add the done button.
        this.actionButtons.add(doneButton);
        this.addDoneActionButton(doneButton);

        // DRAW THE THING
        drawDeck();

        // Adds drag and drop listeners for all the cards.
        for (GUICard tempGUICard : drawGUICards) {
            addDragAndDropCard(tempGUICard);
        }


    }

    private void setClickingDisabled(boolean clickBool) {
        Array<CardButton> currCardButtons = this.buttonGroup.getButtons();
        for (CardButton currBtn : currCardButtons) {
            currBtn.setDisabled(clickBool);
        }
    }

    public void drawDeck() {

        // Always clear first.
        this.clearChildren();
        this.left().bottom();

        this.add(instructionLabel).size(instructionLabel.getWidth(), instructionLabel.getHeight()).padRight(10);

        // Add new assignedGUICards to deck.
        ArrayList<Cell> cardCells = new ArrayList<Cell>();
        // Adds all cards to the GUIDeck table.

        for (GUICard GUICard : drawGUICards) {
            Cell cardCell = this.add(GUICard);
            cardCells.add(cardCell);
        }

        this.cardCells = cardCells;

        // Adds all the cards at the bottom
        for (Button btn : actionButtons) {
            this.add(btn).left().center().size(btn.getWidth(), btn.getHeight());
        }

    }

    /**
     * Removes the GUI deck.
     * pickedCards can now safely be retrieved in the correct order.
     */
    public void addDoneActionButton(Button listenerButton) {
        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearChildren();

                /*TextButton againBtn = new TextButton("Pick again", skin);
                addPickAgainButton(againBtn);

                add(againBtn);*/

            }
        });
    }

    public void addPickAgainButton(TextButton listenerButton) {
        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                pickCardsSetup();
            }
        });
    }

    /**
     * The ready/undo button. Checks for button press.
     */
    public void addActionButton(final GUIDeck GUIDeck, String text) {

        this.listenerButton = new TextButton(text, skin);

        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                actionButtons.clear();
                // If the player changed from ready to not ready.
                if (readyBool) {
                    readyBool = false;
                    // Removes drag listeners :-)
                    for (GUICard GUICard : drawGUICards) {
                        for (EventListener ill : GUICard.getListeners()) {
                            GUICard.removeListener(ill);
                        }
                    }

                    GUIDeck.pickCardsSetup();

                }
                // Opposite, not ready to ready.
                else {
                    readyBool = true;
                    GUIDeck.orderDeckSetup();
                }

            }
        });
    }

    /**
     * Updates the "cards picked" whenever a button in the button group is clicked.
     *
     * @param listenerButton
     */
    public void addCardCounterUpdateListener(Button listenerButton) {

        listenerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                cardsPickedDisplay.setText(amountCardsPickedCounter());
            }
        });

    }

    public String amountCardsPickedCounter() {
        return String.format("Picked: %d/%d", buttonGroup.getAllChecked().size, amountOfCardsAllowedToPick);
    }

    public void addDragAndDropCard(final GUICard listenerGUICard) {

        listenerGUICard.addListener(new DragListener() {

            float oldX = listenerGUICard.getX();


            public void drag(InputEvent event, float x, float y, int pointer) {
                listenerGUICard.moveBy(x - listenerGUICard.getWidth() / 2, y - listenerGUICard.getHeight() / 2);
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                oldX = listenerGUICard.getX();

                // Making sure the dragged GUICard is at the front. This appearently resets. 30 to be safe.
                listenerGUICard.setZIndex(30);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {

                // Change this to

                // Starting position
                float nowX = listenerGUICard.getX();
                int startPos = pickedGUICards.indexOf(listenerGUICard);

                int widthChange = (int) (nowX - oldX);
                int positionChanges = widthChange / (int) listenerGUICard.getWidth();
                int dropPos = startPos + positionChanges;

                boolean movedRight = (widthChange > 0);
                boolean movedLeft = (widthChange < 0);
                boolean tooFarLeft = dropPos < 0;
                boolean tooFarRight = dropPos > pickedGUICards.size() - 1;


                if (movedLeft || movedRight) {

                    GUICard tempGUICard;
                    GUICard lastCard = listenerGUICard;

                    // Checks if the player drags the card too long.
                    if (tooFarRight) {
                        dropPos = pickedGUICards.size() - 1;
                    } else if (tooFarLeft) {
                        dropPos = 0;
                    }

                    // If moving to the left, it need to iterate from i.e 2 to 4.
                    if (movedLeft) {
                        for (int currPos = dropPos; currPos < startPos; currPos++) {
                            tempGUICard = drawGUICards.get(currPos);
                            pickedGUICards.set(currPos, lastCard);
                            lastCard = tempGUICard;
                        }
                    }

                    // If moving to the left, it need to iterate from i.e 7 to 3.
                    else if (movedRight) {
                        for (int currPos = dropPos; currPos > startPos; currPos--) {
                            tempGUICard = drawGUICards.get(currPos);
                            pickedGUICards.set(currPos, lastCard);
                            lastCard = tempGUICard;
                        }
                    }
                    pickedGUICards.set(startPos, lastCard);
                    System.out.println("MOVE");
                    oldX = nowX;
                }

                drawGUICards = pickedGUICards;
                drawDeck();

            }


        });
    }

    public List<ProgramCard> getProgramCards() {
        return this.pgCards;
    }

    public void setProgramCards(List<ProgramCard> pgCards) {
        this.pgCards = pgCards;
        System.out.println(pgCards);
    }

    public boolean getGoBool() {
        return this.goBool;
    }

    public List<ProgramCard> getPickedProgramCards() {
        List<ProgramCard> pickedPgCards = new ArrayList<ProgramCard>();

        for (GUICard card : this.pickedGUICards) {
            pickedPgCards.add(card.getProgramCard());
        }

        return pickedPgCards;
    }

}
