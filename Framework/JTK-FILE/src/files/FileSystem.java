/**
 * JTK-FILE Jamie Purchase 13/11/2015
 */
package files;

import console.Output;
import engine.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import tools.ArrayListTool;

/**
 *
 * @author Jamie
 */
public class FileSystem
{
    
    public static void clearFile(String path)
    {
        saveFile("", path);
    }
    
    public static void createFile(String title, String path, String application, String description, ArrayList<String> data)
    {
        // Create an empty array
        ArrayList<String> file = new ArrayList();
        
        // Header
        file.add("> " + title);
        file.add(">   " + description);
        file.add(">   " + application);
        file.add(">   " + Application.getTimestamp());
        file.add(">");
        
        // Add data
        ArrayListTool.add(file, data);
        
        // Save the file
        saveFile(file, path + ".jtk");
    }
    
    public static void createFolder(String path)
    {
        new File(path).mkdir();
    }
    
    public static boolean findFile(String path)
    {
        if(new File(path).exists()) {return true;}
        return false;
    }
    
    private static String getBreak()
    {
        return System.getProperty("line.separator");
    }
    
    public static String getExtension(File file)
    {
        if(file.getName().lastIndexOf(".") != -1 && file.getName().lastIndexOf(".") != 0)
        {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return "";
    }
    
    public static String getFileName(String path)
    {
        return new File(path).getName();
    }
    
    public static String getFileName(File file)
    {
        return file.getName().substring(0, file.getName().length() - 7);
    }
    
    public static ArrayList<File> getFolder(String path, String ext)
    {
        return getFolder(path, true, false, ext);
    }
    
    public static ArrayList<File> getFolder(String path, boolean getFiles, boolean getDirectories, String getExt)
    {
        ArrayList<File> result = new ArrayList();
        File[] folder = new File(path).listFiles();
        for(int f = 0; f < folder.length; f++)
        {
            if(getFiles && folder[f].isFile())
            {
                if(getExt != null)
                {
                    if(getExtension(folder[f]).equals(getExt)) {result.add(folder[f]);}
                }
                else {result.add(folder[f]);}
            }
            if(getDirectories && folder[f].isDirectory()) {result.add(folder[f]);}
        }
        return result;
    }
    
    public static ArrayList<String> loadFile(String path)
    {
        return loadFile(path, false);
    }
        
    public static ArrayList<String> loadFile(String path, boolean commentIgnore)
    {
        ArrayList<String> data = new ArrayList();
        boolean active = true;
        BufferedReader reader;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(path));
            try
            {
                while(active)
                {
                    line = reader.readLine();
                    if(line != null)
                    {
                        if(commentIgnore)
                        {
                            if(!line.substring(0, 1).equals(">")) {data.add(line);}
                        }
                        else {data.add(line);}
                    }
                    else {active = false;}
                }
                reader.close();
            }
            catch(IOException ex) {Output.print(ex);}
        }
        catch(FileNotFoundException ex) {Output.print(ex);}
        return data;
    }
    
    public static void saveFile(String data, String path)
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(new FileWriter(path, false));
            writer.printf("%s" + "%n", data);
            writer.close();
        }
        catch (IOException ex) {Output.print(ex);}
    }
    
    public static void saveFile(ArrayList<String> data, String path)
    {
        String condense = "";
        for(int x = 0; x < data.size(); x++)
        {
            condense += data.get(x);
            if(x < data.size() - 1) {condense += getBreak();}
        }
        saveFile(condense, path);
    }
    
    public static void setFileHidden(String path, boolean hidden)
    {
        try {setFileHidden(new File(path), hidden);}
        catch (InterruptedException ex) {Output.print(ex);}
        catch (IOException ex) {Output.print(ex);}
    }
    
    public static void setFileHidden(File file, boolean hidden) throws InterruptedException, IOException
    {
        String setting = "+H";
        if(!hidden) {setting = "-H";}
        Process p = Runtime.getRuntime().exec("attrib " + setting + " " + file.getPath());
        p.waitFor();
    }
    
}