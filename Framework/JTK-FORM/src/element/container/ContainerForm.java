/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package element.container;

import form.FormAbstract;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ContainerForm
{
    private FormAbstract form;
    private ArrayList<ContainerAbstract> containerList;
    private int containerActive;
    
    public ContainerForm(FormAbstract form)
    {
        this.form = form;
        this.containerList = new ArrayList();
        this.containerActive = 0;
    }
    
    private Rectangle getArea()
    {
        return this.form.getArea();
    }
    
    private ContainerAbstract getContainerActive()
    {
        return this.containerList.get(this.containerActive);
    }
    
    public void inputKey(KeyEvent e)
    {
        this.getContainerActive().inputKey(e);
    }
    
    public void inputMouse(MouseEvent e)
    {
        for(int x = 0; x < this.containerList.size(); x++)
        {
            if(this.containerList.get(x).getArea().contains(e.getPoint())) {this.containerList.get(x).inputMouse(e);}
        }
    }
    
    public void render(Graphics g)
    {
        for(int x = 0; x < this.containerList.size(); x++)
        {
            this.containerList.get(x).render(g);
        }
    }
    
    public void tick()
    {
        for(int x = 0; x < this.containerList.size(); x++)
        {
            this.containerList.get(x).tick();
        }
    }
    
}