/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.zone;

import board.BoardAbstract;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public class ZoneAbstract
{
    private BoardAbstract board;
    private String ref;
    private Rectangle area;
    private boolean solid;

    public ZoneAbstract(BoardAbstract board, String ref, int posX1, int posY1, int posX2, int posY2, boolean solid)
    {
        this.board = board;
        this.ref = ref;
        this.area = new Rectangle(posX1, posY1, (posX2 + 1) - posX1, (posY2 + 1) - posY1);
        this.solid = solid;
    }
    
    public Rectangle getBoardArea()
    {
        return new Rectangle(this.area.x * 32, this.area.y * 32, this.area.width * 32, this.area.height * 32);
    }
    
    public String getData()
    {
        return this.area.x + "|" + this.area.y + "|" + this.area.width + "|" + this.area.height + "|" + this.solid;
    }
    
    public Rectangle getRenderArea()
    {
        return new Rectangle(this.board.getRenderPosX(this.area.x * 32), this.board.getRenderPosY(this.area.y * 32), this.area.width * 32, this.area.height * 32);
    }
    
    public Rectangle getTileArea()
    {
        return this.area;
    }
    
    public boolean isSolid()
    {
        return this.solid;
    }
    
}