/**
 * Venture Jamie Purchase JTK-RPG 07/11/2015
 */
package states;

import engine.Application;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import menu.MenuAbstract;
import states.board.StateBoardEditor;
import states.board.StateBoardGame;

/**
 *
 * @author Jamie
 */
public class StateTitle extends StateAbstract
{
    private MenuAbstract menu;
    private boolean loadActive;
    private int loadOption, loadWait;

    public StateTitle()
    {
        super("TITLE");
        this.menu = new MenuAbstract("MENU_TITLE");
        this.menu.setItemColour("BLACK");
        this.menu.setItemShadow("TITLE_SHADOW");
        this.menu.setItemFont("TITLE_MENU");
        this.menu.setCursorString("~");
        this.menu.addItem("MENU_TITLE_BOARD", "BOARD", 600, 300);
        this.menu.addItem("MENU_TITLE_BATTLE", "BATTLE", 600, 350);
        this.menu.addItem("MENU_TITLE_BOARDEDIT", "BOARD EDITOR", 600, 400);
        this.loadActive = false;
        this.loadOption = 0;
        this.loadWait = 30;
    }
    
    private void load()
    {
        if(this.loadOption == 1) {Application.stateNew(new StateBoardGame("BOARD", ""));}
        if(this.loadOption == 2) {Application.stateNew(new StateBattle());}
        if(this.loadOption == 3) {Application.stateNew(new StateBoardEditor("BOARD_EDITOR", ""));}
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String ref = this.menu.getItemRef();
            if(ref.equals("MENU_TITLE_BOARD")) {this.loadOption = 1;}
            if(ref.equals("MENU_TITLE_BATTLE")) {this.loadOption = 2;}
            if(ref.equals("MENU_TITLE_BOARDEDIT")) {this.loadOption = 3;}
            this.loadActive = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {this.menu.inputDown();}
        if(e.getKeyCode() == KeyEvent.VK_UP) {this.menu.inputUp();}
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        // Background
        GFX.drawImage(g, "resources/gfx/menu/bkg1.png", 0, 0);
        
        // Title
        GFX.writeShadow(g, "VENTURE", Application.getScreenCenterX(), 120, 2, "CENTER", "HEADER1", "BLACK", "HEADER1_SHADOW");
        
        // Loading
        if(this.loadActive) {GFX.writeShadow(g, "loading...", Application.getScreenCenterX(), 320, 1, "CENTER", "HEADER2", "BLACK", "HEADER1_SHADOW");}
        else
        {
            // Menu
            this.menu.render(g);
        
            // Info
            GFX.write(g, "Venture", 40, 740, "LEFT", "TITLE_INFO", "HEADER1");
            GFX.write(g, "ENTER   UP/DOWN", 1326, 740, "RIGHT", "TITLE_INFO", "HEADER1");
        }
    }

    @Override
    public void stateChange()
    {
        //
    }

    @Override
    public void stateFinish()
    {
        //
    }

    @Override
    public void tick()
    {
        if(this.loadActive)
        {
            if(this.loadWait > 0)
            {
                this.loadWait -= 1;
                if(this.loadWait < 1)
                {
                    this.load();
                }
            }
        }
        else {this.menu.tick();}
    }

}