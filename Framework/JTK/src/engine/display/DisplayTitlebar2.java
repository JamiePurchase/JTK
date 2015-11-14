/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package engine.display;

import console.Output;
import engine.Application;
import static engine.Application.appWidth;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class DisplayTitlebar2 extends DisplayTitlebarAbstract
{
    private Rectangle buttonClose;
    private String bkgBorder, bkgFill, textColour, textStyle;
    
    public DisplayTitlebar2(String textValue, String textColour, String textStyle, String bkgFill, String bkgBorder)
    {
        super(new Rectangle(0, 0, appWidth, 30), textValue);
        this.textColour = textColour;
        this.textStyle = textStyle;
        this.bkgFill = bkgFill;
        this.bkgBorder = bkgBorder;
        this.buttonClose = new Rectangle(appWidth - 30, 4, 22, 22);
        
        // Debug
        /*
        Output.print("DisplayTitlebar2");
        Output.print("  title = " + this.textValue);
        Output.print("");
        */
    }
    
    @Override
    public boolean clickClose(Point point)
    {
        if(this.buttonClose.contains(point)) {return true;}
        return false;
    }
    
    @Override
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.area, this.bkgFill, true);
        
        // Border
        GFX.drawRect(g, this.area, this.bkgBorder, false);
        
        // Title
        if(this.textValue != null) {GFX.write(g, this.textValue, this.area.x + 20, this.area.y + 24, "LEFT", this.textStyle, this.textColour);}
        
        // Close
        if(this.buttonClose.contains(Application.getMousePoint())) {GFX.drawRect(g, this.buttonClose, "TITLEBAR_BUTTON_HOVER", true);}
        else {GFX.drawRect(g, this.buttonClose, "TITLEBAR_BUTTON", true);}
        GFX.write(g, "x", (int) this.buttonClose.getCenterX(), this.buttonClose.y + 16, "CENTER", "TITLEBAR_BUTTON", this.textColour);
        GFX.drawRect(g, this.buttonClose, "BLACK", false);
    }
    
}