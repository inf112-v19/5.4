package inf112.skeleton.app.GUI.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.gameLogic.enums.Action;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.enums.SoundPlayer;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MovableGUIRobot extends GUIRobot {

    TextureAtlas textureAtlas;
    Sprite newSprite;

    public MovableGUIRobot(int robotnr) {

        super(robotnr);
//       this.setScale(0.8f);
        setBounds(getX(), getY(), getWidth(), getHeight());


        textureAtlas = new TextureAtlas("bots/yellowBot/yellow_bot_sprites.txt");

        /*addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        });*/

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                switch (keycode) {
                    case Input.Keys.RIGHT:
                        fullAction(Action.MOVE_1, Direction.EAST);
                        break;
                    case Input.Keys.LEFT:
                        fullAction(Action.MOVE_1, Direction.WEST);
                        break;
                    case Input.Keys.DOWN:
                        fullAction(Action.MOVE_1, Direction.SOUTH);
                        break;
                    case Input.Keys.UP:
                        fullAction(Action.MOVE_1, Direction.NORTH);
                        break;
                    case Input.Keys.R:
                        fullAction(Action.ROTATE_R, Direction.NORTH);
                        break;
                    case Input.Keys.L:
                        fullAction(Action.ROTATE_L, Direction.NORTH);
                        break;
                    case Input.Keys.U:
                        fullAction(Action.ROTATE_U, Direction.NORTH);
                        break;
                }

                return true;
            }
        });

    }

    public void fullAction(Action action, Direction dir) {
        int numTimes = action.getValue();
        List<com.badlogic.gdx.scenes.scene2d.Action> toDoActions = new ArrayList<com.badlogic.gdx.scenes.scene2d.Action>();
        System.out.println(numTimes);

        for (int i = 0; i < numTimes; i++) {
            toDoActions.add(
                    new SequenceAction(getGUIAction(action.getActionType(), dir)
                            , new DelayAction(1)));
        }

        for(com.badlogic.gdx.scenes.scene2d.Action act : toDoActions){
            addAction(sequence(act));
        }

    }

    public com.badlogic.gdx.scenes.scene2d.Action getGUIAction(ActionType actionType, Direction faceDir) {
        setOrigin(getWidth() / 2, getHeight() / 2);

        switch (actionType) {
            case MOVE:

                SoundPlayer.GameSound.MOVE.playSound();

                // The move audio
                MoveByAction moveAction = new MoveByAction();
                moveAction.setDuration(0.3f);
                moveAction.setInterpolation(Interpolation.pow3);

                switch (faceDir) {
                    case NORTH:
                        moveAction.setAmount(0f, getHeight());
                        break;
                    case EAST:
                        moveAction.setAmount(getWidth(), 0f);
                        break;
                    case WEST:
                        moveAction.setAmount(-getWidth(), 0);
                        break;
                    case SOUTH:
                        moveAction.setAmount(0f, -getHeight());
                        break;

                }

                System.out.println("WE OUT HERE MAYNNNEEE");

                /* addAction(sequence(moveAction, new DelayAction(1), new RunnableAction() {
                    @Override
                    public void run() {
                        System.out.println("COMPLETE!");
                    }
                })); */

                return moveAction;
            case ROTATE:

                System.out.println("rotating boys");
                this.changeSprite(faceDir);

                // Remove this lol
                RotateByAction rotateByAction = new RotateByAction();
                rotateByAction.setAmount(90f);
                //MovableGUIRobot.this.addAction(rotateByAction);
                return rotateByAction;

        }
        return null;
    }

    public void changeSprite(Direction facingDir) {
        String spriteString = "yellow_robot_";
        switch (facingDir){
            case NORTH:
                spriteString += "back";
                break;
            case EAST:
                spriteString += "right";
                break;
            case WEST:
                spriteString += "left";
                break;
            case SOUTH:
                spriteString += "front";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }

}
