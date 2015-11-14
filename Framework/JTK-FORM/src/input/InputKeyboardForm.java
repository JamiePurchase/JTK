/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package input;

import engine.FormApplication;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jamie
 */
public class InputKeyboardForm extends InputKeyboard
{
    private boolean eventNew = false;
    
    public boolean getEventNew()
    {
        return this.eventNew;
    }
    
    public boolean isAlphabetical(KeyEvent e)
    {
        if(Character.isAlphabetic(e.getKeyChar())) {return true;}
        return false;
    }
    
    public boolean isAlphanumeric(KeyEvent e)
    {
        if(isAlphabetical(e)) {return true;}
        if(isNumeric(e)) {return true;}
        return false;
    }
    
    public boolean isNumeric(KeyEvent e)
    {
        if(Character.isDigit(e.getKeyChar())) {return true;}
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //this.eventNew = true;
        //Application.inputKey(e);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        this.eventNew = true;
        FormApplication.inputKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //
    }
    
    public void setEventDone()
    {
        this.eventNew = false;
    }
    
}