/*
 * JTK-RPG-EDITOR
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states;

import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateEditor extends StateAbstract
{
    //

    public StateEditor()
    {
        super("EDITOR");
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        //
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        // Toolbar
        Rectangle toolbarArea = new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y, Application.getAppAreaDraw().width, 30);
        GFX.drawRect(g, toolbarArea, "TOOLBAR_BKG", true);
        GFX.drawRectBorder(g, toolbarArea, "BLACK", false, false, true, false);
        
        // Status Bar
        Rectangle statusArea = new Rectangle(Application.getAppAreaDraw().x, Application.getAppAreaDraw().y + Application.getAppAreaDraw().height - 30, Application.getAppAreaDraw().width, 30);
        GFX.drawRect(g, statusArea, "TOOLBAR_BKG", true);
        GFX.drawRectBorder(g, statusArea, "BLACK", true, false, false, false);
    }

    @Override
    public void stateChange()
    {
        //
    }

    @Override
    public void stateFinish()
    {
        //
    }

    @Override
    public void tick()
    {
        //
    }
    
}