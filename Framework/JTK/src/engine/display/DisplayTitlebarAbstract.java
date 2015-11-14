/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package engine.display;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public abstract class DisplayTitlebarAbstract
{
    protected Rectangle area;
    protected String textValue;
    
    public DisplayTitlebarAbstract(Rectangle area, String textValue)
    {
        this.area = area;
        this.textValue = textValue;
    }
    
    public boolean clickClose(Point point)
    {
        return false;
    }
    
    public abstract void render(Graphics g);
    
    public void setTitle(String newValue)
    {
        this.textValue = newValue;
    }
    
}