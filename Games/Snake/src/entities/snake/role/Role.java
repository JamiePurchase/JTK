/*
 * Snake
 * Jamie Purchase
 */
package entities.snake.role;

import entities.snake.SnakeAction;
import entities.snake.SnakeRole;
import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class Role
{
    private String name;
    private SnakeRole type;
    private BufferedImage imageSnake, imagePortrait;
    private ArrayList<SnakeAction> actionArray;
    private int actionActive;
    // NOTE: resistence/weakness/status?
    
    public Role(String name, int image, SnakeRole type)
    {
        this.name = name;
        this.type = type;
        this.imageSnake = Drawing.getImageResource("resources/gfx/snake/snake" + image + ".png");
        this.imagePortrait = Drawing.getImageResource("resources/gfx/interface/snake/portraitBkg" + image + ".png");
        this.actionArray = new ArrayList();
        this.actionActive = 0;
    }
    
    public void actionAdd(SnakeAction newAction)
    {
        this.actionArray.add(newAction);
    }
    
    public void actionNext()
    {
        this.actionActive += 1;
        if(this.actionActive > this.actionArray.size() - 1) {this.actionActive = 0;}
    }
    
    public SnakeAction getActionActive()
    {
        return this.actionArray.get(this.actionActive);
    }
    
    public BufferedImage getImagePortrait()
    {
        return this.imagePortrait;
    }
    
    public BufferedImage getImageSnake()
    {
        return this.imageSnake;
    }
    
    public SnakeRole getType()
    {
        return this.type;
    }

}