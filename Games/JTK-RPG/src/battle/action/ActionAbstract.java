/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package battle.action;

import battle.BattleAbstract;
import battle.action.phase.PhaseAbstract;
import battle.action.phase.PhaseType;
import battle.unit.UnitAbstract;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class ActionAbstract
{
    private BattleAbstract battle;
    private String ref;
    private ActionType type;
    private UnitAbstract unitSource;
    private UnitAbstract unitTarget;
    private ArrayList<PhaseAbstract> phaseList;
    private int phaseNow;
    
    public ActionAbstract(BattleAbstract battle, String ref, ActionType type, UnitAbstract source)
    {
        this.battle = battle;
        this.ref = ref;
        this.type = type;
        this.unitSource = source;
        this.phaseList = new ArrayList();
        this.phaseNow = 0;
    }
    
    public void addPhase(PhaseAbstract newPhase)
    {
        this.phaseList.add(newPhase);
    }
    
    private PhaseAbstract getPhase()
    {
        return this.phaseList.get(this.phaseNow);
    }
    
    public void phaseExecute()
    {
        this.getPhase().execute();
    }
    
    public void phaseNext()
    {
        this.phaseNow += 1;
        if(this.phaseNow >= this.phaseList.size()) {this.battle.actionDone();}
        else {this.phaseExecute();}
    }
    
}