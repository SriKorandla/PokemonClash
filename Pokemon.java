//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

//Pokemon Class
public class Pokemon {
    String name;
    String type1;
    String type2;
    int dexNumber, totalStats, hp, attack, defense, spAtk, spDef, speed;
    int currentHP;
    boolean legendary, isFainted;
    Move[] moves = new Move[4];
    public Pokemon(int dexNumber, String name, String type1, String type2, int totalStats, int hp, int attack, int defense, int spAtk, int spDef, int speed, boolean legendary) {
        this.dexNumber = dexNumber;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.totalStats = totalStats;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.legendary = legendary;
        currentHP = hp;
    }
    //getter and setter methods
    public boolean getFainted() {
        return isFainted;
    }
    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }
    public void changeCurrentHP(int newHP) {
        currentHP = newHP;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public Move[] getMoves() {
        return moves;
    }
    public void addMove(Move m, int index) {
        moves[index] = m;
    }
    public void printMoves() {
        for(int i=0; i<moves.length; i++) {
            System.out.println(moves[i].getName());
        }
    }
    public int getDexNumber() {
        return dexNumber;
    }
    public String getName() {
        return name;
    } 
    public String getType1() {
        return type1;
    }
    public String getType2() {
        return type2;
    }
    public int getTotalStats() {
        return totalStats;
    }
    public int getHp() {
        return hp;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getSpAtk() {
        return spAtk;
    }
    public int getSpDef() {
        return spDef;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean getLegendary() {
        return legendary;
    }

}
