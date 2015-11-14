/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package input;

import engine.Application;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jamie
 */
public class InputMouse extends MouseAdapter implements MouseMotionListener
{
    private Point mousePoint;
    private boolean eventNew = false;
    private int idleTick = 0;
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        Application.setMousePoint(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        this.eventNew = true;
    }
    
    @Override
    public void mouseReleased (MouseEvent e)
    {
        Application.inputMouse(e);
    }
    
    public void setEventDone()
    {
        this.eventNew = false;
    }
    
    private void setIdleTick()
    {
        this.idleTick = 0;
    }
    
    public void tick()
    {
        this.idleTick ++;
    }

}