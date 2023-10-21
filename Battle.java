//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import javax.sound.sampled.*;

//Battle Class - Frame for the Battle   

public class Battle implements ActionListener {
    JButton[] userTeamButtons = new JButton[Game.getSize()];
    JButton[] moveButtons = new JButton[4];
    JFrame battleScreen;
    JPanel battlePanel, attackChoice, moveChoice, userPokemon;
    JLabel userSprite, enemySprite, background, attackChoiceLabel, volumeLabel;
    JLayeredPane pane;
    JProgressBar userHP, enemyHP;
    JButton fight, pokemon, forfeit, back;
    Pokemon[] trainerTeam;
    Trainer trainer;
    Pokemon currentPokemon, enemyCurrentPokemon;
    Move[] currentMoves;
    Move[] enemyCurrentMoves;
    JLabel[] balls;
    JMenuBar menuBar;
    JMenu sound;
    JMenuItem m11, m12, m13;
    File songFile;
    AudioInputStream audio;
    Clip clip;
    JSlider volumeSlider;
    int enemyPokemonNumber = 0;
    boolean playerLost;
    //Get the trainer that the user is battling 
    public Battle(Trainer trainer) {
        this.trainer = trainer;
        trainerTeam = trainer.getTrainerTeam();
        battleSetup();

        //set up the frame for the battle and set the pokemon battle background
        battleScreen = new JFrame("Battle");
        pane = new JLayeredPane();
        battleScreen.setSize(650, 940);
        battleScreen.setLocationRelativeTo(null);
        background = new JLabel(new ImageIcon("Images/battleField.jpg"));
        background.setBounds(0, 0, 650, 475);

        //add menu bar for sound (option to play the battle song)
        menuBar = new JMenuBar();
        sound = new JMenu("Sound");
        menuBar.add(sound);
        m11 = new JMenuItem("Play");
        sound.add(m11);
        m12 = new JMenuItem("Stop");
        sound.add(m12);
        m13 = new JMenuItem("Volume");
        sound.add(m11);
        sound.add(m12);
        sound.add(m13);
        battleScreen.setJMenuBar(menuBar);
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        //create the hp bars and add them to the screen
        setHPBar();
        //set the current pokemon to the first one on the team
        setCurrentPokemon(Game.userTeam[0]); 
        //add the background to the bottom most layer of the layered pane
        pane.add(background, Integer.valueOf(0));
        //display the pokemon on the screen
        displayPokemon(Game.userTeam[0].getDexNumber(), trainerTeam[0].getDexNumber());
        //create the user menu (fight, moves etc)
        userMenu();
        battleScreen.add(pane);
        battleScreen.setVisible(true);
    }
    //method to play battle song
    private void playSong() {
        songFile = new File("Files/battleSong.wav");
        try {
            audio = AudioSystem.getAudioInputStream(songFile);
             clip = AudioSystem.getClip();
             clip.open(audio);
             FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
             if(volumeSlider.getValue() > 10) {
                gainControl.setValue(-1*(volumeSlider.getValue() - 10));
             } else {
                gainControl.setValue(-1*(volumeSlider.getValue() + 20));
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.start();
    }
    //stops the song from playing
    private void stopSong() {
        clip.stop();
    }
    //set the hp bars to full
    //change the colors and set the position in the screen 
    private void setHPBar() {
        JPanel userHPPanel = new JPanel();
        //hp bars using JProgressBar
        userHPPanel.setBounds(400, 300, 250, 35);
        userHPPanel.setBackground(new Color(1f, 1f, 1f, 0f));
        userHP = new JProgressBar(0, currentPokemon.getHp());
        userHP.setValue(currentPokemon.getHp());
        userHP.setBounds(475, 375, 175, 45);
        userHP.setStringPainted(true);
        userHP.setForeground(new Color(0, 128, 0));
        userHP.setBackground(new Color(255, 255, 255));
        JLabel HPImg = new JLabel(new ImageIcon("Images/hp.PNG"));
        HPImg.setBounds(455, 375, 50, 45);
        userHPPanel.add(HPImg);
        userHPPanel.add(userHP);

        JPanel enemyHPPanel = new JPanel();
        enemyHPPanel.setBounds(0, 150, 250, 35);
        enemyHPPanel.setBackground(new Color(1f, 1f, 1f, 0f));
        enemyHP = new JProgressBar(0, enemyCurrentPokemon.getHp());
        enemyHP.setValue(enemyCurrentPokemon.getHp());
        enemyHP.setBounds(50, 150, 175, 45);
        enemyHP.setStringPainted(true);
        enemyHP.setForeground(new Color(0, 128, 0));
        enemyHP.setBackground(new Color(255, 255, 255));
        JLabel EnemyHPImg = new JLabel(new ImageIcon("Images/hp.PNG"));
        EnemyHPImg.setBounds(0, 150, 50, 45);
        enemyHPPanel.add(EnemyHPImg);
        enemyHPPanel.add(enemyHP);
        pane.add(userHPPanel, Integer.valueOf(1));
        pane.add(enemyHPPanel, Integer.valueOf(1));
    }
    //update the HP bar (when a user switches pokemon or when a pokemon faints)
    private void updateHPBar(boolean user) {
        if(user) {
            userHP.setMinimum(0);
            userHP.setMaximum(currentPokemon.getHp());
            userHP.setValue(currentPokemon.getCurrentHP());
            pane.repaint();
        } else {
            enemyHP.setMinimum(0);
            enemyHP.setMaximum(enemyCurrentPokemon.getHp());
            enemyHP.setValue(enemyCurrentPokemon.getCurrentHP());
            pane.repaint();
        }
    }
    //set the current pokemon for the user and the trainer 
    public void battleSetup() {
        currentPokemon = Game.userTeam[0];
        enemyCurrentPokemon = trainerTeam[0];
    }
    //display the sprites (gifs) for the user pokemon and trainer pokemon
    private void displayPokemon(int userDex, int enemyDex) {
        Icon userIcon = new ImageIcon("BackSprites/" + String.valueOf(Game.userTeam[0].getDexNumber()) + ".gif");
        userSprite = new JLabel(userIcon);
        userSprite.setBounds(50, 200, 300, 300);
        Icon enemyIcon = new ImageIcon("Sprites/" + String.valueOf(enemyCurrentPokemon.getDexNumber()) + ".gif");
        enemySprite = new JLabel(enemyIcon);
        enemySprite.setBounds(325, 40, 300, 300);
        pane.add(userSprite, Integer.valueOf(1));
        pane.add(enemySprite, Integer.valueOf(1));
    }
    //menu for the user (fight option, switch option, move select)
    public void userMenu() {
        userPokemon = new JPanel();
        userPokemon.setLayout(new FlowLayout(FlowLayout.LEFT));
        userPokemon.setBounds(0, 475, 650, 50);
        userPokemon.setBackground(new Color(.75f, .76f, .76f, .75f));
        //shows a pokeball for each pokemon in the team, for each empty slot show pokeball with an X through it
        for(int i=0; i<Game.userTeam.length; i++) {
            balls = new JLabel[6];
            if(Game.userTeam[i] != null && Game.userTeam[i].getFainted() == false) {
                JLabel ball = new JLabel(new ImageIcon("Images/smallPokeBall.png"));
                userPokemon.add(ball);
                balls[i] = ball;
            } else {
                JLabel ball = new JLabel(new ImageIcon("Images/faintedPokeBall.png"));
                userPokemon.add(ball);
                balls[i] = ball;
            }
        }
        //set boundaries of components and add to screen
        attackChoice = new JPanel();
        attackChoice.setBounds(0, 525, 650, 250);
        fight = new JButton("FIGHT");
        Font f = new Font("Sans Serif", Font.PLAIN, 60);
        fight.setFont(f);
        fight.setBackground(new Color(1f,0f,0f,.95f));
        fight.setForeground(Color.WHITE);
        fight.setBounds(150, 750, 430, 250);
        fight.addActionListener(this);

        JPanel switchChoice = new JPanel();
        switchChoice.setBounds(0, 825, 650, 75);
        switchChoice.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel switchPokemon = new JLabel("Switch");
        switchPokemon.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 24));
        switchPokemon.setForeground(new Color(21, 76, 121));
        switchPokemon.setBounds(5, 770, 100, 50);
        //add user's team to screen so that they can choose to switch their pokemon
        for(int i=0; i<userTeamButtons.length; i++) {
            JButton temp = new JButton(getImage(Game.userTeam[i].getDexNumber()));
            temp.setPreferredSize(new Dimension(97, 55));
            userTeamButtons[i] = temp;
            userTeamButtons[i].addActionListener(this);
            switchChoice.add(temp);
        }
        attackChoice.add(fight);
        pane.add(switchPokemon);
        pane.add(attackChoice);
        pane.add(switchChoice);
        pane.add(userPokemon);
    }
    //changes the moves panel back to the panel with the button (called every time the user uses a move)
    private void reset() {
        pane.remove(back);
        pane.remove(attackChoiceLabel);
        pane.remove(moveChoice);
        pane.repaint();
        pane.add(attackChoice);
    }
    //when the enemy's pokemon faints, use their next pokemon
    private void switchEnemyPokemon() {
        enemyPokemonNumber++;
		if(enemyPokemonNumber > 5) {
			System.out.println("You won the battle!");
			battleScreen.setEnabled(false);
			return;
		}
        if(trainerTeam[enemyPokemonNumber] != null) {
            enemyCurrentPokemon = trainerTeam[enemyPokemonNumber];
            enemyCurrentMoves = enemyCurrentPokemon.getMoves();
            updateEnemySprite(enemyCurrentPokemon.getDexNumber());
            enemyHP.setValue(currentPokemon.getHp());
            updateHPBar(false);
        } 
        
    }
    //action listener 
    public void actionPerformed(ActionEvent e) {
        //check if the user clicked any of the switch pokemon buttons
        for(int i=0; i<userTeamButtons.length; i++) {
            if(e.getSource() == userTeamButtons[i]) {
                fight.setEnabled(true);
                updateSprites(Game.userTeam[i].getDexNumber());
                updateHPBar(true);
                setCurrentPokemon(Game.userTeam[i]);
            }
        }
        //check if the user hit the fight button, if so bring up the moves menu
        if(e.getSource() == fight) {
            pane.remove(attackChoice);
            pane.repaint();
            attackChoiceLabel = new JLabel("Attack");
            attackChoiceLabel.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 24));
            attackChoiceLabel.setForeground(new Color(210, 0, 0));
            attackChoiceLabel.setBounds(0, 525, 100, 50);  
            moveChoice = new JPanel();
            moveChoice.setBounds(0, 590, 650, 75);
            moveChoice.setLayout(new GridLayout(1, 4, 10, 10));
            for(int i=0; i<currentMoves.length; i++) {
                JButton move = new JButton(currentMoves[i].getName());
                move.setFont(new Font("Tahoma", Font.BOLD, 16));
                move = getButtonColor(currentMoves[i].getType(), move);
                moveButtons[i] = move;
                moveButtons[i].addActionListener(this);
                moveChoice.add(move);
            }
            back = new JButton("Back");
            back.setFont(new Font("Tahoma", Font.BOLD, 16));
            back.addActionListener(this);
            back.setBounds(0, 700, 100, 50);
            pane.add(back);
            pane.add(attackChoiceLabel);
            pane.add(moveChoice);
        }
        //check which move button the user pressed and call the attack method
        for(int i=0; i<moveButtons.length; i++) {
            if(e.getSource() == moveButtons[i]) {
                userAttack(currentMoves[i]);
            }
        }
        //button to go back to the fight screen
        if(e.getSource() == back) {
            reset();
        }
        //jmenu item buttons
        if(e.getSource() == m11) {
            playSong();
        } 
        if(e.getSource() == m12) {
            stopSong();
        } 
        //volume button on the menu bar, creates new frame that displays a volume slider for the user to change the volume of the song
        if(e.getSource() == m13) {
            JFrame volume = new JFrame("Volume");
            JPanel volumePanel = new JPanel();
            volumeLabel = new JLabel();
            Font volumeFont = new Font("Tahoma", Font.PLAIN, 24);
            volumeLabel.setFont(volumeFont);
            volumeSlider = new JSlider(0, 20, 10);
            volumeSlider.setPreferredSize(new Dimension(400, 200));
            volumeSlider.setPaintTicks(true);
            volumeSlider.setMinorTickSpacing(1);

            volumeSlider.setPaintTrack(true);
            volumeSlider.setMajorTickSpacing(2);

            volumeSlider.setOrientation(SwingConstants.HORIZONTAL);
            volumeLabel.setText("Volume: " + volumeSlider.getValue());

            changeHandler handler = new changeHandler();
            volumeSlider.addChangeListener(handler);
            volumePanel.add(volumeSlider);
            volumePanel.add(volumeLabel);
            volume.add(volumePanel);
            volume.setSize(420, 420);
            volume.setLocationRelativeTo(null);
            volume.setVisible(true);
        }
    }
    //method called when the user's pokemon faints
    //marks the pokemon as fainted and disables the switch button for that pokemon
    private void userFainted() {
        currentPokemon.setFainted(true);
        //check if all the pokemon in the team are either fainted or null
        //if this is the case, the user has no more usable pokemon -> user loses
        for(int j=0; j<Game.userTeam.length; j++) {
            if(Game.userTeam[j] == null) {
                playerLost = true;
            } else if(Game.userTeam[j].getFainted()) {
                playerLost = true;
            } else {
                playerLost = false;
                break;
            }
        }
        if(playerLost) {
            System.out.println("You blacked out and dropped $1000");
            battleScreen.setEnabled(false);
            return;
        }
        //check what the current pokemon is and disable the switch button 
        for(int i=0; i<Game.userTeam.length; i++) {
            if(currentPokemon.getDexNumber() == Game.userTeam[i].getDexNumber()) {
                userSprite.setIcon(null);
                userTeamButtons[i].setEnabled(false);
                fight.setEnabled(false);
                updateHPBar(true);
                balls[i].setIcon(new ImageIcon("Images/faintedPokeBall.png"));
                pane.repaint();
            } 
        }
    }
    //user attack
    private void userAttack(Move move) {
        //swing worker runs the process in the background so that the main thread does not crash (needed for the HP bars)
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            //method does the process in the background
            protected Void doInBackground() throws Exception {
                System.out.println(currentPokemon.getName() + " used " + move.getName());
                int damage = move.getDamage(currentPokemon.getAttack(), enemyCurrentPokemon.getDefense(), move, enemyCurrentPokemon);
                //decrease the HP bar by the damage amount
                while(damage>=0) {
                    //enemy pokemon fainted
                    damage--;
                    enemyHP.setValue(enemyCurrentPokemon.getCurrentHP()-1);
                    enemyCurrentPokemon.changeCurrentHP(enemyCurrentPokemon.getCurrentHP()-1);
                    Thread.sleep(30);
                }
                // TODO Auto-generated method stub
                return null;
            }
            //when the process is done, the enemy has a turn
            protected void done() {
				if(enemyCurrentPokemon.getCurrentHP() <= 0) {
					switchEnemyPokemon();
				} else {
					enemyMove();
				}
            }

        };
        worker.execute();
    }
    //randomly chooses move from moveset (for the enemy)
    private void enemyMove() {
        int enemySelectMove = (int) Math.random() * 4;
        enemyCurrentMoves = enemyCurrentPokemon.getMoves();
        enemyAttack(enemyCurrentMoves[enemySelectMove]);
    }
    //similar to user attack
    private void enemyAttack(Move move) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            //process runs in the background and decreases the HP bar
            protected Void doInBackground() throws Exception {
                System.out.println(enemyCurrentPokemon.getName() + " used " + move.getName());
                Thread.sleep(1000);
                int damage = move.getEnemyDamage(enemyCurrentPokemon.getAttack(), currentPokemon.getDefense(), move, currentPokemon);
                while(damage>=0) {
                    if(currentPokemon.getCurrentHP() <= 0) {
                        userFainted();
                        break;
                    }
                    damage--;
                    userHP.setValue(currentPokemon.getCurrentHP()-1);
                    currentPokemon.changeCurrentHP(currentPokemon.getCurrentHP()-1);
                    Thread.sleep(30);
                }
                // TODO Auto-generated method stub
                return null;
            }
            //
            protected void done() {
                reset();
            }
        };
        worker.execute();
    }
    //set the current pokemon
    private void setCurrentPokemon(Pokemon p) {
        currentPokemon = p;
        currentMoves = p.getMoves();
    }
    //change the sprite
    private void updateSprites(int dexNum) {
        System.out.println("Sprite Update");
        userSprite.setIcon(new ImageIcon("BackSprites/" + String.valueOf(dexNum) + ".gif"));
    }
    //change the enemy sprite
    private void updateEnemySprite(int dexNum) {
        System.out.println("Sprite Update");
        enemySprite.setIcon(new ImageIcon("Sprites/" + String.valueOf(dexNum) + ".gif"));
    }
    private Icon getImage(int dexNum) {
        return new ImageIcon("StaticSprite/" + String.valueOf(dexNum) + ".png");
    }
    //gets the color of the button based on the type
    public JButton getButtonColor(String type, JButton button){
	
        switch(type) {
            case "Normal":
                button.setBackground(Color.lightGray);
                break;
            case "Flying":
                button.setBackground(new Color(137, 170, 227));
                break;
            case "Fighting":
                button.setBackground(Color.orange);
                break;
            case "Fire":
                button.setBackground(Color.red);
                break;
            case "Water":
                button.setBackground(Color.blue);
                
                break;
            case "Electric":
                button.setBackground(Color.yellow);
                
                break;
            case "Grass":
                button.setBackground(Color.green);
                break;
            case "Bug":
                button.setBackground(new Color(50, 205, 50));
            
                break;
            case "Poison":
                button.setBackground(Color.magenta);
                break;
            case "Psychic":
                button.setBackground(Color.pink);
                break;
            case "Ghost":
                button.setBackground(Color.black);
                
                break;
            case "Ground":
                button.setBackground(new Color(210,105,30));
                
                break;
            case "Rock":
                button.setBackground(Color.orange);
                break;
        
            case "Ice":
                button.setBackground(Color.cyan);
                break;
            case "Dragon":
                button.setBackground(Color.darkGray);
                
            break;
                    
                    
        }
        return button;
    }
    //change listener for the volume slider
    public class changeHandler implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            volumeLabel.setText("Volume: " + volumeSlider.getValue());
        }
    }
}
