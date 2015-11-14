/**
 * Class Fusion
 */
package states;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateIntro extends StateAbstract
{
    //
    
    public StateIntro()
    {
        super("STATE_INTRO");
        //
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
        //
        GFX.write(g, "Welcome to Class Fusion, the quick way to build classes for ColdBox and ColdFusion from UML sketches.", 40, 80, "LEFT", "STANDARD", "BLACK");
        GFX.write(g, "Create a new project?", 90, 160, "LEFT", "STANDARD", "BLACK");
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