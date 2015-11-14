/*
 * JTK-RPG
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package campaign.party;

import files.InterfaceData;
import items.equip.weapon.WeaponAbstract;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class CharacterData implements InterfaceData
{
    // Character
    private String name;
    
    // Equipment
    private WeaponAbstract equipWeapon;
    //private AccessoryAbstract equipAccessory;
    
    public CharacterData(String name)
    {
        // Character
        this.name = name;
        
        // Equipment
        this.equipWeapon = null;
    }
    
    public ArrayList<String> getData()
    {
        // Create an empty array
        ArrayList<String> data = new ArrayList();
        
        // Temp
        data.add("temporary character data");
        
        // Return data
        return data;
    }
    
    public String getName()
    {
        return this.name;
    }
    
}