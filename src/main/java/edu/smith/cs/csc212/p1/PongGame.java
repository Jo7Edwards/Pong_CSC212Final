package edu.smith.cs.csc212.p1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

//Cite: https://docs.oracle.com/javase/7/docs/api/java/awt/Shape.html
//Cite: used a lot of the docs.oracle.com related to different specific java shapes 

/*
 * MAJOR cleanup, write doc up, maybe do power ups, like make paddle bigger or smaller 
 * 
 * really got in the zone 
 * title screen buttons took a long time, figuring out how to change and unchange color when clicked
 * describe how game works, p and q are pause buttons, how when you restart whatever you press now will
 * launch accordingly 
 * ball movement still eh 
 * AI pretty hard to beat, but because doesnt aim ball for center sometimes misses 
 * booleans are all the switches basically 
 */

//ASK ABOUT static variables, what I should do to clean up the code, etc 

public class PongGame extends GFX {
	//GFX win 500 by 500? 
	List<TitleButton> buttonz;

	TitleScreen title = new TitleScreen();
	//Aquarium needs to know the title buttons
	TitleButton startButton; 
	TitleButton onePl;
	TitleButton twoPl;
	TitleButton endl;
	TitleButton plWin;

	//For Y, 0 its at the top edge and getHeight() is at bottom edge 

	//Right and left paddle x's 75 from edge of win
	int rPaddleX = getWidth() -75;
	int lPaddleX = 75;


	//Game components, paddles and the ball, score 
	AbstractPaddle LPaddle;
	AbstractPaddle RPaddle;
	Ball ball;

	//ScoreScreen object to write/draw score
	ScoreScreen scoreScreen; 

	//If in play to win mode, first to this score wins 
	public static int WINNING_SCORE = 5;
	//Once someone wins, saves who one as a string to this 
	String winner;

	//Booleans, all saying what screen we should be on. If these are all false then the game is playing 
	boolean onTitleScreen = true;
	boolean endScreen = false;
	boolean pauseScreen = false;

	public PongGame() {
		//need to create title buttons here so they are only created once, and later can be drawn many times 
		this.buttonz = createTitleButtons();
		//create start button separately 
		startButton = new TitleButton(175, 450, 100, 50, Color.darkGray, "START", "");
		
	}
	
	
	

	/**
	 * Starts/continues game, i.e. puts into motion the paddles and ball depending 
	 * on the settings/game mode selected and passed to it 
	 * 
	 * @param singlePlayer - boolean, start the game in single player mode? T = single player, F = against AI
	 * @param endless - boolean, start the game in endless mode? T = endless, F = Play to Win
	 * @param cont - boolean, Asking if this is "continuing", i.e. if you're just resetting after a point was won
	 * @param Lscore - only needed if cont = T, is current int score of Left Paddle
	 * @param Rscore - only needed if cont = T, is current int score of Right Paddle
	 */
	public void startGame(boolean singlePlayer, boolean endless, boolean cont, int Lscore, int Rscore) {
		/*
		 * Need to "clear" what has been clicked each time you use startGame
		 * but depending on what's passed in we know what was clicked
		 */
		clearWasClicked();
		
		/*
		 * create different game pieces (paddles & ball) according to given game mode
		 * if single player then create left paddle as AIpaddle, else create as player 2 paddle
		 */
		if (singlePlayer) { 
			onePl.wasClicked = true;
			LPaddle = new AIPaddle(Color.blue, lPaddleX, getHeight()/2);
		} else { 
			twoPl.wasClicked = true;
			LPaddle = new Paddle(Color.blue, lPaddleX, getHeight()/2, false);
		}
		RPaddle = new Paddle(Color.cyan, rPaddleX, getHeight()/2, true);
		ball = new Ball(Color.orange, getWidth()/2, getHeight()/2, RPaddle, LPaddle);
		
		
		
		/*
		 * If you are continuing in a single game and not 
		 * starting a new game (with scores), then "save" the score 
		 */
		if (cont) {
			ball.scoreLP = Lscore;
			ball.scoreRP = Rscore;
		}
		
		/*
		 * If not endless mode, then play to win and create score at top of game 
		 */
		if (endless == false) {
			plWin.wasClicked = true;
			scoreScreen = new ScoreScreen();	
		} else {
			endl.wasClicked = true;	
		}
		
		/*
		 * Once startGame has finished you should no longer be on title screen
		 */
		onTitleScreen = false;
	}
	
	
	/**
	 * Set specific Title button's values for wasClicked to false 
	 */
	public void clearWasClicked() {
		this.onePl.wasClicked = false;
		this.twoPl.wasClicked = false;
		this.endl.wasClicked = false;
		this.plWin.wasClicked = false;
		this.startButton.wasClicked = false;
	}
	

	@Override
	public void draw(Graphics2D g) {
		
		// Draw the background, set the window 
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		//checks if it needs to be on any specific screen (title, pause, or end), else playing game
		if (onTitleScreen) {
			doTitleScreen(g);

		} else if (endScreen){
			doEndScreen(g);

		} else if (pauseScreen){
			doPauseScreen(g);

		} else {
			doGamePlay(g);
		}
	}

	
	
	/**
	 * Create all the necessary title buttons for different game modes 
	 * @return list of TitleButtons, which are the ones you need to check if clicked for specific game modes 
	 */
	public List<TitleButton> createTitleButtons() {
		List<TitleButton> buttons = new ArrayList<>();
		TitleButton top = new TitleButton(60, 60, 340, 100, Color.black, "Pong", "Click which modes to play");
		buttons.add(top);
		
		TitleButton first = new TitleButton(60, 200, 150, 100, Color.cyan, "1-player", "Play against CPU!");
		this.onePl = first;
		buttons.add(first);
		
		TitleButton second = new TitleButton(250, 200, 150, 100, Color.red, "2-player", "Move with W & S + Up & Down");
		this.twoPl = second; 
		buttons.add(second);
		
		TitleButton third= new TitleButton(60, 320, 150, 100, Color.green, "Endless", "");
		this.endl = third;
		buttons.add(third);
		
		TitleButton fourth = new TitleButton(250, 320, 150, 100, Color.magenta, "Play to win", "First to 5 wins!");
		this.plWin = fourth;
		buttons.add(fourth);
		
		return buttons;
	}
	
	
	/**
	 * Any other extra words (or other drawn things) I want to add to title screen go in this method
	 * @param Graphics2D g
	 */
	public void drawExtraTitleWords(Graphics2D g) {
		Font winNum = g.getFont();
		g.setFont(winNum.deriveFont(14f));
		g.setColor(Color.black);
		g.drawString("Pause or quit anytime", 320, 475);
		g.drawString("with Q or P", 320, 490);
	}
	
	
	/**
	 * Execute this method to draw all title screen things and do all title screen things 
	 * @param Graphics2D g
	 */
	public void doTitleScreen(Graphics2D g) {
		/*
		 * Draw necessary title screen things 
		 */
		title.drawTitle(g);
		startButton.draw(g);
		for (TitleButton b : buttonz) {
			b.draw(g);
		}
		drawExtraTitleWords(g);
		
		/*
		 * If user clicks in window, check if they clicked on Game mode button or start button 
		 * if they click on game mode button, check whether it should make the button selected or unselected 
		 */
		IntPoint click = this.processClick();
		if (click != null) {
			for (TitleButton b : buttonz ) {
				if (b.contains(click) && b.wasClicked == false) { 
					b.wasClicked = true; 
				} else if (b.contains(click) && b.wasClicked == true) {
					b.wasClicked = false;
				}
			}
			
			/*
			 * If the start button was clicked, check that user selected the right # of modes 
			 * if 2 buttons clicked, then decide how to start game accordingly 
			 */
			if (startButton.contains(click)) {
				int numclicked = 0;
				for (TitleButton b : buttonz) { 
					if (b.wasClicked) {
						numclicked += 1; 
					} 
				}
				if (numclicked == 2) {
					decideGameModeToStart(); 
				} else if (numclicked < 2) {
					System.out.println("You didn't pick all the options! Pick more and try again");
				} else { 
					System.out.println("You probably clicked too many options, unclick some stuff "
							+ "and try again");
				}
			}
		}
	}
	
	
	
	/**
	 * Does all the drawing and things to execute the pause screen, 
	 * should happen any time P or Q is pressed. 
	 * @param Graphics2D g
	 */
	public void doPauseScreen(Graphics2D g) {
		
		IntPoint click = this.processClick();

		title.drawTitle(g);

		/*
		 * Draw/write "Paused"
		 */
		Font font = g.getFont();
		g.setFont(font.deriveFont(35f));
		g.setColor(Color.black);
		g.drawString("PAUSED", 150, 150);
		
		
		/*
		 * make 2 new buttons, resume to resume the game and quit to exit game 
		 * then check whether either was clicked
		 */
		TitleButton resume = new TitleButton(180, 300, 150, 55, Color.blue, "Continue", "");
		resume.draw(g);
		TitleButton quit = new TitleButton(180, 385, 150, 55, Color.red, "Quit", "");
		quit.draw(g);
		
		if (click != null && resume.contains(click)) {
			pauseScreen = false;
		} else if (click != null && quit.contains(click)) {
			pauseScreen = false;
			onTitleScreen = true;
		}
	}
	
	
	/**
	 * Do all the things to execute the end screen, after a Play to Win game ends 
	 * 
	 * @param g
	 */
	public void doEndScreen(Graphics2D g) { 
		IntPoint click = this.processClick();
		
		title.drawTitle(g);
		
		/*
		 * Draw/write who won on the screen 
		 */
		Font font = g.getFont();
		g.setFont(font.deriveFont(25f));
		g.setColor(Color.black);
		g.drawString(this.winner + " won!", 150, 150);
		
		/*
		 * Make new button Restart to give user option to return to title screen 
		 * then check if it was clicked 
		 */
		TitleButton restart = new TitleButton(180, 300, 100, 48, Color.green.darker(), "Restart", "");
		restart.draw(g);

		if (click != null && restart.contains(click)) {
			endScreen = false; 
			onTitleScreen = true;
		}
	}
	
	
	/**
	 * Execute actual game play, with paddles and ball moving
	 * 
	 * @param g
	 */
	public void doGamePlay(Graphics2D g) {
		
		/*
		 * check if Q or P is pressed, and go to pause screen if it is 
		 */
		if (isKeyDown(KeyEvent.VK_P) || isKeyDown(KeyEvent.VK_Q)) {
			pauseScreen = true;
		}
		

		/*
		 * if a point was just won, reset ball to middle 
		 */
		if (ball.justInc) {
			startGame(onePl.wasClicked, endl.wasClicked, true, ball.scoreLP, ball.scoreRP);
		}
		
		/*
		 * draw/animate the ball and paddles 
		 */
		ball.draw(g);
		LPaddle.draw(g, this, ball);
		RPaddle.draw(g, this, ball); //pass 'this' to give it GFX / access to window 

		/*
		 * If game is in play to win mode
		 */
		if (this.endl.wasClicked == false) {
			scoreScreen.drawScore(g, ball.scoreLP, false);
			scoreScreen.drawScore(g,  ball.scoreRP, true);

			//Winning Scores! 
			if (ball.scoreLP == WINNING_SCORE) {
				this.winner = "Left Paddle";
				this.endScreen = true;
			} else if (ball.scoreRP == WINNING_SCORE) {
				this.winner = "Right Paddle";
				this.endScreen = true;
			}
		}
	}
	
	/*
	 * Based on boolean values wasClicked of the Title buttons
	 * i.e based on what title buttons were clicked, execute game accordingly  
	 */
	public void decideGameModeToStart() {
		if (onePl.wasClicked) {
			if (endl.wasClicked) {
				startGame(true, true, false, 0, 0);
			} else {
				startGame(true, false, false, 0, 0);
			}
		} else if (twoPl.wasClicked) {
			
			if (endl.wasClicked) {
				startGame(false, true, false, 0, 0); 
			} else {
				startGame(false, false, false, 0, 0);
			}
		}
	}
	
	

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new PongGame();
		app.start();
	}

}
