/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package element;

import element.container.ContainerAbstract;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public abstract class ElementAbstract
{
    protected String ref;
    protected ContainerAbstract parent;
    protected int posX, posY, posW, posH;
    
    public ElementAbstract(String ref, ContainerAbstract parent, int posX, int posY, int posW, int posH)
    {
        this.ref = ref;
        this.parent = parent;
        this.posX = posX;
        this.posY = posY;
        this.posW = posW;
        this.posH = posH;
    }
    
    public Rectangle getArea()
    {
        return new Rectangle(this.parent.getArea().x + posX, this.parent.getArea().y + posY, this.posW, this.posH);
    }
    
    public abstract void inputKey(KeyEvent e);
    
    public abstract void inputMouse(MouseEvent e);
    
    public String ref()
    {
        return this.ref;
    }
    
    public abstract void render(Graphics g);
    
    public abstract void tick();

}