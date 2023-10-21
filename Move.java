//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import java.util.ArrayList;

public class Move { //move class
    String moveName, type, special; 
    int moveNo, damage, accuracy, pp;
    boolean status;
    public Move(int moveNo, String move, String type, int baseDamage, int accuracy, boolean status, int pp, String special) { //Move constructor
        this.moveNo = moveNo;    
        moveName = move;
        this.type = type;
        damage = baseDamage;
        this.accuracy = accuracy;
        this.status = status;
        this.pp = pp;  
        this.special = special;
    }   
    public int getDamage(int attack, int defense, Move move, Pokemon enemyPokemon) { //method that gets actual damage done to user from the moves bp 
        TypeAdvantage t = new TypeAdvantage();
        ArrayList<String> superEffective = t.returnTypeStrengths(move.getType());
        ArrayList<String> notEffective = t.returnTypesResisted(move.getType());
        double effectiveMultiplier = 1;
        for(int i=0; i<superEffective.size(); i++) { //the move gets multipled by 1.5 if its supereffeective
            if(enemyPokemon.getType1().equals(superEffective.get(i)) || enemyPokemon.getType2().equals(superEffective.get(i))) {
                effectiveMultiplier +=.5;
                System.out.println("It was super effective!"); //lets user know
            }
        }
        for(int i=0; i<notEffective.size(); i++) { //if the move is not very effective, the moves base power gets multiplied by 0.5
            if(enemyPokemon.getType1().equals(notEffective.get(i)) || enemyPokemon.getType2().equals(notEffective.get(i))) { 
                effectiveMultiplier -= 0.5;
                System.out.println("It wasn't very effective..."); //lets user know
            }
        }
        return (int) (((2.4*damage*(attack/defense))/25+((int) (Math.random() * 12)))*getCritical()*effectiveMultiplier); //damage calculation algorithm
    }
    public int getEnemyDamage(int attack, int defense, Move move, Pokemon userPokemon) { //method that gets actual damage done to enemy from the moves bp
        TypeAdvantage t = new TypeAdvantage();
        ArrayList<String> superEffective = t.returnTypeStrengths(move.getType());
        ArrayList<String> notEffective = t.returnTypesResisted(move.getType());
        double effectiveMultiplier = 1;
        for(int i=0; i<superEffective.size(); i++) {
            if(userPokemon.getType1().equals(superEffective.get(i)) || userPokemon.getType2().equals(superEffective.get(i))) {
                effectiveMultiplier +=.5; //the move gets multipled by 1.5 if its supereffeective
                System.out.println("It was super effective!");
            }
        }
        for(int i=0; i<notEffective.size(); i++) {
            if(userPokemon.getType1().equals(notEffective.get(i)) || userPokemon.getType2().equals(notEffective.get(i))) {
                effectiveMultiplier -= 0.5; //if the move is not very effective, the moves base power gets multiplied by 0.5
                System.out.println("It wasn't very effective...");
            }
        }
        return (int) (((2.4*damage*(attack/defense))/25+((int) (Math.random() * 12)))*getCritical()*effectiveMultiplier);
    }
    public int getCritical() { //random chance of a move doing extra damage
        int random = (int) Math.random()*7 + 1;
        if(random == 1) {
            return 2;
        } else {
            return 1;
        }
    }
	//getters or setters
    public String getName() {
        return moveName;
    }
    public String getType() {
        return type;
    }
}
