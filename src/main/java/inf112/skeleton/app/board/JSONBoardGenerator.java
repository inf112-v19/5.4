package inf112.skeleton.app.board;

import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();

    public void generateJsonBoard() {

        try {
            Object boardFile = parser.parse(new FileReader("C:\\Users\\Morten\\IdeaProjects\\jsonParseTest\\jsonParseTesto\\src\\main\\java\\PackageMcGoo\\TestBoard.json"));
            JSONObject jsonBoardFile = (JSONObject) boardFile;
            System.out.println(jsonBoardFile);
            for (int x = 0; x <= 9; x++) {
                for (int y = 0; y <= 9; y++) {
                    String intX = Integer.toString(x);
                    String intY = Integer.toString(y);

                    JSONObject xCord = (JSONObject) jsonBoardFile.get(intX);
                    JSONArray yCord = (JSONArray) xCord.get(intY);
                    Iterator<String> iterator = yCord.iterator();
                    while (iterator.hasNext()) {
                        String temp = iterator.next();
                        System.out.println(temp);
                        switch (temp) {
                            case "Hole":
                                System.out.println("Making Hole!");
                                break;
                            case "WestWall":
                                System.out.println("Making westfacing wall!");
                                break;
                            case "SouthWall":
                                System.out.println("Making southfacing wall!");
                                break;
                            case "NorthWall":
                                System.out.println("Making northfacing wall!");
                                break;
                        }

                    }
                    //System.out.println(yCord);
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
