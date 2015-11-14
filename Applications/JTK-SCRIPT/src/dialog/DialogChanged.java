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
public class DialogChanged extends DialogAbstract
{
    
    public DialogChanged()
    {
        super("DIALOG_CHANGED", 400, 100);
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
        GFX.write(g, "There are unsaved changes to the current file.", this.getArea().x + 25, this.getArea().y + 25, "LEFT", "DIALOG_STANDARD", "DIALOG_TEXT");
        GFX.write(g, "Would you like to save?", this.getArea().x + 25, this.getArea().y + 65, "LEFT", "DIALOG_STANDARD", "DIALOG_TEXT");
    }
    
    public void tickDialog()
    {
        //
    }

}