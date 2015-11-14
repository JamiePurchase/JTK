/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package quest;

/**
 *
 * @author Jamie
 */
public class QuestStage
{
    private QuestAbstract quest;
    private int ref;
    private String objective, description;
    
    public QuestStage(QuestAbstract quest, int ref, String objective, String description)
    {
        this.quest = quest;
        this.ref = ref;
        this.objective = objective;
        this.description = description;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public String getObjective()
    {
        return this.objective;
    }
    
}