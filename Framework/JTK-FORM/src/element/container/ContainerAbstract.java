/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package element.container;

import element.ElementAbstract;
import element.ElementSelect;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ContainerAbstract extends ElementAbstract
{
    private ArrayList<ElementAbstract> elementList;
    private ElementSelect elementSelect;

    public ContainerAbstract(String ref, ContainerAbstract parent, int posX, int posY, int posW, int posH)
    {
        super(ref, parent, posX, posY, posW, posH);
        this.elementList = new ArrayList();
        this.elementSelect = null;
    }
    
    public Rectangle getArea()
    {
        return this.parent.getArea();
    }
    
    private ElementSelect getSelected()
    {
        return this.elementSelect;
    }
    
    private int getElementPos(ElementAbstract find)
    {
        return this.elementList.indexOf(find);
    }
    
    public void inputKey(KeyEvent e)
    {
        if(this.elementSelect != null) {this.elementSelect.inputKey(e);}
    }
    
    public void inputMouse(MouseEvent e)
    {
        for(int x = 0; x < this.elementList.size(); x++)
        {
            if(this.elementList.get(x).getArea().contains(e.getPoint())) {this.elementList.get(x).inputMouse(e);}
        }
    }
    
    public void render(Graphics g)
    {
        for(int x = 0; x < this.elementList.size(); x++)
        {
            this.elementList.get(x).render(g);
        }
    }
    
    public void selectElement(ElementSelect select)
    {
        if(this.elementSelect != null) {this.elementSelect.select(false);}
        this.elementSelect = select;
        if(this.elementSelect != null) {this.elementSelect.select(true);}
    }
    
    public void tick()
    {
        for(int x = 0; x < this.elementList.size(); x++)
        {
            this.elementList.get(x).tick();
        }
    }

}