/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package quest;

import campaign.CampaignData;
import files.InterfaceData;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class QuestData implements InterfaceData
{
    private CampaignData campaign;
    private QuestAbstract quest;
    private int stage;
    private boolean complete, visible;
    
    public QuestData(CampaignData campaign, QuestAbstract quest, int stage, boolean complete, boolean visible)
    {
        this.campaign = campaign;
        this.quest = quest;
        this.stage = stage;
        this.complete = complete;
        this.visible = visible;
    }
    
    public boolean getComplete()
    {
        return this.complete;
    }
    
    public ArrayList<String> getData()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Temp
        data.add("temporary quest data");
        
        // Return data
        return data;
    }
    
    public QuestAbstract getQuest()
    {
        return this.quest;
    }
    
    public QuestStage getStage()
    {
        return this.quest.getStage().get(this.stage);
    }
    
    public boolean getVisible()
    {
        return this.visible;
    }
    
    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }
    
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
}