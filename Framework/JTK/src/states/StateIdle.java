/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateIdle extends StateAbstract
{

    public StateIdle()
    {
        super("IDLE");
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        System.out.println("INPUT KEY: " + e.toString());
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