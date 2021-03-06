/**
 * Bloody Skirmish Jamie Purchase JTK Framework 07-11-2015
 */
package states;

import battle.BattleAbstract;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jamie
 */
public class StateBattle extends StateAbstract
{
    private BattleAbstract battle;
    
    public StateBattle()
    {
        super("STATE_GAME");
        this.battle = new BattleAbstract();
    }

    @Override
    public void inputKey(KeyEvent e)
    {
        this.battle.input(e);
    }

    @Override
    public void inputMouse(MouseEvent e)
    {
        //
    }

    @Override
    public void render(Graphics g)
    {
        this.battle.render(g);
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
        this.battle.tick();
    }

}