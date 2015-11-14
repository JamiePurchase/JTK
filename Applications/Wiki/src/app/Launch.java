/**
 * Wiki Jamie Purchase JTK Framework 07/11/2015
 */
package app;

import engine.Application;
import java.util.ArrayList;
import project.Project;
import states.StatePreview;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("WIKI", 1300, 640, "1.0", "").start(false);
        
        // Project
        ArrayList<String> article1 = new ArrayList();
        article1.add("HELLO");
        Project project = new Project("TEST", "");
        project.addCategory("C1", "CATEGORY ONE");
        project.getCategory("C1").addArticle("A1", "ARTICLE ONE", article1);
        
        // Set Project
        Application.setProperty("PROJECT", project);
        
        // State
        Application.stateNew(new StatePreview());
    }
    
}