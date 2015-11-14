/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package toolbar;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import menu.MenuItem;

/**
 *
 * @author Jamie
 */
public class ToolbarAbstract
{
    private String ref, barFill, barBorder, itemText, itemFont, itemHighlight, labelColour, labelFont;
    private Rectangle area;
    private ArrayList<ToolbarItem> itemList;
    private ArrayList<ToolbarLabel> labelList;
    private ArrayList<ToolbarDivider> dividerList;
    private boolean borderTop, borderRight, borderBottom, borderLeft;
    
    public ToolbarAbstract(String ref, Rectangle area, String barFill, String barBorder, String itemColour, String itemFont, String itemHighlight)
    {
        this.ref = ref;
        this.area = area;
        this.itemList = new ArrayList();
        this.barFill = barFill;
        this.barBorder = barBorder;
        this.itemText = itemColour;
        this.itemFont = itemFont;
        this.itemHighlight = itemHighlight;
        this.labelList = new ArrayList();
        this.labelColour = "BLACK";
        this.labelFont = "STANDARD";
        this.dividerList = new ArrayList();
        this.borderTop = false;
        this.borderRight = false;
        this.borderBottom = true;
        this.borderLeft = false;
    }
    
    public void addDivider(int posX)
    {
        this.dividerList.add(new ToolbarDivider(this, posX));
    }
    
    public void addLabel(String caption, int posX)
    {
        this.labelList.add(new ToolbarLabel(this, caption, posX));
    }
    
    public void addItem(String ref, String caption)
    {
        this.itemList.add(new ToolbarItem(this, ref, caption, 20 + (100 * this.itemList.size())));
    }
    
    public void addItem(String ref, String icon, int posX, int posY, int size)
    {
        this.itemList.add(new ToolbarItem(this, ref, icon, posX, posY, size));
    }
    
    public Rectangle getArea()
    {
        return this.area;
    }
    
    public String getBorderColour()
    {
        return this.barBorder;
    }
    
    public String getFillColour()
    {
        return this.barFill;
    }
    
    public String getItemAt(Point point)
    {
        for(int x = 0; x < this.itemList.size(); x++)
        {
            if(this.itemList.get(x).getArea().contains(point)) {return this.itemList.get(x).getRef();}
        }
        return "";
    }
    
    public String getItemColour()
    {
        return this.itemText;
    }
    
    public String getItemFont()
    {
        return this.itemFont;
    }
    
    public String getItemHighlight()
    {
        return this.itemHighlight;
    }
    
    public String getLabelColour()
    {
        return this.labelColour;
    }
    
    public String getLabelFont()
    {
        return this.labelFont;
    }
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.area, this.barFill, true);
        
        // Items
        if(this.itemList.size() > 0) {this.renderItem(g);}
        
        // Labels
        if(this.labelList.size() > 0) {this.renderLabel(g);}
        
        // Dividers
        if(this.dividerList.size() > 0) {this.renderDivider(g);}
        
        // Border
        GFX.drawRectBorder(g, this.area, this.barBorder, this.borderTop, this.borderRight, this.borderBottom, this.borderLeft);
    }
    
    private void renderDivider(Graphics g)
    {
        for(int x = 0; x < this.dividerList.size(); x++)
        {
            this.dividerList.get(x).render(g);
        }
    }
    
    private void renderItem(Graphics g)
    {
        for(int x = 0; x < this.itemList.size(); x++)
        {
            this.itemList.get(x).render(g);
        }
    }
    
    private void renderLabel(Graphics g)
    {
        for(int x = 0; x < this.labelList.size(); x++)
        {
            this.labelList.get(x).render(g);
        }
    }
    
    public void setBorder(boolean top, boolean right, boolean bottom, boolean left)
    {
        this.borderTop = top;
        this.borderRight = right;
        this.borderBottom = bottom;
        this.borderLeft = left;
    }
    
    public void setLabelStyle(String newColour, String newFont)
    {
        this.labelColour = newColour;
        this.labelFont = newFont;
    }
    
    public void tick()
    {
        //
    }

}