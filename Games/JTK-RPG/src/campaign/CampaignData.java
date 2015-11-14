/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package campaign;

import campaign.party.CharacterData;
import engine.Application;
import files.FileSystem;
import java.util.ArrayList;
import quest.QuestData;
import tools.ArrayListTool;

/**
 *
 * @author Jamie
 */
public class CampaignData
{
    private String path;
    private ArrayList<CharacterData> party;
    private ArrayList<QuestData> quest;
    
    public CampaignData(String path)
    {
        this.path = path;
        this.party = new ArrayList();
        this.quest = new ArrayList();
    }
    
    public void addCharacter(CharacterData newCharacter)
    {
        this.party.add(newCharacter);
    }
    
    public ArrayList<CharacterData> getCharacterList()
    {
        return this.party;
    }
    
    public ArrayList<QuestData> getQuestList()
    {
        return this.quest;
    }
    
    private ArrayList<String> getData()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Campaign Data
        data = ArrayListTool.add(data, this.getDataCampaign());
        data.add(">");
        
        // Board Data
        data = ArrayListTool.add(data, this.getDataBoard());
        data.add(">");
        
        // Party Data
        data = ArrayListTool.add(data, this.getDataParty());
        data.add(">");
        
        // Quest Data
        data = ArrayListTool.add(data, this.getDataQuest());
        data.add(">");
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataBoard()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> BOARD DATA");
        data.add(">");
        
        // Board
        //
        
        // Location
        //
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataCampaign()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> CAMPAIGN DATA");
        data.add(">");
        
        // Board
        //
        
        // Location
        //
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataParty()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> PARTY DATA");
        data.add(">");
        
        // Units
        data.add("" + this.party.size());
        for(int x = 0; x < this.party.size(); x++)
        {
            ArrayListTool.add(data, this.party.get(x).getData());
        }
        
        // Return data
        return data;
    }
    
    private ArrayList<String> getDataQuest()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Header
        data.add("> QUEST DATA");
        data.add(">");
        
        // Quests
        data.add("" + this.quest.size());
        for(int x = 0; x < this.quest.size(); x++)
        {
            ArrayListTool.add(data, this.quest.get(x).getData());
        }
        
        // Return data
        return data;
    }
    
    public void save()
    {
        FileSystem.createFile("CAMPAIGN_DATA", "C:/Users/Jamie/Documents/NetBeansProjects/JTK/Games/Venture/save/temp", Application.getAppTitle(), "Contains campaign save data", this.getData());
    }

}