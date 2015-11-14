/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package ui;

import engine.Application;
import game.GameData;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class DialogPause
{
    private GameData game;
    private final Rectangle area = new Rectangle(483, 175, 400, 418);
    
    public DialogPause(GameData game)
    {
        this.game = game;
    }
    
    public Rectangle getArea()
    {
        return this.area;
    }
    
    public void inputKey(KeyEvent e)
    {
        //
    }
    
    public void inputMouse(MouseEvent e)
    {
        // temp
        this.game.pause(false);
    }
    
    public void render(Graphics g)
    {
        // Shadow
        GFX.drawRect(g, new Rectangle(this.area.x + 3, this.area.y + 3, this.area.width, this.area.height), "BLACK", true);
        
        // Background
        GFX.drawRect(g, this.area, "INFO_BKG", true);
        
        // Border
        GFX.drawRect(g, this.area, "INFO_BORDER", false);
        
        // Title
        GFX.write(g, "PAUSE", (int) this.area.getCenterX(), this.area.y + 25, "CENTER", "INFO_STANDARD", "INFO_TEXT");
    }
    
}