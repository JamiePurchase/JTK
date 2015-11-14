/**
 * Wiki Jamie Purchase JTK Framework 07/11/2015
 */
package article;

import category.Category;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class Article
{
    private Category category;
    private String ref, title;
    private ArrayList<String> contents;
    
    public Article(Category category, String ref, String title, ArrayList<String> contents)
    {
        this.category = category;
        this.ref = ref;
        this.title = title;
        this.contents = contents;
    }
    
    public ArrayList<String> getContents()
    {
        return this.contents;
    }

    public void render(Graphics g)
    {
        
    }

}