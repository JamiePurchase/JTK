/*
 * Snake
 * Jamie Purchase
 */
package app;

import states.StateInit;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        String name = "SNAKE";
        String author = "Jamie Purchase";
        String version = "0.1.0";
        String path = "C:/Users/Jamie/Documents/NetBeansProjects/JTK/Games/Snake/resources/";
        //Colours.loadColours();
        //Fonts.loadFonts();
        new Engine(name, author, version, path, new StateInit()).start(false);
    }
    
}