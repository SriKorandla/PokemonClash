# Pokemon Clash!
## Sri Korandla

## Website Link:
https://drive.google.com/file/d/1Tsv7AJLCpb_UOXX1PwhgsB04AGpX6ctV/view?usp=share_link

This is a Code Sample for my 24W DALI Application. It is built entirely in Java. Pokemon Clash is a Pokemon Batle Simulator that features all original 151 Pokemon. The User plays against an AI team that is either randomly selected, or one of two pregenerated teams (Trainers Red and Blue from the original games). Type effectiveness from the original games have been implemented; for example, a fire-type like Charizard will be weak to a water-type move like water gun. The AI will select moves that are super-effective against the User's pokemon if the AI has those moves available. Other mechanics, like switching and critical hits, have also been implemented.

This project was built by me and a friend in high school! We both wrote/reviewed all the code for the project, so I have included all of the code

The Whole Project has been uploaded. There are some bugs with this edition of the code however:

1. Some of the animations in the gifs are not working, and lead to a weird-looking backsprite for the player's pokemon
2. Various colors are messed up on Mac systems, such as the HP bar glitching (this is due to Java Swing look and feels being different on both systems)
3. There is a crash due to a JComponent illegal component position error (Make sure to switch pokemon not from the move selection menu, but after hitting back first)

## Files included: 
CODE:
Move.java : class that defines a move/attack. Includes methods to get and calculate damage, get the typing, and calculate critical hits
Trainer.java : class that represents the AI trainer and its team. This class also contains the TrainerRed and TrainerBlue subclasses that extend Trainer.
Battle.java : Represents battle simulator portion of the game. Handles UI elements such as coloration of attacks, battle menus, and the sound
Pokemon.java : class that defines a pokemon. A pokemon object is represented by characteristics such as its name, typing(s), stats, moves, etc.
Game.java : Handles behind the scenes actions such as importing moves and pokemon from csv files and adding pokemon to the user's team, 
GameScreen.java : Handles the pokemon selection screen, and chosing which opponent to fight.
Popups.java : Handles confirmation boxes for selecting pokemon and the opponent
TypeAdvantage.java : contains methods on determining type advantages and resistances for each type
ViewTeam.java : Handles viewing the user's team in team preview mode
FOLDERS:
    BackSprites  (for when the user is using a pokemon)
    Files (for music, and csv files on pokemon, all available moves, and learnable moves)
    StaticSprite (for the pokemon icons in the select screen)
    FrontSprites (for when the AI is using a pokemon)
    MiscImages (for the battle background, hp bar, and other icons used)

## HOW TO RUN:
Make sure to have Java on your system. To run, simply go to terminal, go to the directory, and type in java pokemon.Driver. The program is already compiled! 

## How to Play:
To play, you first have to build a team
To build a team, select a pokemon, then hit select on the bottom right corner, and confirm. You can select up to 6 pokemon.
To view your team and their stats, hit the view team. When you are done viewing, close the view window.
After selecting up to 6 pokemon, hit the home button on the top right of the screen, and then press play.
select either trainer blue, trainer red, or a random trainer to battle.

To battle, hit the white button, then select a move
TO switch, hit back from the attacks, then click a pokemon to switch to.

Hit Control c in the terminal to quit at any time.






