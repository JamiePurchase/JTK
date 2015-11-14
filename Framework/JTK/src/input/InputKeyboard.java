/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package input;

import engine.Application;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class InputKeyboard implements KeyListener
{
    private boolean eventNew = false;
    private ArrayList<String> holdList = new ArrayList();
    
    public boolean getEventNew()
    {
        return this.eventNew;
    }
    
    public ArrayList<String> getHoldList()
    {
        return this.holdList;
    }
    
    public boolean holdListContains(String find)
    {
        return this.holdList.contains(find);
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
    
    public boolean isSymbol(KeyEvent e)
    {
        if(Character.isDefined(e.getKeyChar())) {return true;}
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
        // Event
        this.eventNew = true;
        Application.inputKey(e);
        
        // Hold (add if not already listed)
        boolean holdAdd = true;
        for(int x = 0; x < this.holdList.size(); x++)
        {
            if(this.holdList.get(x).equals("KEY|" + e.getKeyCode())) {holdAdd = false;}
        }
        if(holdAdd) {this.holdList.add("KEY|" + e.getKeyCode());}
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Event
        
        // Hold (remove from list)
        for(int x = 0; x < this.holdList.size(); x++)
        {
            if(this.holdList.get(x).equals("KEY|" + e.getKeyCode())) {this.holdList.remove(x);}
        }
    }
    
    public void setEventDone()
    {
        this.eventNew = false;
    }
    
}