/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package input;

import engine.FormApplication;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class InputMouseForm extends InputMouse
{
    private Point mousePoint;
    private boolean eventNew = false;
    private int idleTick = 0;
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        FormApplication.setMousePoint(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        this.eventNew = true;
    }
    
    @Override
    public void mouseReleased (MouseEvent e)
    {
        FormApplication.inputMouse(e);
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