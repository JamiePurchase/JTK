/*
 * Snake
 * Jamie Purchase
 */
package states;

import app.Engine;
import app.input.InputKey;
import game.Game;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import world.Location;

/**
 *
 * @author Jamie
 */
public class StateGame extends State
{
    private Game game;
    
    public StateGame()
    {
        this.game = new Game(new Location(15, 15));
        //this.game = (Game) Engine.getAppVariable("PRELOAD_GAME");
    }

    public void inputKeyPress(String key)
    {
        //System.out.println("StateGame -> inputKeyPress(" + key + ")");
        
        // Commands
        if(key.equals("BACKSPACE")) {this.game.input(InputKey.COMMAND_BACKSPACE);}
        if(key.equals("ESCAPE")) {this.game.input(InputKey.COMMAND_ESCAPE);}
        if(key.equals("SPACE")) {this.game.input(InputKey.COMMAND_SPACE);}
        
        // Arrows
        if(key.equals("DOWN")) {this.game.input(InputKey.ARROW_DOWN);}
        if(key.equals("LEFT")) {this.game.input(InputKey.ARROW_LEFT);}
        if(key.equals("RIGHT")) {this.game.input(InputKey.ARROW_RIGHT);}
        if(key.equals("UP")) {this.game.input(InputKey.ARROW_UP);}
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        /*
        System.out.println("StateGame -> inputKeyType(" + key + ")");
        
        // Commands
        if(key.equals("ESCAPE")) {this.game.input(InputKey.COMMAND_ESCAPE);}
        if(key.equals("SPACE")) {this.game.input(InputKey.COMMAND_SPACE);}
        
        // Arrows
        if(key.equals("DOWN")) {this.game.input(InputKey.ARROW_DOWN);}
        if(key.equals("LEFT")) {this.game.input(InputKey.ARROW_LEFT);}
        if(key.equals("RIGHT")) {this.game.input(InputKey.ARROW_RIGHT);}
        if(key.equals("UP")) {this.game.input(InputKey.ARROW_UP);}
        */
    }

    public void inputMouseClickL(MouseEvent e)
    {
        Engine.setState(new StateGame());
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }

    public void render(Graphics g)
    {
        this.game.render(g);
    }

    public void tick()
    {
        this.game.tick();
    }

}