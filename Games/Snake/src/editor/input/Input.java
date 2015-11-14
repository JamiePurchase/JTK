/*
 * Snake
 * Jamie Purchase
 */
package editor.input;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public abstract class Input
{
    private String key;
    public Rectangle zone;
    
    public Input(String key, Rectangle zone)
    {
        this.key = key;
        this.zone = zone;
    }
    
    public String getKey()
    {
        return this.key;
    }
    
    public abstract void render(Graphics g);
    
}