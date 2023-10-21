//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//pop up frame that displays the user's team and the stats of each pokemon on the team

public class ViewTeam {
    mouseHandler mh = new mouseHandler(); 
    windowHandler wh = new windowHandler();
    Pokemon[] userTeam;
    Game game;
    JLayeredPane team;
    JLabel[] teamImages = new JLabel[6];
    JTextArea stats;
    JLabel testLabel;
    GameScreen screen;
    //screen to display user team, shows the icon of each pokemon on the team
    public ViewTeam(Game g, GameScreen screen) {
        this.screen = screen;
        game = g;
        JFrame teamFrame = new JFrame("Your Team");
        teamFrame.addWindowListener(wh);
        teamFrame.setSize(400, 400);
        team = new JLayeredPane();
        team.setBounds(0, 100, 300, 300);
        team.setLayout(new GridLayout(2, 6, 5, 5));
        userTeam = game.getUserTeam();
        //gets the icon for each pokemon in the team
        for(int i=0; i<userTeam.length; i++) {
            if(userTeam[i] != null) {
                teamImages[i] = new JLabel(getImage(userTeam[i].getDexNumber()));
                teamImages[i].addMouseListener(mh);
                team.add(teamImages[i]);
            }
        }
        teamFrame.add(team);
        teamFrame.setLocationRelativeTo(null);
        teamFrame.setVisible(true);
    }
    //method that gets the icon based on the dexNum (all icons are named by dexNumber)
    public Icon getImage(int dexNum) {
        return new ImageIcon("StaticSprite/" + String.valueOf(dexNum) + ".png");
    }
    //Window handler used to check if the screen has been closed, if it has been closed, go back to the select pokemon screen
    public class windowHandler implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {

        }
        @Override
        public void windowClosing(WindowEvent e) {
            screen.choosePokemon();
        }
        @Override
        public void windowClosed(WindowEvent e) {
        }
        @Override
        public void windowIconified(WindowEvent e) {
            
        }
        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }
        @Override
        public void windowActivated(WindowEvent e) {

        }
        public void windowDeactivated(WindowEvent e) {

        }
    }
    //mouse handler to see if the icon has been hovered
    //when an icon is hovered, display the stats of that pokemon
    public class mouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        //display stats
        public void mouseEntered(MouseEvent e) {
            for(int i=0; i<teamImages.length; i++) {
                if(e.getSource() == teamImages[i]) {
                    Font font = new Font("Tahoma", Font.BOLD, 18);
                    //stats.setFont(font);
                    testLabel = new JLabel(new ImageIcon("D:\\JaysenPrograms_Java\\AP Computer Science\\Quan_Jaysen_Final\\pokemongame\\Images\\Type\\" + userTeam[i].getType1() + ".png"));
                    testLabel.setSize(new Dimension(150, 50));
                    stats = new JTextArea(10, 10);
                    Color c = new Color(.87f,.86f, .86f,.75f);
                    stats.setBackground(c);
                    stats.append("DEX NO. " + userTeam[i].getDexNumber() + "\n");
                    stats.append("NAME: " + userTeam[i].getName() + "\n");
                    stats.append("TYPE: " + userTeam[i].getType1());
                    if(!userTeam[i].getType2().equals("")) {
                        stats.append(", " + userTeam[i].getType2() + "\n");
                    } else {
                        stats.append("\n");
                    }
                    stats.append("TOTAL STATS: " + userTeam[i].getTotalStats() + "\n");
                    stats.append("HP:" + userTeam[i].getHp() + "\n");
                    stats.append("ATTACK: " + userTeam[i].getAttack() + "\n");
                    stats.append("DEFENSE: " + userTeam[i].getDefense() + "\n");
                    stats.append("SP. ATK: " + userTeam[i].getSpAtk() + "\n");
                    stats.append("SP. DEF: " + userTeam[i].getSpDef() + "\n");
                    stats.append("SPEED: " + userTeam[i].getSpeed() + "\n");
                    stats.append("LEGENDARY: " + userTeam[i].getLegendary());
                    stats.setBounds(teamImages[i].getBounds());
                    team.add(stats);
                }
            }    
        }
        @Override 
        //when the user leaves the icon, remove the stats from the screen
        public void mouseExited(MouseEvent e) {
            team.remove(stats);
            team.remove(testLabel);
            team.repaint();
            team.validate();
        }
    }
}
