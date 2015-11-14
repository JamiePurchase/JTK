/*
 * JTK-SCRIPT
 * 13/11/2015
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package dialog;

import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import tools.RectangleTool;

/**
 *
 * @author Jamie
 */
public abstract class DialogAbstract
{
    private String ref;
    private int width, height;
    
    public DialogAbstract(String ref, int width, int height)
    {
        this.ref = ref;
        this.width = width;
        this.height = height;
    }
    
    protected Rectangle getArea()
    {
        return new Rectangle((int) Application.getAppAreaDraw().getCenterX() - (width / 2), (int) Application.getAppAreaDraw().getCenterY() - (height / 2), width, height);
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public abstract void inputMouse(MouseEvent e);
    
    public void render(Graphics g)
    {
        this.renderFrame(g);
        this.renderDialog(g);
    }
    
    protected abstract void renderDialog(Graphics g);
    
    private void renderFrame(Graphics g)
    {
        // Shadow
        GFX.drawRect(g, RectangleTool.offset(this.getArea(), 2, 2), "BLACK", true);
        
        // Background
        GFX.drawRect(g, this.getArea(), "WHITE", true);
        
        // Border
        GFX.drawRect(g, this.getArea(), "BLACK", false);
    }
    
    private void tick()
    {
        this.tickDialog();
    }
    
    protected abstract void tickDialog();

}