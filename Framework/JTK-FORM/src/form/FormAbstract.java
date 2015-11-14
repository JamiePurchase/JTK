/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package form;

import element.container.ContainerAbstract;
import element.container.ContainerForm;
import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public abstract class FormAbstract
{
    private final String ref;
    private ContainerForm container;
    
    public FormAbstract(String ref)
    {
        this.ref = ref;
        this.container = new ContainerForm(this);
    }
    
    public String ref()
    {
        return this.ref;
    }
    
    public Rectangle getArea()
    {
        return Application.getAppArea();
    }
    
    private String getStyleBackground()
    {
        return "BLACK";
    }
    
    public void inputKey(KeyEvent e)
    {
        this.container.inputKey(e);
    }
    
    public void inputMouse(MouseEvent e)
    {
        this.container.inputMouse(e);
    }
    
    public void render(Graphics g)
    {
        // Background
        GFX.drawRect(g, this.getArea(), this.getStyleBackground(), true);
        
        // Container
        this.container.render(g);
    }
    
    public void tick()
    {
        this.container.tick();
    }

}