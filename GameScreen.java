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

public class GameScreen implements ActionListener {
    int dexNum = 0;
    JFrame frame, selectTrainer;
    JFrame selectFrame;
    Popups p;
    JButton[] pokemonButtons = new JButton[151];
    JButton buildTeam, select, cancel, team, play;
    JComboBox comboBox;
    JLabel chosen, chosenImg, volumeLabel;
    JMenuBar menuBar;
    JMenu sound, home;
    JMenuItem m21;
    JMenuItem m22;
    JMenuItem m23;
    JMenuItem m11;
    Game game;
    File songFile;
    AudioInputStream audio;
    Clip clip;
    JSlider volumeSlider;
    public GameScreen() {
        game = new Game();
    }
    public void homeScreen() {
        //configure the frame for the screen
        frame = new JFrame();
        frame.setTitle("Pokemon Showdown");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1, 5, 5));
        Font font = new Font("Tahoma", Font.PLAIN, 26);
        JLabel banner = new JLabel(new ImageIcon("Images/banner.png"));
        buildTeam = new JButton("Build Team");
        buildTeam.addActionListener(this);
        buildTeam.setFont(font);
        play = new JButton("Play");
        play.addActionListener(this);
        play.setFont(font);
        frame.add(banner);
        frame.add(buildTeam);
        frame.add(play);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //new frame that displays all the available pokemon to the user 
    public void choosePokemon() {
        selectFrame = new JFrame("Select Pokemon");
        selectFrame.setSize(1920, 1040);
        selectFrame.setLayout(new GridLayout(13, 12, 5, 5));
        for(int i=0; i<151; i++) {
            Icon icon = getImage(i);
            pokemonButtons[i] = new JButton(icon);
            selectFrame.add(pokemonButtons[i]);
            pokemonButtons[i].addActionListener(this);
            pokemonButtons[i].setDoubleBuffered(true);
        }
        disableChosen();
        select = new JButton("Select");
        cancel = new JButton("Cancel");
        team = new JButton("View Team");
        addActionListener();
        select.setFont(getFont(20));
        cancel.setFont(getFont(20));
        team.setFont(getFont(20));

        //create menubar
        menuBar = new JMenuBar();
        home = new JMenu("Home");
        sound = new JMenu("Sound");
        menuBar.add(home);
        menuBar.add(sound);
        m11 = new JMenuItem("Home");
        home.add(m11);
        m21 = new JMenuItem("Play");
        sound.add(m21);
        m22 = new JMenuItem("Stop");
        sound.add(m22);
        m23 = new JMenuItem("Volume");
        sound.add(m23);
        m11.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m23.addActionListener(this);

        selectFrame.setJMenuBar(menuBar);
        selectFrame.add(team);
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setVisible(true);
    }
    //give the user the option to choose the trainer that they want to battle
    private void chooseTrainer() {
        selectTrainer = new JFrame("Select Trainer");
        selectTrainer.setSize(420, 150);
        selectTrainer.setLayout(new FlowLayout());
        selectTrainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectTrainer.setLocationRelativeTo(null);
        selectTrainer.setResizable(false);

        JLabel pickTrainer = new JLabel("Pick the trainer you would like to battle.");
        Font font = new Font("Sans Serif", Font.BOLD, 20);
        pickTrainer.setFont(font);
        //use a combo box to give the user a drop down list
        String[] trainers = {"Blue", "Random", "Red"};
        comboBox = new JComboBox(trainers);
        comboBox.addActionListener(this);
        selectTrainer.add(pickTrainer);
        selectTrainer.add(comboBox);
        selectTrainer.setVisible(true);
    }
    //song for when the user chooses their pokemon
    private void playSong() {
        songFile = new File("Files/opening.wav");
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
    //stop the song
    private void stopSong() {
        clip.stop();
    }
    //disable the buttons for the pokemon that the user has already selected
    public void disableChosen() {
        for(int i=0; i<Game.userTeam.length; i++) {
            if(Game.userTeam[i] != null) {
                pokemonButtons[Game.userTeam[i].getDexNumber()-1].setEnabled(false);
            }
        }
    }
    //disable all buttons when the user is asked to confirm if they want to add the pokemon
    private void disableButtons() {
        for(int i=0; i<pokemonButtons.length; i++) {
            pokemonButtons[i].setEnabled(false);
        }
    }
    //reenable the buttons
    private void enableButtons() {
        for(int i=0; i<pokemonButtons.length; i++) {
            pokemonButtons[i].setEnabled(true);
        }
    }
    //add buttons to the actionlistener
    private void addActionListener() {
        cancel.addActionListener(this);
        select.addActionListener(this);
        team.addActionListener(this);
    }
    //enable the frame (it becomes disabled when a popup shows up)
    public void enableFrame() {
        selectFrame.setEnabled(true);
        selectFrame.setVisible(true);
    }
    //getters
    public Font getFont(int size) {
        return new Font("Tahoma", Font.BOLD, size);
    }
    public Icon getImage(int dexNum) {
        return new ImageIcon("StaticSprite/" + String.valueOf(dexNum+1) + ".png");
    }
    //when the user selects a pokemon, add it to the user's team
    public void addToTeam() {
        game.addToUserTeam(dexNum+1);
        disableChosen();
    }
    //actionlistener for buttons
    public void actionPerformed(ActionEvent e) {
        Font font = new Font("Sans Serif", Font.BOLD, 20);
        //check which button was pressed and ask the user if the want to add the pokemon to their team
        for(int i=0; i<pokemonButtons.length; i++) {
            if(e.getSource() == pokemonButtons[i] && game.fullTeam() == false) {
                dexNum = i;
                disableButtons();
                chosen = new JLabel("Add To Team?");
                chosen.setFont(font);
                chosenImg = new JLabel(getImage(i));
                chosen.setFont(font);
                selectFrame.add(chosen);
                selectFrame.add(chosenImg);
                selectFrame.add(select);
                selectFrame.add(cancel);
                selectFrame.repaint();
                selectFrame.setVisible(true);
            } else if(e.getSource() == pokemonButtons[i] && game.fullTeam()) {
                selectFrame.setEnabled(false);
                p = new Popups(this);
                p.errorScreen("full");
                break;
            }
        }
        //for build team button on the main screen, brings the user to the select pokemon screen
        if(e.getSource() == buildTeam) {
            frame.dispose();
            choosePokemon();
        } 
        //select button for when the user is asked if they want to choose the pokemon
        if(e.getSource() == select) {
            selectFrame.setEnabled(false);
            p = new Popups(this);
            p.confirm();
            enableButtons();
            selectFrame.remove(chosen);
            selectFrame.remove(chosenImg);
            selectFrame.remove(select);
            selectFrame.remove(cancel);
            selectFrame.repaint();
            selectFrame.validate();
        } 
        //cancel button
        if(e.getSource() == cancel) {
            enableButtons();
            selectFrame.remove(chosen);
            selectFrame.remove(chosenImg);
            selectFrame.remove(select);
            selectFrame.remove(cancel);
            selectFrame.repaint();
            selectFrame.validate();
        }
        //creates new frame where the user can view the pokemon that they have added to their team
        if(e.getSource() == team) {
            selectFrame.dispose();
            ViewTeam view = new ViewTeam(game, this);
        }
        //menuitem buttons
        if(e.getSource() == m11) {
            frame.setVisible(true);
            selectFrame.dispose();
        }
        if(e.getSource() == m21) {
            playSong();
        }
        if(e.getSource() == m22) {
            stopSong();
        }
        if(e.getSource() == play) {
            if(Game.userTeam[0] != null) {
                frame.dispose();
                selectFrame.dispose();
                chooseTrainer();
            } else {
                p = new Popups(this);
                p.errorScreen("empty");
            }
        }
        //volume menu item
        if(e.getSource() == m23) {
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
        //when the combobox is pressed, check which trainer the user selected
        if(e.getSource() == comboBox) {
            String trainer = (String)comboBox.getSelectedItem();
            switch(trainer) {
                case "Blue":
                    selectTrainer.dispose();
                    selectFrame.dispose();
                    TrainerBlue blue = new TrainerBlue();
                    Pokemon[] blueTeam = blue.getTrainerTeam();
                    for(int i=0; i<6; i++) {
                        game.generateMoveSet(blueTeam[i]);
                    }
                    System.out.println("trainer blue created");
                    Battle blueBattle = new Battle(blue);
                    break;
                case "Random":
                    selectTrainer.dispose();
                    RandomTrainer random = new RandomTrainer();
                    Pokemon[] randomTeam = random.getTrainerTeam();
                    for(int i=0; i<6; i++) {
                        game.generateMoveSet(randomTeam[i]);
                    }
                    System.out.println("random trainer generated");
                    Battle randomBattle = new Battle(random);
                    break;
                case "Red":
                    selectTrainer.dispose();
                    selectFrame.dispose();
                    TrainerRed red = new TrainerRed();
                    Pokemon[] redTeam = red.getTrainerTeam();
                    for(int i=0; i<6; i++) {
                        game.generateMoveSet(redTeam[i]);
                    }
                    System.out.println("trainer blue created");
                    Battle redBattle = new Battle(red);
                    break;
                default:
                    selectTrainer.dispose();
                    RandomTrainer random1 = new RandomTrainer();
                    break;
            }
        }
    }
    //change listener that is used for the volume slider
    public class changeHandler implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            volumeLabel.setText("Volume: " + volumeSlider.getValue());
        }
    }
}
