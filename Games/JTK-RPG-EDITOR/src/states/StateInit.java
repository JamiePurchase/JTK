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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateInit extends StateAbstract
{
    private int loadTick;

    public StateInit()
    {
        super("INIT");
        this.loadTick = 0;
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
        // Background
        GFX.drawRect(g, Application.getAppArea(), "BLACK", true);
        
        // Title
        GFX.writeShadow(g, "VENTURE", Application.getScreenCenterX(), 350, 1, "CENTER", "HEADER1", "HEADER1", "HEADER1_SHADOW");
        GFX.write(g, "loading", Application.getScreenCenterX(), 440, "CENTER", "HEADER2", "HEADER1");
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
        this.loadTick += 1;
        if(this.loadTick >= 6) {Application.stateNew(new StateEditor());}
    }
    
}