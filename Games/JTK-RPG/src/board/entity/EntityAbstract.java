/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package board.entity;

import board.BoardAbstract;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jamie
 */
public abstract class EntityAbstract
{
    protected BoardAbstract board;
    private String ref;
    protected int posX, posY, posW, posH;
    
    public EntityAbstract(BoardAbstract board, String ref, int posX, int posY, int posW, int posH)
    {
        this.board = board;
        this.ref = ref;
        this.posX = posX;
        this.posY = posY;
        this.posW = posW;
        this.posH = posH;
    }
    
    public Rectangle getBoardArea()
    {
        return new Rectangle(posX, posY, posW, posH);
    }
    
    public abstract void render(Graphics g);
    
    public abstract void tick();
    
}