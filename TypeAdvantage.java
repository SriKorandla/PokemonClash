//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import java.util.ArrayList;

public class TypeAdvantage {
    ArrayList<String> typeAdvantage = new ArrayList<String>();
    ArrayList<String> typeResisted = new ArrayList<String>();
    ArrayList<String> immune = new ArrayList<String>();
    public TypeAdvantage() {

    }
    public ArrayList<String> returnTypeStrengths (String type) {
        String normalType = "Normal", flyingType = "Flying", fightingType = "Fighting", fireType = "Fire", 
        waterType = "Water", electricType = "Electric", grassType = "Grass", bugType = "Bug", poisonType = "Poison", psychicType = "Psychic", ghostType = "Ghost", groundType = "Ground", rockType = "Rock", iceType = "Ice", dragonType = "Dragon"; 
        
        switch(type) {
            case "Normal":
                immune.add(ghostType);
                break;
            case "Flying":
                typeAdvantage.add(fightingType);
                typeAdvantage.add(grassType);
                break;
            case "Fighting":
                typeAdvantage.add(normalType);
                typeAdvantage.add(rockType);
                typeAdvantage.add(psychicType);
                typeAdvantage.add(bugType);     
                break;     
            case "Fire":
                typeAdvantage.add(bugType);
                typeAdvantage.add(grassType);
                typeAdvantage.add(iceType);
                typeAdvantage.add(bugType);
                break;
            case "Water":
                typeAdvantage.add(rockType);
                typeAdvantage.add(groundType);
                typeAdvantage.add(fireType);
                break;
            case "Electric":
                typeAdvantage.add(waterType);
                typeAdvantage.add(flyingType); 
                break;
            case "Grass":
                typeAdvantage.add(waterType);
                typeAdvantage.add(groundType);
                typeAdvantage.add(rockType);
                break;
            case "Bug":
                typeAdvantage.add(grassType);
                typeAdvantage.add(psychicType);
                break;
            case "Poison":
                typeAdvantage.add(grassType);
                typeAdvantage.add(psychicType);
                break;
            case "Psychic":
                typeAdvantage.add(fightingType);
                typeAdvantage.add(poisonType);
                typeAdvantage.add(ghostType);
                break;
            case "Ghost":
                typeAdvantage.add(psychicType);
                typeAdvantage.add(ghostType);
                break;
            case "Ground":
                typeAdvantage.add(rockType);
                typeAdvantage.add(fireType);
                typeAdvantage.add(electricType);
                typeAdvantage.add(poisonType);
                break;
            case "Rock":
                typeAdvantage.add(fireType);
                typeAdvantage.add(iceType);
                typeAdvantage.add(flyingType);
                typeAdvantage.add(bugType);
                break;
        
            case "Ice":
                typeAdvantage.add(groundType);
                typeAdvantage.add(rockType);
                typeAdvantage.add(groundType);
                typeAdvantage.add(flyingType);
                typeAdvantage.add(dragonType);
                break;
            case "Dragon":
                typeAdvantage.add(dragonType);
                
            break;
        }		
        return typeAdvantage;
    }
    
    public ArrayList<String> returnTypesResisted (String type) {
        String normalType = "Normal", flyingType = "Flying", fightingType = "Fighting", fireType = "Fire", 
        waterType = "Water", electricType = "Electric", grassType = "Grass", bugType = "Bug", poisonType = "Poison", psychicType = "Psychic", ghostType = "Ghost", groundType = "Ground", rockType = "Rock", iceType = "Ice", dragonType = "Dragon"; 

        ArrayList<String> typeResisted = new ArrayList<String>();
        
        switch(type) {
            case "Normal":
                typeResisted.add(ghostType);
                break;
            case "Flying":
                typeResisted.add(electricType);
                typeResisted.add(iceType);
                typeResisted.add(rockType);
                

                
                break;
            case "Fighting":
                typeResisted.add(grassType);
                typeResisted.add(bugType);
                typeResisted.add(psychicType);
                typeResisted.add(ghostType);
                
                break;
                
            case "Fire":
                typeResisted.add(waterType);
                typeResisted.add(rockType);
                typeResisted.add(groundType);
                typeResisted.add(dragonType);
                typeResisted.add(fireType);

                
                break;
            case "Water":
                typeResisted.add(grassType);
                typeResisted.add(dragonType);
                typeResisted.add(electricType);
                typeResisted.add(waterType);	
                break;
            case "Electric":
                typeResisted.add(groundType);
                typeResisted.add(grassType);
                typeResisted.add(dragonType);
                typeResisted.add(electricType);
                break;
            case "Grass":
                typeResisted.add(fireType);
                typeResisted.add(bugType);
                typeResisted.add(poisonType);
                typeResisted.add(flyingType);
                typeResisted.add(fightingType);
                typeResisted.add(dragonType);
                typeResisted.add(iceType);
                typeResisted.add(grassType);
                break;
            case "Bug":
                typeResisted.add(fireType);
                typeResisted.add(rockType);
                typeResisted.add(flyingType);
                
            
                break;
            case "Poison":
                typeResisted.add(psychicType);
                typeResisted.add(groundType);
                typeResisted.add(rockType);
            
                break;
            
    
            case "Psychic":
                typeResisted.add(bugType);
                typeResisted.add(ghostType);
                break;
            case "Ghost":
                typeResisted.add(psychicType);
                typeResisted.add(normalType);
                
                break;
            case "Ground":
                typeResisted.add(waterType);
                typeResisted.add(grassType);
                typeResisted.add(poisonType);
                typeResisted.add(flyingType);
                typeResisted.add(iceType);
                break;
            case "Rock":
                typeResisted.add(waterType);
                typeResisted.add(groundType);
                typeResisted.add(grassType);
                typeResisted.add(iceType);
                typeResisted.add(fightingType);
                
                break;
        
            case "Ice":
                typeResisted.add(fireType);
                typeResisted.add(iceType);
                typeResisted.add(rockType);
                typeResisted.add(fightingType);
                typeResisted.add(iceType);
                break;
            case "Dragon":
                typeResisted.add(iceType);
            break;
        }
        return typeResisted;
    }
}
