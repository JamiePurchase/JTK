/**
 * Venture Jamie Purchase JTK-RPG 07/11/2015
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
        GFX.drawImage(g, "resources/gfx/menu/bkg1.png", 0, 0);
        
        // Title
        GFX.writeShadow(g, "VENTURE", Application.getScreenCenterX(), 120, 2, "CENTER", "HEADER1", "BLACK", "HEADER1_SHADOW");
        
        // Loading
        GFX.writeShadow(g, "loading...", Application.getScreenCenterX(), 320, 1, "CENTER", "HEADER2", "BLACK", "HEADER1_SHADOW");
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
        if(this.loadTick >= 6) {Application.stateNew(new StateTitle());}
    }
    
}