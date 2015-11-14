/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package app;

import engine.Application;
import java.awt.Font;
import states.StateGame;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("SIEGE", "1.0", "").start(false);
        
        // Style: Colours
        Application.setStyleColour("BKG_GRASS", 121, 174, 104);
        Application.setStyleColour("INFO_BKG", 128, 119, 71);
        Application.setStyleColour("INFO_BORDER", 0, 0, 0);
        Application.setStyleColour("INFO_TEXT", 0, 0, 0);
        Application.setStyleColour("OPTION_BKG", 176, 165, 104);
        Application.setStyleColour("OPTION_BKG_HOVER", 196, 185, 124);
        Application.setStyleColour("OPTION_BORDER", 0, 0, 0);
        Application.setStyleColour("OPTION_TEXT", 0, 0, 0);
        
        // Style: Fonts
        Application.setStyleFont("HEADER1", new Font("Times New Roman", Font.PLAIN, 42));
        Application.setStyleFont("INFO_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("OPTION_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        
        // State
        Application.stateNew(new StateGame());
        
        // Properties
        Application.setProperty("MODE_DEBUG", true);
    }
    
}