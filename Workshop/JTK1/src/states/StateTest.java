/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateTest extends StateAbstract
{

    public StateTest()
    {
        super("TEST");
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        System.out.println("INPUT KEY: " + e.toString());
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        System.out.println("INPUT MOUSE: " + e.getPoint().toString());
    }

    @Override
    public void render(Graphics g)
    {
        //
    }
    
    public void stateChange()
    {
        System.out.println("STATE CHANGE");
    }
    
    public void stateFinish()
    {
        System.out.println("STATE FINISH");
    }

    @Override
    public void tick()
    {
        //
    }

}