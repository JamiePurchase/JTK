/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package quest;

import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class QuestAbstract
{
    private String ref, name;
    private QuestType type;
    private ArrayList<QuestStage> stage;
    
    public QuestAbstract(String ref, String name, QuestType type)
    {
        this.ref = ref;
        this.name = name;
        this.type = type;
        this.stage = new ArrayList();
    }
    
    public void addStage(String objective, String description)
    {
        this.stage.add(new QuestStage(this, this.stage.size(), objective, description));
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public QuestType getType()
    {
        return this.type;
    }
    
    public ArrayList<QuestStage> getStage()
    {
        return this.stage;
    }
    
}