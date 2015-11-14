/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package toolbar;

import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class ToolbarItem
{
    private ToolbarAbstract toolbar;
    private String ref, caption, icon;
    private Rectangle area;
    
    public ToolbarItem(ToolbarAbstract toolbar, String ref, String caption, int posX)
    {
        this.toolbar = toolbar;
        this.ref = ref;
        this.caption = caption;
        this.icon = "";
        this.area = new Rectangle(posX, this.toolbar.getArea().y, 90, this.toolbar.getArea().height);
    }
    
    public ToolbarItem(ToolbarAbstract toolbar, String ref, String icon, int posX, int posY, int size)
    {
        this.toolbar = toolbar;
        this.ref = ref;
        this.caption = "";
        this.icon = icon;
        this.area = new Rectangle(this.toolbar.getArea().x + posX, this.toolbar.getArea().y + posY, size, size);
    }
    
    public Rectangle getArea()
    {
        return this.area;
    }
    
    public String getCaption()
    {
        return this.caption;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public void render(Graphics g)
    {
        // Background
        if(this.area.contains(Application.getMousePoint())) {GFX.drawRect(g, this.area, this.toolbar.getItemHighlight(), true);}
        else {GFX.drawRect(g, this.area, this.toolbar.getFillColour(), true);}
        
        // Caption
        if(this.caption.length() > 0)
        {
            GFX.write(g, this.caption, this.area.x + 10, this.area.y + 20, "LEFT", this.toolbar.getItemFont(), this.toolbar.getItemColour());
        }
        
        // Image
        if(this.icon.length() > 0)
        {
            GFX.drawImage(g, this.icon, this.area.x, this.area.y);
            //GFX.drawRect(g, this.area, this.toolbar.getBorderColour(), false);
        }
    }

}