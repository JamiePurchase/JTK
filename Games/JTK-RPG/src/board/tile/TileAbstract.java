/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.tile;

/**
 *
 * @author Jamie
 */
public class TileAbstract
{
    public String sheet;
    public int x, y;
    
    public TileAbstract(String image, int x, int y)
    {
        this.sheet = image;
        this.x = x;
        this.y = y;
    }
    
    public String getData()
    {
        return this.sheet + "|" + this.x + "|" + this.y;
    }
    
    public String getName()
    {
        String[] path = this.sheet.split("\\/");
        String name = path[path.length - 1];
        return name.substring(0, name.length() - 4);
    }
    
}