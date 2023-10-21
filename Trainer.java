//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import java.io.*;
import java.util.HashSet;
//class that generates trainer (computer)
public class Trainer {
    Pokemon[] team = new Pokemon[6];
    int partySlot = 0;
    int dexNumber = 0;
    public Trainer() {

    }
    //getter method
    public Pokemon[] getTrainerTeam() {
        return team;
    }
    //generate the pokemon given dexnumber
    public Pokemon generatePokemon(int dexNum) {
        String fileName = "Files/PokemonInformation.csv"; //csv file 
        File file = new File(fileName);
        String line = "";
        int lineNum = 1;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null) {
                if(lineNum == dexNum && partySlot < 6) {
                    String[] pokemon = line.split(",");
                    int dexNumber = Integer.parseInt(pokemon[0]);
                    String name = pokemon[1];
                    String type1 = pokemon[2];
                    String type2 = pokemon[3];
                    int totalStats = Integer.parseInt(pokemon[4]);
                    int hp = Integer.parseInt(pokemon[5]);
                    int attack = Integer.parseInt(pokemon[6]);
                    int defense = Integer.parseInt(pokemon[7]);
                    int spAtk = Integer.parseInt(pokemon[8]);
                    int spDef = Integer.parseInt(pokemon[9]);
                    int speed = Integer.parseInt(pokemon[10]);
                    boolean legendary = Boolean.parseBoolean(pokemon[11]);
                    return new Pokemon(dexNumber, name, type1, type2, totalStats, hp, attack, defense, spAtk, spDef, speed, legendary);
                }
                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
//Trainers from the game 
class TrainerBlue extends Trainer {
    public TrainerBlue() {
        //manually add the pokemon to the trainer's team
        team[0] = generatePokemon(133); 
        team[1] = generatePokemon(59);
        team[2] = generatePokemon(103);
        team[3] = generatePokemon(130);
        team[4] = generatePokemon(65);
        team[5] = generatePokemon(149);
        for(int i=0; i<team.length; i++) {
            System.out.println(team[i].getName());
        }
    }   
}
//Random trainer, randomly generates the team
class RandomTrainer extends Trainer {
    HashSet<Integer> set = new HashSet<Integer>();
    public RandomTrainer() {
        for(int i=0; i<team.length; i++) {
            dexNumber = (int) (Math.random() * 151) + 1;
            if(set.contains(dexNumber)) {
                i--;
            } else {
                team[i] = generatePokemon(dexNumber);
                set.add(dexNumber);
            }
        }
        System.out.println("trainer");
        for(int i=0; i<team.length; i++) {
            System.out.println(team[i].getName());
        }
    }
}

class TrainerRed extends Trainer {
    public TrainerRed() {
		//pokemon generated and added to the Trainer Class' array. 
        team[0] = generatePokemon(25); 
        team[1] = generatePokemon(143);
        team[2] = generatePokemon(131);
        team[3] = generatePokemon(3);
        team[4] = generatePokemon(6);
        team[5] = generatePokemon(9);
        for(int i=0; i<team.length; i++) { // prints out his team
            System.out.println(team[i].getName());
        }
    }   
}
