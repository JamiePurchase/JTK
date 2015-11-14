/**
 * Wiki Jamie Purchase JTK Framework 07/11/2015
 */
package category;

import article.Article;
import java.util.ArrayList;
import java.util.HashMap;
import project.Project;

/**
 *
 * @author Jamie
 */
public class Category
{
    private Project project;
    private String ref, title;
    private HashMap<String, Article> article;
    
    public Category(Project project, String ref, String title)
    {
        this.project = project;
        this.ref = ref;
        this.title = title;
        this.article = new HashMap();
    }
    
    public void addArticle(String ref, String title, ArrayList<String> contents)
    {
        this.article.put(ref, new Article(this, ref, title, contents));
    }
    
    public Article getArticle(String ref)
    {
        return this.article.get(ref);
    }

}