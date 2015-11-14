/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.action.phase;

import battle.action.ActionAbstract;
import battle.unit.UnitAbstract;

/**
 *
 * @author Jamie
 */
public class PhaseAnimation extends PhaseAbstract
{
    private UnitAbstract unit;
    
    public PhaseAnimation(ActionAbstract action, UnitAbstract unit)
    {
        super(action, PhaseType.ANIMATION);
        this.unit = unit;
    }
    
    public void execute()
    {
        this.unit.tempAnim = true;
    }
    
}