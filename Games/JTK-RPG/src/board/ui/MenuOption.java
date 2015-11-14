/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.ui;

/**
 *
 * @author Jamie
 */
public class MenuOption
{
    private MenuAbstract menu;
    private String ref, value;
    private int posX, posY;
    
    public MenuOption(MenuAbstract menu, String ref, String value, int posX, int posY)
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
    
    public String getValue()
    {
        return this.value;
    }
    
}