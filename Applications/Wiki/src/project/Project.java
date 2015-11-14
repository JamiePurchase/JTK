/**
 * Wiki Jamie Purchase JTK Framework 07/11/2015
 */
package project;

import category.Category;
import java.util.HashMap;

/**
 *
 * @author Jamie
 */
public class Project
{
    private String name, path;
    private HashMap<String, Category> category;
    
    public Project(String name, String path)
    {
        this.name = name;
        this.path = path;
        this.category = new HashMap();
    }
    
    public void addCategory(String ref, String title)
    {
        this.category.put(ref, new Category(this, ref, title));
    }
    
    public Category getCategory(String ref)
    {
        return this.category.get(ref);
    }
    
    public void save()
    {
        //
    }

}