package inf112.skeleton.app.Game.board;

import inf112.skeleton.app.Game.Enum.Direction;
import inf112.skeleton.app.Game.board.activeCells.Conveyor;
import inf112.skeleton.app.Game.board.pieces.Flag;
import inf112.skeleton.app.Game.board.pieces.Wall;
import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONBoardGenerator {

    JSONParser parser = new JSONParser();


    /**
     * The main method for generating a List/Board of pieces from a JSON document.
     * @param filepath The path of the JSON file you want to parse.
     * @return  A List of lists containing the parsed pieces.
     */
    public List<List<IPiece>> generateJsonBoard (String filepath) {

        List<List<IPiece>> jsonBoardPieceList = new ArrayList<List<IPiece>>();

        try {

            Object boardFile = parser.parse(new FileReader(filepath));
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
                        ArrayList<IPiece> innerList = new ArrayList<IPiece>();
                        String temp = iterator.next();
                        System.out.println(temp);
                        switch (temp) {
                            case "Hole":
                                System.out.println("Making Hole!");
                                break;
                            case "NorthWall":
                                System.out.println("Making northfacing wall!");
                                innerList.add(new Wall (Direction.NORTH));
                                break;
                            case "EastWall":
                                System.out.println("Making eastfacing wall!");
                                innerList.add(new Wall(Direction.EAST));
                                break;
                            case "SouthWall":
                                System.out.println("Making southfacing wall!");
                                innerList.add(new Wall(Direction.SOUTH));
                                break;
                            case "WestWall":
                                System.out.println("Making westfacing wall!");
                                innerList.add(new Wall(Direction.NORTH));
                                break;
                            case "ConveyorNorth":
                                System.out.println("Making northfacing conveyor!");
                                new Conveyor(Direction.NORTH,1);
                                break;
                            case "ConveyorEast":
                                System.out.println("Making eastfacing conveyor!");
                                new Conveyor(Direction.EAST,1);
                                break;
                            case "ConveyorSouth":
                                System.out.println("Making southfacing conveyor!");
                                new Conveyor(Direction.SOUTH,1);
                                break;
                            case "ConveyorWest":
                                System.out.println("Making westfacing conveyor!");
                                new Conveyor(Direction.WEST,1);
                                break;
                            case "FlagOne":
                                System.out.println("Making flag number 1");
                                new Flag(1);
                                break;
                            case "FlagTwo":
                                System.out.println("Making flag number 2");
                                new Flag(2);
                                break;
                            case "FlagThree":
                                System.out.println("Making flag number 3");
                                new Flag (3);
                                break;
                        }
                        jsonBoardPieceList.add(innerList);
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
        return jsonBoardPieceList;
    }
}
