package inf112.skeleton.app.GUI.board;

public class DisplayLog {

    private String log;

    public DisplayLog(){
        this.log = "LOG:\n";
    }

    public void updateLog(String input){
        this.log +=input + " \n";
    }

    public void clearLog(){
        this.log = "LOG:\n";
    }

    public String getLog(){
        String returnString = new String(this.log);
        //this.log = "";
        System.out.println("RETURNSTRING");
        System.out.println(returnString);
        return returnString;
    }



}
