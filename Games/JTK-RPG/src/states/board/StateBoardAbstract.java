/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package states.board;

import board.BoardAbstract;
import engine.Application;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import states.StateAbstract;

/**
 *
 * @author Jamie
 */
public abstract class StateBoardAbstract extends StateAbstract
{
    // Board
    protected BoardAbstract board;

    public StateBoardAbstract(String ref)
    {
        // Board
        super(ref);
        this.board = null;
    }

    public abstract void inputKey(KeyEvent e);

    public abstract void inputMouse(MouseEvent e);
    
    public abstract void interactHint();
    
    public abstract void interactHint(String hint);

    @Override
    public void render(Graphics g)
    {
        this.board.render(g);
    }

    @Override
    public void tick()
    {
        this.board.tick();
    }

}