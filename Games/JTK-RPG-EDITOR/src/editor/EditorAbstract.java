/*
 * JTK-RPG-EDITOR
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package editor;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public abstract class EditorAbstract
{
    
    public EditorAbstract()
    {
        //
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public abstract void inputMouse(MouseEvent e);
    
    public abstract void render(Graphics g);
    
    public abstract void tick();
    
}