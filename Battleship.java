/*Wasif Ahmad Butt, Ms. Gorski
Password in options is "admin"
Images from:
http://wallpapercave.com/wp/RMIs0gk.png
http://www.spirit-of-metal.com/les%20goupes/V/Victory%20(GER)/pics/logo.jpg
http://www.knowledgeadventure.com/games/battleship/
*/

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;

public class Battleship extends Applet implements ActionListener
{
    int row = 10; //grid is 10 x 10
    JButton a[] = new JButton [row * row]; //both user and computer's grid are the same dimensions
    JButton b[] = new JButton [row * row];
    int comptracker[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //1D array for computer's grid ***Note: the value 0 represents that the spot is empty***
    int tracker[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //1D array for user's grid, identical to that of the computer
    Panel p_card;
    Panel card1, card2, card3, card4, card5, card6;
    CardLayout cdLayout = new CardLayout ();
    int temp = 0;
    char turn = 'u';
    JButton boat1, boat2, boat3, boat4, boat5, redbutton, compgo;
    JLabel instrpic, scorepr;
    int count = 0;
    int buttons = 0;
    int score = 0;
    int instrp = 0;
    int trackscore = 0;
    int x = 99;
    int y = 1;
    String prompt = "";
    int streak = 150 - trackscore;
    String compname = "COMPUTER";
    int start = 0;
    int current = 5;
    JRadioButton soundon;
    JTextField cname;
    char difficulty = 'e';
    JComboBox dif;
    String dif_s;
    JPasswordField p;
    boolean seecheck = false;
    //*******************************************************************************************************
    public void init ()
    { //initialize method begins

	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 ();
	screen2 ();
	screen3 ();
	screen4 ();
	screen5 ();
	screen6 ();
	resize (400, 250);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    } //*******************************************************************************************************


    public void screen1 ()
    { //screen 1 is set up, also known as "Main Menu"

	card1 = new Panel ();
	card1.setBackground (new Color (155, 155, 222)); //background colour for screen

	JLabel title = new JLabel ("                     Battleship                   ");
	title.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 35));
	title.setForeground (new Color (11, 96, 13));

	JButton start = new JButton ("Start"); //this button will lead to game screen
	start.addActionListener (this);
	start.setActionCommand ("2a");
	start.setBackground (new Color (11, 96, 13));
	start.setForeground (Color.white);

	JButton instr = new JButton ("Instructions"); //this button will lead to instructions screen
	instr.addActionListener (this);
	instr.setActionCommand ("instr");
	instr.setBackground (new Color (11, 96, 13));
	instr.setForeground (Color.white);

	JButton options = new JButton ("Options"); //this button will lead to options screen
	options.addActionListener (this);
	options.setActionCommand ("op");
	options.setBackground (new Color (11, 96, 13));
	options.setForeground (Color.white);

	JLabel boat1 = new JLabel (createImageIcon ("boatbkg.jpg")); //image at the bototm of screen

	card1.add (title);
	card1.add (start);
	card1.add (instr);
	card1.add (options);
	card1.add (boat1);
	p_card.add ("1", card1);
    } //*******************************************************************************************************


    public void screen2 ()
    { //screen 2 is set up, also known as "Game" screen
	card2 = new Panel ();
	card2.setBackground (Color.gray);

	Panel g = new Panel (new GridLayout (row, row)); //computer's grid is setup in a panel
	for (int i = 0 ; i < a.length ; i++) //attributes are added to each button on grid
	{
	    a [i] = new JButton ();
	    a [i].addActionListener (this);
	    a [i].setActionCommand ("" + i);
	    a [i].setPreferredSize (new Dimension (34, 35));
	    a [i].setBackground (new Color (155, 155, 222));
	    a [i].setEnabled (false);
	    g.add (a [i]);
	}
	JLabel comp = new JLabel ("Enemy Territory  ");
	comp.setFont (new Font ("Adobe Helti Std R", Font.ITALIC, 20));
	comp.setForeground (Color.red);

	Panel h = new Panel (new GridLayout (row, row)); //user's grid is setup in a panel
	for (int j = 0 ; j < b.length ; j++) //attributes are added to each button on user's grid
	{
	    b [j] = new JButton ();
	    b [j].addActionListener (this);
	    b [j].setActionCommand ("" + j);
	    b [j].setPreferredSize (new Dimension (34, 35));
	    b [j].setBackground (new Color (155, 155, 222));
	    h.add (b [j]);
	}
	JLabel user = new JLabel ("Your Territory      ");
	user.setFont (new Font ("Adobe Helti Std R", Font.ITALIC, 20));
	user.setForeground (Color.red);

	boat1 = new JButton (createImageIcon ("ship1.jpg")); //button to select 5 block ship
	boat1.setBackground (new Color (155, 155, 222));
	boat1.addActionListener (this);
	boat1.setActionCommand ("ship1");

	boat2 = new JButton (createImageIcon ("ship2.jpg")); //button to select 4 block ship
	boat2.setBackground (new Color (155, 155, 222));
	boat2.addActionListener (this);
	boat2.setActionCommand ("ship2");

	boat3 = new JButton (createImageIcon ("ship3.jpg")); //button to select 3 block ship
	boat3.setBackground (new Color (155, 155, 222));
	boat3.addActionListener (this);
	boat3.setActionCommand ("ship3");

	boat4 = new JButton (createImageIcon ("ship3.jpg")); //button to select second 3 block ship
	boat4.setBackground (new Color (155, 155, 222));
	boat4.addActionListener (this);
	boat4.setActionCommand ("ship4");

	boat5 = new JButton (createImageIcon ("ship4.jpg")); //button to select 2 block ship
	boat5.setBackground (new Color (155, 155, 222));
	boat5.addActionListener (this);
	boat5.setActionCommand ("ship5");

	compgo = new JButton ("I'm locked in!"); //button to confirm user has made move and initialize computer's turn
	compgo.setBackground (Color.red);
	compgo.addActionListener (this);
	compgo.setActionCommand ("computer go");
	compgo.setEnabled (false);
	compgo.setPreferredSize (new Dimension (170, 35));

	redbutton = new JButton (createImageIcon ("redbutton.jpg")); //button to confirm user has placed all ships and computer can begin placing ships
	redbutton.setBackground (new Color (155, 155, 222));
	redbutton.addActionListener (this);
	redbutton.setActionCommand ("locked");
	redbutton.setBorderPainted (false);
	redbutton.setPreferredSize (new Dimension (75, 75));
	redbutton.setEnabled (false);

	JButton menu = new JButton ("Main Menu"); //button to return to main menu
	menu.setActionCommand ("1a");
	menu.addActionListener (this);
	menu.setBackground (new Color (11, 96, 13));

	//label that gives useful information of current game such as score, points needed till UAV, and prompts such as "You hit a ship"
	scorepr = new JLabel ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + prompt + "               ");
	scorepr.setBackground (new Color (11, 96, 13));
	scorepr.setFont (new Font ("Adobe Helti Std R", Font.BOLD, 15));
	scorepr.setPreferredSize (new Dimension (500, 25));

	card2.add (comp);
	card2.add (g);
	card2.add (scorepr);
	card2.add (user);
	card2.add (h);
	card2.add (redbutton);
	card2.add (boat1);
	card2.add (boat2);
	card2.add (boat3);
	card2.add (boat4);
	card2.add (boat5);
	card2.add (compgo);
	card2.add (menu);
	p_card.add ("2", card2);
    } //*******************************************************************************************************


    public void screen3 ()
    { //screen 3 is set up, also known as "Instructions" screen
	card3 = new Panel ();
	card3.setBackground (new Color (11, 96, 13));

	JLabel title = new JLabel ("Instructions");
	title.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 35));
	title.setForeground (new Color (155, 155, 222));

	instrpic = new JLabel (); //starting image is step 1 of instructions
	instrpic.setIcon (createImageIcon ("instr0.jpg"));
	instrpic.setPreferredSize (new Dimension (400, 600));

	JButton menu = new JButton ("Main Menu"); //button to return to main menu
	menu.setActionCommand ("1a");
	menu.addActionListener (this);
	menu.setBackground (new Color (155, 155, 222));

	JButton next = new JButton ("Next"); //button to scroll through steps
	next.setActionCommand ("instrn");
	next.addActionListener (this);
	next.setBackground (new Color (155, 155, 222));

	card3.add (title);
	card3.add (instrpic);
	card3.add (menu);
	card3.add (next);
	p_card.add ("3", card3);
    } //*******************************************************************************************************


    public void screen4 ()
    { //screen 4 is set up, also known as "Winning" screen. Only occurs when user has won the game
	card4 = new Panel ();
	card4.setBackground (new Color (155, 155, 222));

	JLabel title = new JLabel ("You Won!");
	title.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 35));
	title.setForeground (Color.red);

	JLabel victory = new JLabel (); //image for screen
	victory.setIcon (createImageIcon ("victory.jpg"));

	JLabel scorelabel = new JLabel ("Score: " + score + "                    "); //screen gives final score to user
	scorelabel.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 25));
	scorelabel.setForeground (Color.red);

	JLabel menu = new JLabel ("Please close the window to restart"); //in order to restart user must restart game
	menu.setForeground (new Color (11, 96, 13));

	card4.add (title);
	card4.add (victory);
	card4.add (scorelabel);
	card4.add (menu);
	p_card.add ("4", card4);
    } //*******************************************************************************************************


    public void screen5 ()
    { //screen 5 is set up, also known as "Losing" screen, has same format as screen4. only occurs if user loses the game
	card5 = new Panel ();
	card5.setBackground (new Color (155, 155, 222));

	JLabel title = new JLabel ("You Lost!");
	title.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 35));
	title.setForeground (Color.red);

	JLabel loss = new JLabel ();
	loss.setIcon (createImageIcon ("loss.jpg"));

	JLabel scorelabel = new JLabel ("Score: " + score + "                  ");
	scorelabel.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 25));
	scorelabel.setForeground (Color.red);

	JLabel menu = new JLabel ("Please close the window to restart");
	menu.setForeground (new Color (11, 96, 13));

	card5.add (title);
	card5.add (loss);
	card5.add (scorelabel);
	card5.add (menu);
	p_card.add ("5", card5);
    } //*******************************************************************************************************


    public void screen6 ()
    { //screen 6 is set up, also known as "Options" screen
	card6 = new Panel ();
	card6.setBackground (new Color (155, 155, 222));

	JLabel title = new JLabel ("Options               ");
	title.setFont (new Font ("Rockwell Extra Bold", Font.BOLD, 35));
	title.setForeground (new Color (11, 96, 13));

	JLabel soundlabel = new JLabel ("Toggle Sound On: "); //user has the option of turning sound on
	soundlabel.setForeground (new Color (11, 96, 13));
	soundlabel.setFont (new Font ("Adobe Helti Std R", Font.BOLD, 15));

	soundon = new JRadioButton ("On"); //radio button used for turning sound on
	soundon.addActionListener (this);
	soundon.setActionCommand ("son");

	JLabel cnamelabel = new JLabel ("Change The Computer's Name: "); //user has option of changing computer's name, this will affect prompt for when computers does an action
	cnamelabel.setForeground (new Color (11, 96, 13));
	cnamelabel.setFont (new Font ("Adobe Helti Std R", Font.BOLD, 15));

	cname = new JTextField (8); //text field to enter new name of computer

	JLabel diflabel = new JLabel ("Select Computer Difficulty: "); //user can select difficulty of AI, default being "easy"
	diflabel.setForeground (new Color (11, 96, 13));
	diflabel.setFont (new Font ("Adobe Helti Std R", Font.BOLD, 15));

	dif_s = "Easy";
	String[] difStrings = {"Easy", "Medium", "Hard"};
	JComboBox dif = new JComboBox (difStrings); //a combo box is used to sleect dificulty from options of, easy, medium, hard
	dif.setActionCommand ("dif");
	dif.addActionListener (this);

	JLabel passlabel = new JLabel ("Enter Password to Open Console: "); //only to be used by admins/testers. Shows a console that displays the grid, can be useful to find bugs/status of board, password is "admin"
	passlabel.setForeground (new Color (11, 96, 13));
	passlabel.setFont (new Font ("Adobe Helti Std R", Font.BOLD, 15));

	p = new JPasswordField (5); //password field, password is "admin"
	JButton done = new JButton ("Done");
	done.setActionCommand ("Done");
	done.addActionListener (this);

	JButton menu = new JButton ("Main Menu"); //button to return to main menu
	menu.setActionCommand ("1a");
	menu.addActionListener (this);
	menu.setBackground (new Color (11, 96, 13));

	card6.add (title);
	card6.add (soundlabel);
	card6.add (soundon);
	card6.add (cnamelabel);
	card6.add (cname);
	card6.add (diflabel);
	card6.add (dif);
	card6.add (passlabel);
	card6.add (p);
	card6.add (done);
	card6.add (menu);
	p_card.add ("6", card6);
    } //*******************************************************************************************************


    public void actionPerformed (ActionEvent e)
    { //start of Action Performed
	if (e.getActionCommand ().equals ("1a")) //command that will switch to screen1, Main Menu
	{
	    resize (400, 250);
	    cdLayout.show (p_card, "1");
	    String cname2 = cname.getText ();
	    compname = cname2;
	}
	else if (e.getActionCommand ().equals ("2a")) //command that will switch to screen2, Game Screen
	{
	    resize (525, 920);
	    cdLayout.show (p_card, "2");
	}
	else if (e.getActionCommand ().equals ("instr")) //command that will switch to screen3, Instructions
	{
	    resize (442, 716);
	    cdLayout.show (p_card, "3");
	}
	else if (e.getActionCommand ().equals ("4a")) //command that will switch to screen4, Winning screen
	{
	    cdLayout.show (p_card, "4");
	    resize (400, 350);
	}
	else if (e.getActionCommand ().equals ("5a")) //command that will switch to screen5, Losing screen
	{
	    cdLayout.show (p_card, "5");
	    resize (400, 350);
	}
	else if (e.getActionCommand ().equals ("op")) //command that will switch to screen6, Options
	    cdLayout.show (p_card, "6");
	else if (e.getActionCommand ().equals ("son")) //command that will begin playing music
	{
	    playMusic ("Battleship"); //method "playMusic" is called with song file name in brackets
	    soundon.setEnabled (false);
	}
	else if (e.getActionCommand ().equals ("Done")) //button to confirm password entry
	{
	    if (p.getText ().equals ("admin"))
	    {
		JOptionPane.showMessageDialog (null, "Console Activated", "Welcome Admin", JOptionPane.INFORMATION_MESSAGE);
		seecheck = true;
	    }
	    else
		JOptionPane.showMessageDialog (null, "Incorrect Login", "Unauthorized Access", JOptionPane.ERROR_MESSAGE);
	    p.setText ("");
	}
	else if (e.getActionCommand ().equals ("dif")) //command to select difficulty
	{
	    JComboBox cb = (JComboBox) e.getSource ();
	    dif_s = (String) cb.getSelectedItem (); //turns selection into string

	    if (dif_s.equals ("Medium")) //depending on selection a char will be set to be used later
		difficulty = 'm';
	    else if (dif_s.equals ("Hard"))
		difficulty = 'h';
	    else if (dif_s.equals ("Easy"))
		difficulty = 'e';
	}
	else if (e.getActionCommand ().equals ("instrn")) //command to swicth to next instructions
	{
	    if (instrp == 0) //depending on instrp value instruction step will change
		JOptionPane.showMessageDialog (null, "Select a ship and then select a place on the board, your ship will place right of that spot.", "Step 1", JOptionPane.ERROR_MESSAGE);
	    else if (instrp == 1)
		JOptionPane.showMessageDialog (null, "Once you are ready to begin press the red button to have the enemy place their ships.", "Step 2", JOptionPane.ERROR_MESSAGE);
	    else
		JOptionPane.showMessageDialog (null, "Press on the enemies board to attack, once done press on the \"locked in\" button to have the enemy attack.\nWhoever sinks all the opponents ships first wins. A whirlpool means you missed and is worth 10 points, \na fire means you hit, and is worth 30 points. Every 150 points you will earn a UAV that will show you the location of one enemy piece.", "Step 3", JOptionPane.ERROR_MESSAGE);
	    instrp++;
	    if (instrp > 2) //if instrp reaches 3 it will reset to 0 making instructions on an infinite loop
		instrp = 0;
	    instrpic.setIcon (createImageIcon ("instr" + instrp + ".jpg")); //image will be set depending on instrp value, images are 0-2
	}

	else if (e.getActionCommand ().equals ("ship1")) //setting up board for 5 piece ship
	{
	    temp = 1; // value will be used in method userPIECE()
	    boat1.setEnabled (false); //disables button
	    for (int y = 6 ; y < b.length ; y += 10) //disables grid spots that are out of bounds for this ship to ensure ship pieces are in the same row
	    {
		b [y].setEnabled (false);
		b [y + 1].setEnabled (false);
		b [y + 2].setEnabled (false);
		b [y + 3].setEnabled (false);
	    }
	    for (int x = 0 ; x < b.length ; x++) //disbales spots beside any other ships to ensure ship does not overlap other ships
	    {
		if (tracker [x] == 1)
		{
		    b [x - 4].setEnabled (false);
		    b [x - 3].setEnabled (false);
		    b [x - 2].setEnabled (false);
		    b [x - 1].setEnabled (false);
		    b [x].setEnabled (false);
		}
	    }
	    buttons++; //counter to keep count of how many buttons were pressed to be used later to initialize computer to place their pieces
	}
	else if (e.getActionCommand ().equals ("ship2")) //setting up board for 4 piece ship, same components as ActionCommand for "ship1"
	{
	    temp = 2;
	    boat2.setEnabled (false);
	    for (int y = 7 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (false);
		b [y + 1].setEnabled (false);
		b [y + 2].setEnabled (false);
	    }

	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 3].setEnabled (false);
		    b [x - 2].setEnabled (false);
		    b [x - 1].setEnabled (false);
		    b [x].setEnabled (false);
		}
	    }
	    buttons++;
	}
	else if (e.getActionCommand ().equals ("ship3")) //setting up board for 3 piece ship, same components as ActionCommand for "ship1"
	{
	    temp = 3;
	    boat3.setEnabled (false);
	    for (int y = 8 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (false);
		b [y + 1].setEnabled (false);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 2].setEnabled (false);
		    b [x - 1].setEnabled (false);
		    b [x].setEnabled (false);
		}
	    }
	    buttons++;
	}
	else if (e.getActionCommand ().equals ("ship4")) //setting up board for 3 piece ship, same components as ActionCommand for "ship1"
	{
	    temp = 3;
	    boat4.setEnabled (false);
	    for (int y = 8 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (false);
		b [y + 1].setEnabled (false);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 2].setEnabled (false);
		    b [x - 1].setEnabled (false);
		    b [x].setEnabled (false);
		}
	    }
	    buttons++;
	}
	else if (e.getActionCommand ().equals ("ship5")) //setting up board for 2 piece ship, same components as ActionCommand for "ship1"
	{
	    temp = 5;
	    boat5.setEnabled (false);
	    for (int y = 9 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (false);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 1].setEnabled (false);
		    b [x].setEnabled (false);
		}

	    }
	    buttons++;
	}
	else if (e.getActionCommand ().equals ("locked")) //button for locking in the users pieces and starting the game
	{
	    redbutton.setEnabled (false);
	    compPIECE (); //calls method to place computer's pieces

	    for (int i = 0 ; i < a.length ; i++)
	    {
		a [i].setEnabled (true); //enables all enemy grid buttons so user can begin attacking
		b [i].setActionCommand ("-1"); //sets value of user's grid ActionCommand as -1 so java does not confuse AI's grid with user's grid
	    }
	    buttons = 6;
	}
	else if (e.getActionCommand ().equals ("computer go")) //command to initialize computer's move
	{
	    if (difficulty == 'h') //depending on difficulty chosen on options screen computer will utilize different methods
		comphitORmissHARD ();
	    else if (difficulty == 'm')
		comphitORmissMEDIUM ();
	    else //default is easy
		comphitORmiss ();

	    for (int i = 0 ; i < a.length ; i++) //after computer is done computer's grid is turne don again
	    {
		a [i].setEnabled (true);
	    }
	    turn = 'u';
	    compgo.setEnabled (false); //after computer is done button is disabled till user makes move again
	}
	else
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    if (buttons <= 5)//calls method for user to move their ships on to the board
		userPIECE (n);
	    else if (n == -1)//error message if user clicked on their own board 
		showStatus ("Don't bomb your own boats. Click on the other");
	    else
	    {
		if (!win () && !compwin ()) //runs code below till someone wins 
		{
		    if (turn == 'u') //for users turn 
		    {
			for (int i = 0 ; i < a.length ; i++) //enables computer's grid so user can attack 
			{
			    a [i].setEnabled (true);
			}
			hitORmiss (n); //calls method to identify if user's selection was a hit or miss 
			for (int i = 0 ; i < a.length ; i++) //disables computer's grid if user has made their move 
			{
			    a [i].setEnabled (false);
			}
			compgo.setEnabled (true); //turns on button to start computer's turn 
		    }
		}
		
		if (win ()) //shows user's winning screen
		    cdLayout.show (p_card, "4");
		else if (compwin ()) //shows user's losing screen
		    cdLayout.show (p_card, "5");
	    }
	}
	count++;
	if (seecheck == true) //will only occur if user enters password "admin" in options to open console 
	    see ();
    } //*******************************************************************************************************


    public void compPIECE () //method to have computer place their pieces 
    {
	JOptionPane.showMessageDialog (null, "The Computer is setting up their board", "ALERT", JOptionPane.ERROR_MESSAGE); //pane to let user know computer is placing ships 

	int cship1 = (int) (Math.random () * 100); //places the first ship in random spot 
	int y = cship1 % 10;
	while (y > 5) //ensures ship placement is in valid spot 
	{
	    cship1 = (int) (Math.random () * 100);
	    y = cship1 % 10;
	}
	comptracker [cship1] = 1; //sets values of on tracker arrya as 1 to be used for hit detection 
	comptracker [cship1 + 1] = 1;
	comptracker [cship1 + 2] = 1;
	comptracker [cship1 + 3] = 1;
	comptracker [cship1 + 4] = 1;

	int cship2 = (int) (Math.random () * 100);//places the second ship in random spot 
	int y2 = cship2 % 10;
	while (y2 > 4 || comptracker [cship2 + 1] == 1 || comptracker [cship2 + 3] == 1)
	{
	    cship2 = (int) (Math.random () * 100);
	    y2 = cship2 % 10;
	}
	comptracker [cship2] = 1;
	comptracker [cship2 + 1] = 1;
	comptracker [cship2 + 2] = 1;
	comptracker [cship2 + 3] = 1;

	int cship3 = (int) (Math.random () * 100);//places the third ship in random spot 
	int y3 = cship3 % 10;
	while (y3 > 3 || comptracker [cship3 + 1] == 1 || comptracker [cship3 + 2] == 1)
	{
	    cship3 = (int) (Math.random () * 100);
	    y3 = cship3 % 10;
	}
	comptracker [cship3] = 1;
	comptracker [cship3 + 1] = 1;
	comptracker [cship3 + 2] = 1;

	int cship4 = (int) (Math.random () * 100);//places the fourth ship in random spot 
	int y4 = cship4 % 10;
	while (y4 > 3 || comptracker [cship4 + 1] == 1 || comptracker [cship4 + 2] == 1)
	{
	    cship4 = (int) (Math.random () * 100);
	    y4 = cship4 % 10;
	}
	comptracker [cship4] = 1;
	comptracker [cship4 + 1] = 1;
	comptracker [cship4 + 2] = 1;

	int cship5 = (int) (Math.random () * 100);//places the fifth ship in random spot 
	int y5 = cship5 % 10;
	while (y5 > 2 || comptracker [cship5] == 1 || comptracker [cship5 + 1] == 1)
	{
	    cship5 = (int) (Math.random () * 100);
	    y5 = cship5 % 10;
	}
	comptracker [cship5] = 1;
	comptracker [cship5 + 1] = 1;
    } //*******************************************************************************************************

    public void userPIECE (int n) //method to place user's pieces 
    {
	if (temp == 1) //sets attributes of 5 block ship
	{
	    b [n].setIcon (createImageIcon ("ship1p1.jpg")); //sets images 
	    b [n + 1].setIcon (createImageIcon ("ship1p2.jpg"));
	    b [n + 2].setIcon (createImageIcon ("ship1p3.jpg"));
	    b [n + 3].setIcon (createImageIcon ("ship1p4.jpg"));
	    b [n + 4].setIcon (createImageIcon ("ship1p5.jpg"));
	    b [n].setBorderPainted (false); //disables images 
	    b [n + 1].setBorderPainted (false);
	    b [n + 2].setBorderPainted (false);
	    b [n + 3].setBorderPainted (false);
	    b [n + 4].setBorderPainted (false);
	    tracker [n] = 1; //sets array's values of 1 to be used for hit detection 
	    tracker [n + 1] = 1;
	    tracker [n + 2] = 1;
	    tracker [n + 3] = 1;
	    tracker [n + 4] = 1;
	    for (int y = 6 ; y < b.length ; y += 10) //disables out of bounds area for this ship 
	    {
		b [y].setEnabled (true);
		b [y + 1].setEnabled (true);
		b [y + 2].setEnabled (true);
		b [y + 3].setEnabled (true);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 4].setEnabled (true);
		    b [x - 3].setEnabled (true);
		    b [x - 2].setEnabled (true);
		    b [x - 1].setEnabled (true);
		    b [x].setEnabled (true);
		}
	    }
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU PLACED A SHIP"); //updates prompt
	    temp = 0;
	}

	else if (temp == 2) //sets attributes for 4 block ship, same functions as if statement before this 
	{
	    b [n].setIcon (createImageIcon ("ship2p1.jpg"));
	    b [n + 1].setIcon (createImageIcon ("ship2p2.jpg"));
	    b [n + 2].setIcon (createImageIcon ("ship2p3.jpg"));
	    b [n + 3].setIcon (createImageIcon ("ship2p4.jpg"));
	    b [n].setBorderPainted (false);
	    b [n + 1].setBorderPainted (false);
	    b [n + 2].setBorderPainted (false);
	    b [n + 3].setBorderPainted (false);
	    tracker [n] = 1;
	    tracker [n + 1] = 1;
	    tracker [n + 2] = 1;
	    tracker [n + 3] = 1;
	    for (int y = 7 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (true);
		b [y + 1].setEnabled (true);
		b [y + 2].setEnabled (true);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 3].setEnabled (true);
		    b [x - 2].setEnabled (true);
		    b [x - 1].setEnabled (true);
		    b [x].setEnabled (true);
		}
	    }
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU PLACED A SHIP");
	    temp = 0;
	}
	else if (temp == 3) //sets attributes for 3 block ship, used for both boat3 and boat4, same functions as if statement before this 
	{
	    b [n].setIcon (createImageIcon ("ship3p1.jpg"));
	    b [n + 1].setIcon (createImageIcon ("ship3p2.jpg"));
	    b [n + 2].setIcon (createImageIcon ("ship3p3.jpg"));
	    b [n].setBorderPainted (false);
	    b [n + 1].setBorderPainted (false);
	    b [n + 2].setBorderPainted (false);
	    tracker [n] = 1;
	    tracker [n + 1] = 1;
	    tracker [n + 2] = 1;
	    for (int y = 8 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (true);
		b [y + 1].setEnabled (true);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {
		if (tracker [x] == 1)
		{
		    b [x - 2].setEnabled (true);
		    b [x - 1].setEnabled (true);
		    b [x].setEnabled (true);
		}
	    }
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU PLACED A SHIP");
	    temp = 0;
	}
	else if (temp == 5)//sets attributes for 2 block ship, same functions as if statement before this 
	{
	    b [n].setIcon (createImageIcon ("ship4p1.jpg"));
	    b [n + 1].setIcon (createImageIcon ("ship4p2.jpg"));
	    b [n].setBorderPainted (false);
	    b [n + 1].setBorderPainted (false);
	    tracker [n] = 1;
	    tracker [n + 1] = 1;
	    for (int y = 9 ; y < b.length ; y += 10)
	    {
		b [y].setEnabled (true);
	    }
	    for (int x = 0 ; x < b.length ; x++)
	    {

		if (tracker [x] == 1)
		{
		    b [x - 1].setEnabled (true);
		    b [x].setEnabled (true);
		}
	    }
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU PLACED A SHIP");
	    temp = 0;
	}
	if (buttons == 5) //enables redbutton to allow computer to place ships 
	    redbutton.setEnabled (true);
    }//*******************************************************************************************************

    public void hitORmiss (int n) //method for detecting if user hit or missed ship 
    {
	if (comptracker [n] == 1)//detects if grid piece is a ship by seeing if array value is 1 
	{
	    score += 30; //bumps up score by 30
	    trackscore += 30; //trackscore used to find score needed to get uav 
	    a [n].setIcon (createImageIcon ("hit.jpg")); //sets icon as hit icon 
	    showStatus ("Hit!"); //updates status at bototm of window 
	    comptracker [n] = 2; //updates array value to 2 to signify hit 
	    if (trackscore >= 150) //activates UAV method if trackscore is 150 or above, then resets trackscore to 0
	    {
		UAV ();
		trackscore = 0;
	    }
	    streak = 150 - trackscore; //streak is prompt for points required for UAV
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU HIT A SHIP");
	}
	else if (comptracker [n] == 0) //dtects if user missed
	{
	    score += 10; //adds 10 score 
	    trackscore += 10; //adds 10 trackscore 
	    a [n].setIcon (createImageIcon ("miss.jpg")); //sets icon as missed 
	    showStatus ("Miss!"); //updates status at bottom of window 
	    comptracker [n] = 3; //updates array value as 3 to signify miss 
	    if (trackscore >= 150) //if trackscore is over 150 UAV method will be called 
	    {
		UAV ();
		trackscore = 0;
	    }
	    streak = 150 - trackscore; //updates prompts 
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///YOU MISSED");
	}
    }//*******************************************************************************************************

    public void comphitORmiss ()//method for computer to make a move, for easy difficulty 
    {
	int c = (int) (Math.random () * 99 + 1); //selects random spot to move 
	while (tracker [c] == 2 && tracker [c] == 3) //if random spot has already been hit and has value of 2 or 3 computer will slect another random number 
	{
	    c = (int) (Math.random () * 99 + 1);
	}
	if (tracker [c] == 1)//changes attributes for a hit 
	{
	    b [c].setIcon (createImageIcon ("hit.jpg"));
	    tracker [c] = 2;
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
	}
	else if (tracker [c] == 0)//change attributes for a miss 
	{
	    b [c].setIcon (createImageIcon ("miss.jpg"));
	    tracker [c] = 3;
	    scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
	}
    }//*******************************************************************************************************

    public void comphitORmissMEDIUM () //method for computer's move om medium difficulty 
    {
	if (tracker [current + 1] >= 100 || tracker [current + 2] >= 100 || tracker [current + 3] >= 100 || tracker [current + 4] >= 100) //if where computer is trying to move is out of bounds they will be redirected elsewhere
	    start = -1;
	else if (tracker [current - 1] <= -1 || tracker [current - 2] <= -1 || tracker [current - 3] <= -1 || tracker [current - 4] <= -1)
	    start = 0;

	else if (start == 1)//value of 1 means hit the block to your right, then checks if it was a ship or not 
	{
	    if (tracker [current + 1] == 1)
	    {
		b [current + 1].setIcon (createImageIcon ("hit.jpg"));
		tracker [current + 1] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 2; //if it was a hit tells computer to hit block 2 to the right on next move 
	    }
	    else if (tracker [current + 1] == 2 || tracker [current + 1] == 3)
		start = -1; //if block is occupied tells computer to hit block to left 
	    else if (tracker [current + 1] == 0)
	    {
		b [current + 1].setIcon (createImageIcon ("miss.jpg"));
		tracker [current + 1] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = -1;//if it was a miss tells computer to hit block to the left on next move 
	    }
	}
	else if (start == 2)//value of 2 means hit block 2 to the right 
	{
	    if (tracker [current + 2] == 1)
	    {
		b [current + 2].setIcon (createImageIcon ("hit.jpg"));
		tracker [current + 2] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 3;//if hit tells computer to hit block 3 to reight on next move 
	    }
	    else if (tracker [current + 2] == 2 || tracker [current + 2] == 3)
		start = -1;//otherwise if occpied hit to the left
	    else if (tracker [current + 2] == 0)
	    {
		b [current + 2].setIcon (createImageIcon ("miss.jpg"));
		tracker [current + 2] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = -1; //otherwise hit to the left on next move 
	    }
	}
	else if (start == 3)
	{
	    if (tracker [current + 3] == 1)
	    {
		b [current + 3].setIcon (createImageIcon ("hit.jpg"));
		tracker [current + 3] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 4;//if hit tells computer to hit block 4 to the right on next move 
	    }
	    else if (tracker [current + 3] == 2 || tracker [current + 3] == 3)
		start = -1;//otherwise if occupied hit to the left 
	    else if (tracker [current + 3] == 0)
	    {
		b [current + 3].setIcon (createImageIcon ("miss.jpg"));
		tracker [current + 3] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = -1;//otherwise hit to the left on next move 
	    }
	}
	else if (start == 4)
	{
	    if (tracker [current + 4] == 1)
	    {
		b [current + 4].setIcon (createImageIcon ("hit.jpg"));
		tracker [current + 4] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 0;//if computer gets this far it means they have successfully taken out a 5 piece ship and should now select a new random number 
	    }
	    else if (tracker [current + 4] == 2 || tracker [current + 4] == 3)
		start = -1; //otherwise if occupied hit to the left
	    else if (tracker [current + 4] == 0)
	    {
		b [current + 4].setIcon (createImageIcon ("miss.jpg"));
		tracker [current + 4] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = -1; //otherwise hit to the left on next move 
	    }
	}
	else if (start == -1)
	{
	    if (tracker [current - 1] == 1)
	    {
		b [current - 1].setIcon (createImageIcon ("hit.jpg"));
		tracker [current - 1] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = -2;//if hit tells computer to hit 2 to the left on next move 
	    }
	    else if (tracker [current - 1] == 2 || tracker [current - 1] == 3)
		start = 0;//otherwise select new number if occupied 
	    else if (tracker [current - 1] == 0)
	    {
		b [current - 1].setIcon (createImageIcon ("miss.jpg"));
		tracker [current - 1] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = 0;//otherwise select new number 
	    }
	}
	else if (start == -2)
	{
	    if (tracker [current - 2] == 1)
	    {
		b [current - 2].setIcon (createImageIcon ("hit.jpg"));
		tracker [current - 2] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = -3;//if hit tells computer to hit 3 to the left on next move
	    }
	    else if (tracker [current - 2] == 2 || tracker [current - 2] == 3)
		start = 0;//otherwise select new number if occupied 
	    else if (tracker [current - 2] == 0)
	    {
		b [current - 2].setIcon (createImageIcon ("miss.jpg"));
		tracker [current - 2] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = 0;//otherwise select new number 
	    }
	}
	else if (start == -3)
	{
	    if (tracker [current - 3] == 1)
	    {
		b [current - 3].setIcon (createImageIcon ("hit.jpg"));
		tracker [current - 3] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = -4;//if hit tells computer to hit 4 to the left on next move
	    }
	    else if (tracker [current - 3] == 2 || tracker [current - 3] == 3)
		start = 0;//otherwise select new number if occupied 
	    else if (tracker [current - 3] == 0)
	    {
		b [current - 3].setIcon (createImageIcon ("miss.jpg"));
		tracker [current - 3] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = 0;//otherwise select new number 
	    }
	}
	else if (start == -4)
	{
	    if (tracker [current - 4] == 1)
	    {
		b [current - 4].setIcon (createImageIcon ("hit.jpg"));
		tracker [current - 4] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 0;//if hit computer has taken out 5 block ship and a new number must be selected 
	    }
	    else if (tracker [current - 4] == 2 || tracker [current - 4] == 3)
		start = 0;//otherwise select new number if occupied 
	    else if (tracker [current - 4] == 0)
	    {
		b [current - 4].setIcon (createImageIcon ("miss.jpg"));
		tracker [current - 4] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
		start = 0;//otherwise select new number 
	    }
	}
	else if (start == 0) //selects random number 
	{
	    int n = (int) (Math.random () * 99 + 1);
	    while (tracker [n] == 2 || tracker [n] == 3)//ensure number selected is not occupied 
	    {
		n = (int) (Math.random () * 99 + 1);
	    }
	    if (tracker [n] == 1)//detects if it is a hit and will then begin sequence at top 
	    {
		b [n].setIcon (createImageIcon ("hit.jpg"));
		tracker [n] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		start = 1;
		current = n; //stores value to be used for if statements above 
	    }
	    else //otherwise it will be a miss
	    {
		b [n].setIcon (createImageIcon ("miss.jpg"));
		tracker [n] = 3;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " MISSED");
	    }
	}
    }//*******************************************************************************************************

    public void comphitORmissHARD () //method for computer to make their move, for hard difficulty 
    {
	int i = 0;
	int stop = 0;
	while (tracker [i] != 1 && stop == 0 && i != 99) //computer will go through array until it finds 1 or reahes the end
	{
	    i++;
	    if (tracker [i] == 1) //this difficulty will always find your ship without ever missing 
	    {
		b [i].setIcon (createImageIcon ("hit.jpg"));
		tracker [i] = 2;
		scorepr.setText ("///Your Score: " + score + " ///Points till next UAV: " + streak + "///" + compname + " HIT A SHIP");
		stop = 1; //stops while loop, without this, computer will hit every ship in one turn 
	    }
	}
    }//*******************************************************************************************************

    public boolean win ()//method to check if user won 
    {
	int check = 0;
	for (int i = 0 ; i < a.length ; i++) //checks arrays for 2s
	{
	    if (comptracker [i] == 2)
		check++;
	}

	if (check == 17) //if 17 ship parts were hit (total amount of pieces) it will return true 
	    return true;
	else
	    return false;
    }//*******************************************************************************************************

    public boolean compwin () //method to check if computer won 
    {
	int check = 0;
	for (int i = 0 ; i < b.length ; i++) //checks array values for 2s
	{
	    if (tracker [i] == 2)
		check++;
	}

	if (check == 17) //if computer hit all 17 pieces returns true 
	    return true;
	else
	    return false;
    }//*******************************************************************************************************

    public void UAV () //method for UAV, called when user reaches score of 150
    {
	int i = 0;
	while (comptracker [i] != 1 && i != 99)
	{
	    i++;
	    if (comptracker [i] == 1) //finds the first computer pieces and highlights it for the player 
		a [i].setIcon (createImageIcon ("UAV.jpg"));
	}
    }//*******************************************************************************************************

    public static void playMusic (String filepath)  // Method to allow music of 1MB size or smaller
    {
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try
	{
	    BGM = new AudioStream (new FileInputStream (filepath + ".wav")); //set song
	    MD = BGM.getData (); //get data fom song
	    loop = new ContinuousAudioDataStream (MD); //set as loop
	}
	catch (IOException error)  //error
	{
	    System.out.println ("Audio - File not found.");
	}
	MGP.start (loop); //start running loop
    } //*******************************************************************************************************

    public void see () //method that will appear in console when password "admin" is entered in options 
    {
	System.out.println ("-----------Computer's Grid-----------------"); //displays computer's grid array values 
	int m = 0;
	for (int k = 0 ; k < 10 ; k++)
	{
	    for (int l = 0 ; l < 10 ; l++)
	    {
		System.out.print (comptracker [m]);
		m++;
	    }
	    System.out.println ();
	}
	System.out.println ();

	System.out.println ("--------------User's Grid-----------------"); //displays user's grid array values 
	int n = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 10 ; j++)
	    {
		System.out.print (tracker [n]);
		n++;
	    }
	    System.out.println ();
	}
	System.out.println ();
    }//*******************************************************************************************************


    protected static ImageIcon createImageIcon (String path) //method for creating image 
    {
	java.net.URL imgURL = Battleship.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);//displays image 
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path); //displays error if image not found 
	    return null;
	}
    }//*******************************************************************************************************
}//END OF GAME 
