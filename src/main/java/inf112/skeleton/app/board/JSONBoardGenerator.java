package inf112.skeleton.app.board;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONBoardGenerator {
    JSONParser parser = new JSONParser();
    public void generateBoard(String filePath) {
        try {
            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonBoard = (JSONObject) obj;
            System.out.print(jsonBoard);
            String y1 = (String) jsonBoard.get("y1");
            System.out.println(y1);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
