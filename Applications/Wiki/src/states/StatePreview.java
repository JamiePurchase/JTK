/**
 * Wiki Jamie Purchase JTK Framework 07/11/2015
 */
package states;

import engine.Application;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import project.Project;

/**
 *
 * @author Jamie
 */
public class StatePreview extends StateAbstract
{

    public StatePreview()
    {
        super("PREVIEW");
    }
    
    private Project getProject()
    {
        return (Project) Application.getProperty("PROJECT");
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        System.out.println("INPUT KEY: " + e.toString());
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        System.out.println("INPUT MOUSE: " + e.getPoint().toString());
    }

    @Override
    public void render(Graphics g)
    {
        System.out.println(this.getProject().getCategory("C1").getArticle("A1").getContents());
    }
    
    public void stateChange()
    {
        System.out.println("STATE CHANGE");
    }
    
    public void stateFinish()
    {
        System.out.println("STATE FINISH");
    }

    @Override
    public void tick()
    {
        //
    }

}