/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package engine.display;

import static engine.Application.appWidth;
import gfx.GFX;
import gfx.StyleColour;
import gfx.StyleText;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class DisplayTitlebar extends DisplayTitlebarAbstract
{
    private StyleColour textColour;
    private StyleText textStyle;
    private StyleColour bkgFill, bkgBorder;
    
    public DisplayTitlebar(Rectangle area, String textValue, StyleColour textColour, StyleText textStyle, StyleColour bkgFill, StyleColour bkgBorder)
    {
        super(new Rectangle(0, 0, appWidth, 30), textValue);
        this.textColour = textColour;
        this.textStyle = textStyle;
        this.bkgFill = bkgFill;
        this.bkgBorder = bkgBorder;
    }
    
    public void render(Graphics g)
    {
        GFX.drawRect(g, this.area, this.bkgFill, true);
        GFX.drawRect(g, this.area, this.bkgBorder, false);
        GFX.write(g, this.textValue, this.area.x + 10, this.area.y + 20, "LEFT", this.textStyle, this.textColour);
    }
    
}