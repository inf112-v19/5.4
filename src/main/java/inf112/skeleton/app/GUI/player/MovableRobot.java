package inf112.skeleton.app.GUI.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import inf112.skeleton.app.GUI.pieces.Robot;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.ActionTypeType;
import inf112.skeleton.app.gameLogic.enums.Direction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MovableRobot extends Robot {


   public MovableRobot(int robotnr){
       super(robotnr);
       setBounds(getX(), getY(), getWidth(), getHeight());

       addListener(new DragListener() {
           public void drag(InputEvent event, float x, float y, int pointer) {
               moveBy(x - getWidth()/2, y - getHeight()/2);
           }
       });

       addListener(new InputListener() {
           @Override
           public boolean keyDown(InputEvent event, int keycode) {

               switch (keycode) {
                   case Input.Keys.RIGHT:
                       doAction(ActionTypeType.MOVE, Direction.EAST);
                       break;
                   case Input.Keys.LEFT:
                       doAction(ActionTypeType.MOVE, Direction.WEST);
                       break;
                   case Input.Keys.DOWN:
                       doAction(ActionTypeType.MOVE, Direction.SOUTH);
                       break;
                   case Input.Keys.UP:
                       doAction(ActionTypeType.MOVE, Direction.NORTH);
                       doAction(ActionTypeType.ROTATE, Direction.NORTH);
                       break;
               }

               return true;
           }
       });

   }

   public void doAction(ActionTypeType att, Direction faceDir){
       setOrigin(getWidth() / 2, getHeight() / 2);

       switch (att) {
           case MOVE:

               // Move sound
               Sound moveSound = Gdx.audio.newSound(Gdx.files.internal("sound/move.mp3"));
               moveSound.play(0.4f,0.5f,1);

               // The move sound
               MoveByAction moveAction = new MoveByAction();
               moveAction.setDuration(0.3f);
               moveAction.setInterpolation(Interpolation.pow3);
               System.out.println("YEEEE");


               switch(faceDir) {
                   case NORTH:
                       System.out.println("WWWAHASHDABSD");
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

               MovableRobot.this.addAction(sequence(moveAction, new DelayAction(1000), new RunnableAction(){
                   @Override
                   public void run() {
                       System.out.println("COMPLETE!");
                   }
               }));

               break;
           case ROTATE:

               System.out.println("rotating boys"
               );
               RotateByAction rotateByAction = new RotateByAction();
               rotateByAction.setAmount(90f);
               MovableRobot.this.addAction(rotateByAction);
               break;

       }
   }

}
