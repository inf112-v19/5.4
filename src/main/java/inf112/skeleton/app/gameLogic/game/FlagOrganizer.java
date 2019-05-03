package inf112.skeleton.app.gameLogic.game;

import inf112.skeleton.app.GUI.player.Position;

import java.io.Serializable;
import java.util.HashMap;

public class FlagOrganizer implements Serializable {

    //Class for keeping track of flags
    //Class is singleton to avoid having multiple of flags.
    //The flags are added to a HashMap to avoid multiple.

    private static FlagOrganizer single_instance = null;

    private HashMap<Integer, Position> flagSet;

    private FlagOrganizer() {
        this.flagSet = new HashMap<>();
    }

    public void setFlagAtPos(int i, Position pos) {
        if (!flagSet.containsKey(i)) {
            flagSet.put(i, pos);
        } else {
            System.out.println("This flag has already been added");
        }
    }

    public int getNumberOfFlags() {
        return flagSet.size();
    }

    public Position getFlagPos(int i) {
        return flagSet.get(i);
    }

    public static FlagOrganizer getInstance() {
        if (single_instance == null)
            single_instance = new FlagOrganizer();

        return single_instance;
    }
}
