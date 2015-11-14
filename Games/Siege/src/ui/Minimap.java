/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package ui;

import game.GameData;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class Minimap
{
    private GameData game;
    private final Rectangle area = new Rectangle(1146, 548, 200, 200);
    
    public Minimap(GameData game)
    {
        this.game = game;
    }
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.area, "INFO_BKG", 0.5f);
        
        // Border
        GFX.drawRect(g, this.area, "INFO_BORDER", false);
    }

}