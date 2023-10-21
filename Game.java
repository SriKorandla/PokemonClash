//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import java.util.HashSet;
import java.io.*;
import java.util.ArrayList;

//OVERVIEW:
//Game class - Handles back end processes (no GUI)
//Extracts data from CSV Files to create the pokemon and their movesets

public class Game {
    public static Pokemon[] userTeam = new Pokemon[6]; //static so that it can be accessed throughout all of the classes and because this array will never change throughout the game
    ArrayList<Move> moves = new ArrayList<Move>();
    int partySlot = 0;
    public Game() {
        importMoves();
    }
    //Method that takes the pokeDex number as a parameter
    //Uses this number to locate the corresponding pokemon in the CSV file
    public void addToUserTeam(int dexNum) {
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
                    //takes each value before and after commas and stores it in an array
                    String[] pokemon = line.split(",");
                    //store the values into its proper variable
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
                    //create a new pokemon with these attributes and add it to the user's team
                    userTeam[partySlot] = new Pokemon(dexNumber, name, type1, type2, totalStats, hp, attack, defense, spAtk, spDef, speed, legendary);
                    generateMoveSet(userTeam[partySlot]); 
                    partySlot++; //count the party slot which each pokemon is stored (prevents out of bounds exception)
                    break;
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
    }
    //gets the number of pokemon in the user's team
    public static int getSize() {
        int size = 0;
        for(int i=0; i<userTeam.length; i++) {
            if(userTeam[i] != null) {
                size++;
            }
        }
        return size;
    }
    //reads each line in the csv file and adds each move to an array list of moves
    //the array list acts as a move bank
    public void importMoves() {
        String fileName = "Files/moves.csv";
        File file = new File(fileName);
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null) {
                //again takes each value before and after a comma and stores each value in the correct variable
                String[] moveInfo = line.split(",");
                int dexNumber = Integer.parseInt(moveInfo[0]);
                String moveName = moveInfo[1];
                String moveType = moveInfo[2];
                int moveDamage = Integer.parseInt(moveInfo[3]);
                int moveAccuracy = Integer.parseInt(moveInfo[4]);
                boolean statusMove = Boolean.parseBoolean(moveInfo[5]);
                int movePP = Integer.parseInt(moveInfo[6]);
                String special = moveInfo[7];
                //addes the move to the bank
                moves.add(new Move(dexNumber, moveName, moveType, moveDamage, moveAccuracy, statusMove, movePP, special));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    //method that is used to generate a pokemon's moveset 
    //takes the pokemon's learnset (moves that it can learn) and randomly chooses 4 of them
    public void generateMoveSet(Pokemon p) {
        String[] learnableMoves = getLearnableMoves(p.getDexNumber());
        HashSet<String> usedMoves = new HashSet<String>(); //hash set prevents duplicate moves
        int counter = 0;
        //keep getting moves until the size of the hash set is 4 because each pokemon has 4 moves
        while(usedMoves.size() != 4) {
            int learnIndex = (int) (Math.random() * learnableMoves.length); //gets a random index which is used to get a random move
            String move = learnableMoves[learnIndex];
            //check if the move has been used already, if not find the move information in the move bank and add it to the pokemon's moveset
            if(usedMoves.contains(move)) {
                continue;
            } else {
                for(int j=0; j<moves.size(); j++) {
                    //find the move object for the selected move
                    if(move.equals(moves.get(j).getName())) {
                        p.addMove(moves.get(j), counter);  
                        usedMoves.add(move);
                        counter++;
                    }
                }
            }
            
        }
        System.out.println(p.getName() + "'s moves");
        p.printMoves();
    }
    //method that is extracts the learnable moves for a pokemon
    public String[] getLearnableMoves(int dexNum) {
        String fileName = "Files/LearnableMoves.csv";
        File file = new File(fileName);
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null) {
                String[] learnableMoves = line.split(",");
                //finds the location of the learnable moves for the given pokemon (based on dexNumber)
                if(Integer.parseInt(learnableMoves[0]) == dexNum) {
                    //-2 from learnablemoves.length because the first 2 values of each learn set is the dexnumber and name (we only want the move names)
                    String[] temp = new String[learnableMoves.length-2];
                    for(int i=0; i<temp.length; i++) {
                        if(!learnableMoves[i].equals("")) {
                            temp[i] = learnableMoves[i+2];
                        }
                    }
                    return temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                
            }
        }
        return null;
    }
    //getters and setters;
    public Pokemon[] getUserTeam() {
        return userTeam;
    }
    public boolean fullTeam() {
        if(userTeam[5] == null) {
            return false;
        } else {
            return true;    
        }
    }
}