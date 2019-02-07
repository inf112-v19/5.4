package inf112.skeleton.app.Game.board;

import inf112.skeleton.app.Enum.Direction;
import inf112.skeleton.app.board.pieces.Wall;
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
                            case "NorthWall":
                                System.out.println("Making northfacing wall!");
                                new Wall(Direction.NORTH);
                                break;
                            case "EastWall":
                                System.out.println("Making eastfacing wall!");
                                new Wall(Direction.EAST);
                                break;
                            case "SouthWall":
                                System.out.println("Making southfacing wall!");
                                new Wall(Direction.SOUTH);
                                break;
                            case "WestWall":
                                System.out.println("Making westfacing wall!");
                                new Wall(Direction.NORTH);
                                break;
                            case "ConveyorNorth":
                                System.out.println("Making northfacing conveyor!");
                                new Wall(Direction.NORTH);
                                break;
                            case "ConveyorEast":
                                System.out.println("Making eastfacing conveyor!");
                                new Wall(Direction.NORTH);
                                break;
                            case "ConveyorSouth":
                                System.out.println("Making southfacing conveyor!");
                                new Wall(Direction.NORTH);
                                break;
                            case "ConveyorWest":
                                System.out.println("Making westfacing conveyor!");
                                new Wall(Direction.NORTH);
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
