/*
 * JTK-SCRIPT
 * 13/11/2015
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package dialog;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class DialogOpen extends DialogAbstract
{
    
    public DialogOpen()
    {
        super("DIALOG_OPEN", 600, 400);
    }
    
    public void inputKey(KeyEvent e)
    {
        //
    }
    
    public void inputMouse(MouseEvent e)
    {
        //
    }
    
    public void renderDialog(Graphics g)
    {
        GFX.write(g, "Open", this.getArea().x + 25, this.getArea().y + 25, "LEFT", "DIALOG_STANDARD", "DIALOG_TEXT");
    }
    
    public void tickDialog()
    {
        //
    }

}