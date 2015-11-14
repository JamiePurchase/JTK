/*
 * JTK-SCRIPT
 * 13/11/2015
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package app;

import engine.Application;
import engine.display.DisplayTitlebar2;
import java.awt.Font;
import states.StateApp;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        // Application
        new Application("JTK SCRIPT", 1200, 600, "1.0", "").start(false);
        Application.setTitlebar(new DisplayTitlebar2("JTK SCRIPT", "BLACK", "TITLEBAR_TITLE", "TITLEBAR_BKG", "BLACK"));
        
        // Style: Colours
        Application.setStyleColour("TITLEBAR_BKG", 0, 94, 138);
        Application.setStyleColour("TITLEBAR_BUTTON", 0, 65, 100);
        Application.setStyleColour("TITLEBAR_BUTTON_HOVER", 0, 75, 120);
        //
        Application.setStyleColour("DIALOG_TEXT", 0, 0, 0);
        Application.setStyleColour("FILE_DATA", 0, 0, 0);
        Application.setStyleColour("FILE_DATA_COMMENT", 0, 0, 50);
        Application.setStyleColour("TOOLBAR_BKG", 115, 128, 136);
        Application.setStyleColour("TOOLBAR_BORDER", 0, 0, 0);
        Application.setStyleColour("TOOLBAR_ITEM_HIGHLIGHT", 106, 123, 145);
        Application.setStyleColour("TOOLBAR_SCROLL_BUTTON", 115, 128, 136);
        Application.setStyleColour("TOOLBAR_SCROLL_BUTTON_HIGHLIGHT", 106, 123, 145);
        
        // Style: Fonts
        Application.setStyleFont("HEADER1", new Font("Times New Roman", Font.PLAIN, 42));
        Application.setStyleFont("INFO_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("OPTION_STANDARD", new Font("Times New Roman", Font.PLAIN, 22));
        //
        Application.setStyleFont("STANDARD", new Font("Don't Mix Yer Drinks", Font.PLAIN, 26));
        //Application.setStyleFont("STANDARD", new Font("Jorvik Informal", Font.PLAIN, 22));
        Application.setStyleFont("TITLEBAR_TITLE", new Font("Vanthian Ragnarok", Font.BOLD, 22));
        Application.setStyleFont("TITLEBAR_BUTTON", new Font("Courier New", Font.BOLD, 18));
        //
        Application.setStyleFont("DIALOG_STANDARD", new Font("Don't Mix Yer Drinks", Font.PLAIN, 26));
        Application.setStyleFont("FILE_DATA", new Font("Courier New", Font.PLAIN, 18));
        Application.setStyleFont("FILE_DATA_COMMENT", new Font("Courier New", Font.ITALIC, 18));
        Application.setStyleFont("TOOLBAR_ITEM", new Font("Times New Roman", Font.PLAIN, 18));
        
        // State
        Application.stateNew(new StateApp());
        
        // Properties
        //Application.setProperty("MODE_DEBUG", true);
    }
    
}