/**
 * Siege JTK Framework Jamie Purchase 07/11/2015
 */
package states;

import engine.Application;
import game.GameData;
import gfx.GFX;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateGame extends StateAbstract
{
    private GameData gameData;
    
    public StateGame()
    {
        super("STATE_GAME");
        this.gameData = new GameData();
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        this.gameData.inputKey(e);
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        this.gameData.inputMouse(e);
    }

    @Override
    public void render(Graphics g)
    {
        this.gameData.render(g);
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
        this.gameData.tick();
    }

}