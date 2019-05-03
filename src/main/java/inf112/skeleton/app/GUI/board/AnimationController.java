package inf112.skeleton.app.GUI.board;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import inf112.skeleton.app.GUI.SoundPlayer;
import inf112.skeleton.app.GUI.player.MovableGUIRobot;
import inf112.skeleton.app.gameLogic.Player;
import inf112.skeleton.app.gameLogic.enums.Direction;
import inf112.skeleton.app.gameLogic.game.PlayerAction;

import java.util.List;

public class AnimationController {

    private DisplayLog displayLog;

    public AnimationController(DisplayLog displayLog) {
        this.displayLog = displayLog;
    }

    public SequenceAction getAllActionsSequenced(List<List<List<PlayerAction>>> allPlayerActions, List<Action> laserAnimations,
                                                 List<List<PlayerAction>> conveyorActions, RunnableAction postExecutionAction){

        SequenceAction allActionsSequenced = new SequenceAction();

        for(int i=0; i<allPlayerActions.size(); i++){

            // Player moves for the phase.
            List<List<PlayerAction>> cardActions = allPlayerActions.get(i);
            for (List<PlayerAction> parallelGUIActions : cardActions) {

                ParallelAction parallelAction = this.makeParallellAction(parallelGUIActions);
                allActionsSequenced.addAction(parallelAction);
                allActionsSequenced.addAction(new DelayAction(1));

            }

            ParallelAction conveyorParallelAction = this.makeConveyorParallellAction(conveyorActions.get(i));
            allActionsSequenced.addAction(conveyorParallelAction);
            allActionsSequenced.addAction(new DelayAction(1));

            // Laser animation for the phase.
            allActionsSequenced.addAction(laserAnimations.get(i));

        }

        allActionsSequenced.addAction(postExecutionAction);

        return allActionsSequenced;

    }

    public ParallelAction makeParallellAction(List<PlayerAction> parallelGUIActions){
        ParallelAction parallelAction = new ParallelAction();

        // Add every action to a parallel action.
        for (PlayerAction robotAction : parallelGUIActions) {

            Player currPlayer = robotAction.getPlayer();
            MovableGUIRobot currRobot = currPlayer.getRobot();
            Direction actionDir = robotAction.getActionDir();

            // Tells the robot the move it's going to do, and which direction. Returns the appropriate action.
            Action guiAction = currRobot.getGUIAction(robotAction.getAction().getActionType(), actionDir);
            guiAction.setTarget(currRobot);
            guiAction.setActor(currRobot);

            RunnableAction logUpdate = new RunnableAction(){
                @Override
                public void run() {
                    String printString = "PLAYER " + currPlayer.getName() + " DOES A " + robotAction.getAction().getDescription();
                    if(robotAction.getAction().getRotation() == null){
                        printString += " IN " + actionDir + " DIRECTION!";
                    }

                    displayLog.updateLog(printString);

                    //System.out.println(printString);
                }
            };

            parallelAction.addAction(logUpdate);
            parallelAction.addAction(guiAction);


        }

        return parallelAction;
    }

    public ParallelAction makeConveyorParallellAction(List<PlayerAction> parallelGUIActions){

        ParallelAction parallelAction = new ParallelAction();
        parallelAction.addAction(new RunnableAction(){
            @Override
            public void run() {
                displayLog.updateLog("-CONVEYORS ROLL-");
                //SoundPlayer.GameSound.ROTATE.playSound();
            }

            {

        }});

        parallelAction.addAction(this.makeParallellAction(parallelGUIActions));

        return parallelAction;
    }


}
