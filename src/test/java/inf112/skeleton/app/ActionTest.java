package inf112.skeleton.app;

import inf112.skeleton.app.GUI.Player.Position;
import inf112.skeleton.app.Game.Action;
import inf112.skeleton.app.Game.Enum.ActionType;
import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.Enum.Rotation;
import inf112.skeleton.app.Game.Game;
import inf112.skeleton.app.Game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ActionTest {

    private Player player;
    private Game game;

    @Before
    public void setupActionTest(){
        player = new Player(new Position(3,3), Direction.NORTH, 3);
        game = new Game();
    }

    @Test
    public void testActionDo1Damage(){
        assertEquals(3, player.getHealth());
        Action action = new Action(player, ActionType.DAMAGE,null,1);
        game.addActionToList(action);
        game.doAction();
        assertEquals(2, player.getHealth());
    }

    @Test
    public void testActionTurnLeft(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.L,1);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.WEST, player.getDirection());
    }

    @Test
    public void testActionTurnRight(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.R,1);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.EAST, player.getDirection());
    }

    @Test
    public void testActionTurnU(){
        assertEquals(Direction.NORTH, player.getDirection());
        Action action = new Action(player, ActionType.TURN, Rotation.U,1);
        game.addActionToList(action);
        game.doAction();
        assertEquals(Direction.SOUTH, player.getDirection());
    }

}
