/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package element.input;

import element.ElementSelect;
import element.container.ContainerAbstract;
import gfx.GFX;
import gfx.StyleColour;
import gfx.StyleText;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class Textbox extends ElementSelect
{
    private String value;
    private int limit;
    
    public Textbox(String ref, ContainerAbstract parent, int posX, int posY, String value, int limit)
    {
        super(ref, parent, posX, posY, 200, 50);
        this.value = value;
        this.limit = limit;
    }
    
    public void inputKey(KeyEvent e)
    {
        //
    }
    
    public void inputMouse(MouseEvent e)
    {
        this.parent.selectElement(this);
    }
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getArea(), StyleColour.INPUT_BKG, true);
        
        // Border
        GFX.drawRect(g, null, StyleColour.INPUT_BKG, true);
        
        // Text
        GFX.write(g, this.value, this.getArea().x + 25, this.getArea().y + 15, "LEFT", StyleText.INPUT_STANDARD, StyleColour.INPUT_TEXT);
    }
    
    public void tick()
    {
        //
    }
    
}