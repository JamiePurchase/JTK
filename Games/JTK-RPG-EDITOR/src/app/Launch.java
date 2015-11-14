/*
 * JTK-RPG-EDITOR
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package app;

import engine.Application;
import engine.display.DisplayTitlebar2;
import gfx.Style;
import java.awt.Font;
import states.StateInit;

/**
 *
 * @author Jamie
 */
public class Launch
{

    public static void main(String[] args)
    {
        // Application
        //new Application("JTK RPG EDITOR", "1.0", "").start(false);
        
        // Application
        new Application("JTK RPG EDITOR", 1300, 650, "1.0", "").start(false);
        Application.setTitlebar(new DisplayTitlebar2("JTK RPG EDITOR", "BLACK", "TITLEBAR_TITLE", "TITLEBAR_BKG", "BLACK"));
        
        // Style: Init
        Style.init();
        
        // Style: Colours
        Application.setStyleColour("BKG_TITLE", 88, 21, 56);
        Application.setStyleColour("HEADER1", 255, 255, 255);
        Application.setStyleColour("HEADER1_SHADOW", 150, 150, 150);
        Application.setStyleColour("TITLEBAR_BKG", 0, 94, 138);
        Application.setStyleColour("TITLEBAR_BUTTON", 0, 65, 100);
        Application.setStyleColour("TITLEBAR_BUTTON_HOVER", 0, 75, 120);
        //
        Application.setStyleColour("TOOLBAR_BKG", 115, 128, 136);
        
        // Style: Fonts
        Application.setStyleFont("HEADER1", new Font("Don't Mix Yer Drinks", Font.PLAIN, 96));
        Application.setStyleFont("HEADER2", new Font("Don't Mix Yer Drinks", Font.ITALIC, 48));
        Application.setStyleFont("MENU", new Font("Times New Roman", Font.PLAIN, 22));
        Application.setStyleFont("MENU_OPTION", new Font("Times New Roman", Font.PLAIN, 28));
        Application.setStyleFont("STANDARD", new Font("Don't Mix Yer Drinks", Font.PLAIN, 26));
        Application.setStyleFont("TITLEBAR_TITLE", new Font("Vanthian Ragnarok", Font.BOLD, 22));
        Application.setStyleFont("TITLEBAR_BUTTON", new Font("Courier New", Font.BOLD, 18));
        
        // State
        Application.stateNew(new StateInit());
    }

}