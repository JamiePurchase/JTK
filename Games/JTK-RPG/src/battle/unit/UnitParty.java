/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.unit;

import battle.BattleAbstract;

/**
 *
 * @author Jamie
 */
public class UnitParty extends UnitAbstract
{
    //
    
    public UnitParty(BattleAbstract battle, String ref, String name)
    {
        super(battle, ref, name, 1000, 100, "resources/gfx/character/1_battle.png", 0, 192);
    }
    
}