/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.action.phase;

import battle.action.ActionAbstract;

/**
 *
 * @author Jamie
 */
public abstract class PhaseAbstract
{
    private ActionAbstract action;
    private PhaseType type;
    
    public PhaseAbstract(ActionAbstract action, PhaseType type)
    {
        this.action = action;
        this.type = type;
    }
    
    public abstract void execute();
    
    public PhaseType getType()
    {
        return this.type;
    }

}