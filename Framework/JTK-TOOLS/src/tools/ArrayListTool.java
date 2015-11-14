/**
 * JTK-TOOLS Jamie Purchase 13/11/2015
 */
package tools;

import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ArrayListTool
{

    public static ArrayList<String> add(ArrayList<String> list, ArrayList<String> additional)
    {
        for(int x = 0; x < additional.size(); x++)
        {
            list.add(additional.get(x));
        }
        return list;
    }
    
    public static ArrayList<String> insert(ArrayList<String> list, String additional, int pos)
    {
        ArrayList<String> result = new ArrayList();
        for(int x = 0; x < list.size() + 1; x++)
        {
            if(x < pos) {result.add(list.get(x));}
            else if(x == pos) {result.add(additional);}
            else if(x > pos) {result.add(list.get(x - 1));}
        }
        return result;
    }

}