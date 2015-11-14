/*
 * Snake
 * Jamie Purchase
 */
package game.gateway;

import debug.Console;
import entities.snake.Snake;
import files.Filesystem;
import game.Game;
import java.util.ArrayList;
import world.Location;

/**
 *
 * @author Jamie
 */
public class GameGateway
{

    public static Game load(String game)
    {
        // Load Data
        ArrayList<String> gameData = loadData("resources/data/game/" + game + ".jtk");
        
        // Create Game
        return loadGame(gameData);
    }
    
    private static ArrayList<String> loadData(String path)
    {
        try {path = ClassLoader.getSystemResource(path).getPath();}
        catch(Exception ex) {Console.error(ex);}
        path = path.substring(1, path.length()).replaceAll("%20", " ");
        return Filesystem.loadFile(path);
    }
    
    private static Game loadGame(ArrayList<String> data)
    {
        // TEMP
        System.out.println(data);
        
        // New Game
        int snakeX = 15;
        int snakeY = 15;
        Game game = new Game(new Location(snakeX, snakeY));
        
        // World
        //
        
        // Snake
        
        
        // Return Game
        return game;
    }

}