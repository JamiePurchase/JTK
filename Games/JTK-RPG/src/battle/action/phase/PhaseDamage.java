/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.action.phase;

import battle.action.ActionAbstract;
import battle.action.damage.DamageType;
import battle.unit.UnitAbstract;

/**
 *
 * @author Jamie
 */
public class PhaseDamage extends PhaseAbstract
{
    private DamageType type;
    private int amount;
    private UnitAbstract target;
    
    public PhaseDamage(ActionAbstract action, DamageType type, int amount, UnitAbstract target)
    {
        super(action, PhaseType.DAMAGE);
        this.type = DamageType.MELEE;
        this.amount = 12;
        this.target = target;
    }
    
    public void execute()
    {
        this.target.damage(this.type, this.amount);
    }
    
}