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
public class UnitEnemy extends UnitAbstract
{
    // Reward
    private int rewardXP, rewardGoldMin, rewardGoldMax;
    
    public UnitEnemy(BattleAbstract battle, String ref, String name)
    {
        super(battle, ref, name, 0, 0);
        
        // Reward
        this.rewardXP = 0;
        this.rewardGoldMin = 0;
        this.rewardGoldMax = 0;
    }
    
}