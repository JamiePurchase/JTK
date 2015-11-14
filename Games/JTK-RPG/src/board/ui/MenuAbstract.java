/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.ui;

import gfx.GFX;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class MenuAbstract
{
    private ArrayList<MenuOption> option;
    private int cursor;
    
    public MenuAbstract()
    {
        this.option = new ArrayList();
        this.cursor = 0;
    }
    
    public void addOption(String ref, String value, int posX, int posY)
    {
        this.option.add(new MenuOption(this, ref, value, posX, posY));
    }
    
    public String getOptionRef()
    {
        return this.option.get(this.cursor).getRef();
    }
    
    public void inputKey(KeyEvent e)
    {
        // Arrow: Up
        if(e.getKeyCode() == KeyEvent.VK_UP && this.cursor > 0) {this.cursor -= 1;}
        
        // Arrow: Down
        if(e.getKeyCode() == KeyEvent.VK_DOWN && this.cursor < (this.option.size() - 1)) {this.cursor += 1;}
    }
    
    public void render(Graphics g)
    {
        // Options
        for(int x = 0; x < this.option.size(); x++)
        {
            GFX.write(g, this.option.get(x).getValue(), this.option.get(x).getPosX(), this.option.get(x).getPosY(), "LEFT", "MENU_OPTION", "BLACK");
        }
        
        // Cursor
        GFX.write(g, ">", this.option.get(this.cursor).getPosX() - 25, this.option.get(this.cursor).getPosY(), "LEFT", "MENU_OPTION", "BLACK");
    }
    
    public void tick()
    {
        //
    }
    
}