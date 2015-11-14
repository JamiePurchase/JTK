/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package toolbar;

import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class ToolbarDivider
{
    private ToolbarAbstract toolbar;
    private int posX;
    
    public ToolbarDivider(ToolbarAbstract toolbar, int posX)
    {
        this.toolbar = toolbar;
        this.posX = this.toolbar.getArea().x + posX;
    }
    
    public void render(Graphics g)
    {
        GFX.drawLine(g, this.posX, this.toolbar.getArea().y, this.posX, this.toolbar.getArea().y + this.toolbar.getArea().height, this.toolbar.getBorderColour());
    }
    
}