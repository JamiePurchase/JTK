/*
 * JTK Form
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package element;

import element.container.ContainerAbstract;

/**
 *
 * @author Jamie
 */
public abstract class ElementSelect extends ElementAbstract
{
    protected boolean selected;
    
    public ElementSelect(String ref, ContainerAbstract parent, int posX, int posY, int posW, int posH)
    {
        super(ref, parent, posX, posY, posW, posH);
        this.selected = false;
    }
    
    public void select()
    {
        this.selected = true;
    }
    
    public void select(boolean selected)
    {
        this.selected = selected;
    }

}