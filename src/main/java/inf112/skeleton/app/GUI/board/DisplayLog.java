package inf112.skeleton.app.GUI.board;

public class DisplayLog {

    private String log;

    public DisplayLog(){
        this.log = "";
    }

    public void updateLog(String input){
        this.log +=input + " \n";
    }

    public void refreshLog(){
        this.log = "";
    }

    public String getLog(){
        return this.log;
    }

}
