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
public abstract class StateAbstract
{
    private final String ref;
    
    public StateAbstract(String ref)
    {
        this.ref = ref;
    }
    
    public String ref()
    {
        return this.ref;
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public abstract void inputMouse(MouseEvent e);
    
    public abstract void render(Graphics g);
    
    public abstract void stateChange();
    
    public abstract void stateFinish();
    
    public abstract void tick();

}