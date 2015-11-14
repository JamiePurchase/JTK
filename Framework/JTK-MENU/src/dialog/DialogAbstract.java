/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package dialog;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public abstract class DialogAbstract
{
    protected String ref, titleCaption, fill, border, titleFill;
    protected Rectangle area, titleArea;
    protected boolean titleActive;
    
    public DialogAbstract(String ref, Rectangle area)
    {
        this.ref = ref;
        this.area = area;
        this.titleCaption = "";
        this.titleActive = false;
        this.titleArea = null;
        this.titleFill = "";
        this.fill = "TOOLBAR_BKG";
        this.border = "TOOLBAR_BORDER";
    }
    
    public DialogAbstract(String ref, Rectangle area, String titleCaption, String titleFill)
    {
        this.ref = ref;
        this.area = area;
        this.titleCaption = titleCaption;
        this.titleActive = true;
        this.titleArea = new Rectangle(area.x, area.y, area.width, 30);
        this.titleFill = titleFill;
        this.fill = "TOOLBAR_BKG";
        this.border = "TOOLBAR_BORDER";
    }
    
    protected Rectangle getAreaContent()
    {
        if(titleActive) {return new Rectangle(this.area.x, this.area.y + 30, this.area.width, this.area.height - 30);}
        else {return this.area;}
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public abstract void inputMouse(MouseEvent e);
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.area, this.fill, true);
        
        // Title
        if(this.titleActive) {this.renderTitle(g);}
        
        // Border
        GFX.drawRect(g, this.area, this.border, false);
        
        // Content
        this.renderContent(g);
    }
    
    protected abstract void renderContent(Graphics g);
    
    private void renderTitle(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.titleArea, this.titleFill, true);
        
        // Border
        GFX.drawRect(g, this.titleArea, this.border, false);
        
        // Title
        GFX.write(g, this.titleCaption, this.titleArea.x + 20, this.titleArea.y + 24, "LEFT", "TITLEBAR_HEADER", "BLACK");
    }
    
    public abstract void tick();
    
}