/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package menu;

import gfx.GFX;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class MenuAbstract
{
    private String ref, itemColour, itemShadow, itemFont, cursorString;
    private ArrayList<MenuItem> itemList;
    private int pos, tickNow, tickFrame;
    
    public MenuAbstract(String ref)
    {
        this.ref = ref;
        this.itemList = new ArrayList();
        this.itemColour = "WHITE";
        this.itemShadow = "";
        this.itemFont = "MENU";
        this.cursorString = ">";
        this.pos = 0;
        this.tickNow = 0;
        this.tickFrame = 0;
    }
    
    public void addItem(String ref, String value, int posX, int posY)
    {
        this.itemList.add(new MenuItem(this, ref, value, posX, posY));
    }
    
    public String getItemColour()
    {
        return this.itemColour;
    }
    
    public String getItemFont()
    {
        return this.itemFont;
    }
    
    public String getItemRef()
    {
        return this.itemList.get(this.pos).getRef();
    }
    
    public String getItemShadow()
    {
        return this.itemShadow;
    }
    
    public void inputDown()
    {
        if(this.pos < this.itemList.size() - 1) {this.pos += 1;}
    }
    
    public void inputUp()
    {
        if(this.pos > 0) {this.pos -= 1;}
    }
    
    public void render(Graphics g)
    {
        // Items
        for(int x = 0; x < this.itemList.size(); x++)
        {
            this.itemList.get(x).render(g);
        }
        
        // Cursor
        //GFX.write(g, this.cursorString, this.itemList.get(this.pos).getPosX() - (2 * this.tickFrame), this.itemList.get(this.pos).getPosY(), "RIGHT", this.getItemFont(), this.getItemColour());
        GFX.drawImage(g, "resources/gfx/menu/cursor1.png", this.itemList.get(this.pos).getPosX() - 15 - (2 * this.tickFrame), this.itemList.get(this.pos).getPosY() - 20);
    }
    
    public void setCursorString(String cursor)
    {
        this.cursorString = cursor;
    }
    
    public void setItemColour(String newColour)
    {
        this.itemColour = newColour;
    }
    
    public void setItemFont(String newFont)
    {
        this.itemFont = newFont;
    }
    
    public void setItemShadow(String newShadow)
    {
        this.itemShadow = newShadow;
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow >= 12)
        {
            this.tickNow = 0;
            this.tickFrame += 1;
            if(this.tickFrame > 1) {this.tickFrame = 0;}
        }
    }
    
}