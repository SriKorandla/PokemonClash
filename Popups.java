//Pokemon Clash 
//Jaysen Quan and Sri Korandla
//6/11/21

package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Popups implements ActionListener {
    JFrame confirmScreen, errorScreen;
    JPanel confirmPanel, errorPanel;
    JLabel confirm, error;
    JButton confirmButton, cancelButton, ok;
    GameScreen screen;
	String errorReason = "";
    private GridBagConstraints gbc = new GridBagConstraints();
    public Popups(GameScreen screen) {
        this.screen = screen;
    }
    public void confirm() {
        //Creates a new frame
		confirmScreen = new JFrame("Confirm");
		confirmScreen.setResizable(false);
		confirmScreen.setSize(535, 180);
		
		//Create a new confirmPanel for the frame
		confirmPanel = new JPanel();
		confirmPanel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(15, 15, 15, 15); //Make 15 pixel spacing between j elements
		
		//Change the font of the jlabel
        confirm = new JLabel("Are you sure you want to select this pokemon?");
		confirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gbc.gridx = 0;
		gbc.gridy = 1;
		confirmPanel.add(confirm, gbc);
		//Change the font of the jbutton
        confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gbc.gridx = 3;
		gbc.gridy = 2;
		confirmPanel.add(confirmButton, gbc);
		//Change the font of the jbutton
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gbc.gridx = 2;
		gbc.gridy = 2;
		confirmPanel.add(cancelButton, gbc);
		
		//Add all the buttons to the event handler
		cancelButton.addActionListener(this);
		confirmButton.addActionListener(this);
		
		confirmScreen.setLocationRelativeTo(null); //Make the frame show up in the middle of the screen
		confirmScreen.add(confirmPanel); //Add the confirmPanel to the frame
		confirmScreen.setVisible(true); //Show the frame
    }
    public void errorScreen(String issue) {
		errorReason = issue;
        errorScreen = new JFrame("Fatal Error");
		errorScreen.setResizable(false);
		errorScreen.setSize(535, 180);
		
		//Create a new confirmPanel for the frame
		errorPanel = new JPanel();
		errorPanel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(15, 15, 15, 15); //Make 15 pixel spacing between j elements
		
		//check what the isuse is
		switch(issue) {
			case "full":
				error = new JLabel("An Error has occured: Your team is already full!");
				break;
			case "empty":
				error = new JLabel("An Error has occured: Your team is empty.");
				break;
			default:
				error = new JLabel("An Error has occured.");
		} 
		//Change the font of the jlabel
		error.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gbc.gridx = 0;
		gbc.gridy = 1;
		errorPanel.add(error, gbc);
		//Change the font of the jbutton
        ok = new JButton("OK");
		ok.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gbc.gridx = 3;
		gbc.gridy = 2;
		errorPanel.add(ok, gbc);
		//Change the font of the jbutton
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gbc.gridx = 2;
		gbc.gridy = 2;
		errorPanel.add(cancelButton, gbc);
		
		//Add all the buttons to the event handler
		cancelButton.addActionListener(this);
		ok.addActionListener(this);
		
		errorScreen.setLocationRelativeTo(null); //Make the frame show up in the middle of the screen
		errorScreen.add(errorPanel); //Add the panel to the frame
		errorScreen.setVisible(true); //Show the frame
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmButton) {
            confirmScreen.dispose();
            screen.enableFrame();
            screen.addToTeam();
        } else if (e.getSource() == cancelButton) {
			if(errorReason.equals("full")) {
				screen.enableFrame();
            	errorScreen.dispose();
			}
            errorScreen.dispose();
        } else if (e.getSource() == ok) {
			if(errorReason.equals("full")) {
				screen.enableFrame();
			}
            errorScreen.dispose();
        }
    }
}
