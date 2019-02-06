package inf112.skeleton.app.Game.board;

import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();

    public void generateJsonBoard() {

        try {
            Object boardFile = parser.parse(new FileReader("C:\\Users\\motre\\IdeaProjects\\jsonParseTest\\JsonParseTest\\src\\main\\java\\JSONBoard.json"));
            JSONObject jsonBoardFile = (JSONObject) boardFile;
            System.out.println(jsonBoardFile);
            for (int x = 0; x <= 9; x++) {
                for (int y = 0; y <= 9; y++) {
                    String intX = Integer.toString(x);
                    String intY = Integer.toString(y);

                    JSONObject xCord = (JSONObject) jsonBoardFile.get(intX);
                    JSONArray yCord = (JSONArray) xCord.get(intY);

                    System.out.println(yCord);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
