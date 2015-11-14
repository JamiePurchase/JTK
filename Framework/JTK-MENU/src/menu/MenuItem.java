/**
 * JTK-MENU Jamie Purchase 07/11/2015
 */
package menu;

import gfx.GFX;
import java.awt.Graphics;

/**
 *
 * @author Jamie
 */
public class MenuItem
{
    private MenuAbstract menu;
    private String ref, value;
    private int posX, posY;
    
    public MenuItem(MenuAbstract menu, String ref, String value, int posX, int posY)
    {
        this.menu = menu;
        this.ref = ref;
        this.value = value;
        this.posX = posX;
        this.posY = posY;
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public void render(Graphics g)
    {
        if(this.menu.getItemShadow() != "") {GFX.writeShadow(g, this.value, this.posX + 30, this.posY, 1, "LEFT", this.menu.getItemFont(), this.menu.getItemColour(), this.menu.getItemShadow());}
        else {GFX.write(g, this.value, this.posX + 30, this.posY, "LEFT", this.menu.getItemFont(), this.menu.getItemColour());}
    }

}