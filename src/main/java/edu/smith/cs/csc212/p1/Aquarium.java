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

public class Aquarium extends GFX {
	 //GFX win 500 by 500? 
	List<TitleButton> buttonz;
	
	TitleButton startButton; 
	TitleButton onePl;
	TitleButton twoPl;
	TitleButton endl;
	TitleButton plWin;
	
	public Aquarium() {
		this.buttonz = createTitleButtons();
		startButton = new TitleButton(175, 450, 100, 50, Color.darkGray, "START");
		//startGame(true);
	}
	
	//For Y, 0 its at the top edge and getHeight() is at bottom edge 

	public boolean up = false;
	boolean down = false;
	
	//Right and left paddle x distances from edge of win
	int rPaddleX = getWidth() -75;
	int lPaddleX = 75;
	
	//Make our paddles 
	AbstractPaddle LPaddle;
	AbstractPaddle RPaddle;
	Ball ball;
	
	ScoreScreen score; 
	
	static int WINNING_SCORE = 5;
	
	
	
	
	
	boolean titlestart = true;
	String winner;
	
	
	
	boolean onTitleScreen = true;
	boolean endScreen = false;
	boolean pauseScreen = false;
	

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
		//Need to "clear" what has been clicked each time you use startGame
		this.onePl.wasClicked = false;
		this.twoPl.wasClicked = false;
		this.endl.wasClicked = false;
		this.plWin.wasClicked = false;
		this.startButton.wasClicked = false;
		
		
		if (singlePlayer) {
			onePl.wasClicked = true;
			//If single player create AI paddle on left side 
			LPaddle = new AIPaddle(Color.blue, lPaddleX, getHeight()/2);
		} else {
			twoPl.wasClicked = true;
		LPaddle = new Paddle(Color.blue, lPaddleX, getHeight()/2, false);
		}
		RPaddle = new Paddle(Color.cyan, rPaddleX, getHeight()/2, true);
		ball = new Ball(Color.orange, getWidth()/2, getHeight()/2, RPaddle, LPaddle);
		
		if (cont) {
			ball.scoreLP = Lscore;
			ball.scoreRP = Rscore;
		}
		
		if (endless == false) {
			plWin.wasClicked = true;
			score = new ScoreScreen();
			
		} else {
			endl.wasClicked = true;
			
		}
		
		
		onTitleScreen = false;
	}
	public void beforeGame(Graphics2D g) {
		onTitleScreen = true; 
		TitleScreen title = new TitleScreen();
		title.drawTitle(g);
		
		//this.buttonz = drawTitleButtons(g);
		
	}
	/*
	 * make a variable that chacks how many time process cey hhow many times user has clicked in window 
	 * then if greater than two
	 * then if topleft button.is clicked qnd bottom right is clicked 
	 * do that gamem mode and also set the title screen to null 
	 * (non-Javadoc)
	 * @see me.jjfoley.gfx.GFX#draw(java.awt.Graphics2D)
	 */

	@Override
	public void draw(Graphics2D g) {
		
		
		// Draw the background, set the window 
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		//TitleScreen title = new TitleScreen();
		//List<TitleButton> buttons = new ArrayList<>();
		//This is to draw the title screen
		TitleScreen title = new TitleScreen();
		
		//if on title screen, do all these title screen things 
		if (onTitleScreen) {
			
			title.drawTitle(g);
			
			startButton.draw(g);
			
			for (TitleButton b : buttonz) {
				b.draw(g);
			}
			Font font = g.getFont();
			g.setFont(font.deriveFont(14f));
			
			g.setColor(Color.white);
			g.drawString("(First to 5 wins!)", 270, 392);
			
			
			
			IntPoint click = this.processClick();
			//Check all these things for when user clicks in the window
			if (click != null) {
				for (TitleButton b : buttonz ) {
					//b.wasClicked = false; 
					if (b.contains(click) && b.wasClicked == false) {
						b.wasClicked = true; //makes it change color within TitleButton 
						//b.color = Color.black;
					} else if (b.contains(click) && b.wasClicked == true) {
						b.wasClicked = false;
						//b.color = Color.white;
						System.out.println("UPPITY");
					}

				}
				//If the start button was clicked, check that user selected the right # of modes 
				if (startButton.contains(click)) {
					int numclicked = 0;
					for (TitleButton b : buttonz) { 
						if (b.wasClicked) {
							numclicked += 1; 
						} 
					}
					if (numclicked == 2) {
						
						if (onePl.wasClicked) {
							
							if (endl.wasClicked) {
								startGame(true, true, false, 0, 0);
							} else {
								startGame(true, false, false, 0 ,0);
								
							}
							
						} else if (twoPl.wasClicked) {
							
							if (endl.wasClicked) {
								startGame(false, true, false, 0,0); 
							} else {
								startGame(false, false, false, 0, 0);
							}
						}
						

					} else if (numclicked < 2) {
						System.out.println("You didn't pick all the options! Pick more and try again");
						//say you didnt pick all options
						
					} else { 
						System.out.println("You probably clicked too many options, unclick some stuff "
								+ "and try again");
						//Picked too many, unclick something 
					}
				}
			}
			
			

		} else if (endScreen){
			IntPoint click = this.processClick();
			
			title.drawTitle(g);
			
			Font font = g.getFont();
			g.setFont(font.deriveFont(25f));
			
			g.setColor(Color.black);
			g.drawString(this.winner + " won!", 150, 150);
			TitleButton restart = new TitleButton(180, 300, 100, 48, Color.green.darker(), "Restart");
			restart.draw(g);

			if (click != null && restart.contains(click)) {

				endScreen = false; 
				onTitleScreen = true;
			}


		} else if (pauseScreen){
			IntPoint click = this.processClick();

			title.drawTitle(g);

			Font font = g.getFont();
			g.setFont(font.deriveFont(35f));

			g.setColor(Color.black);
			g.drawString("PAUSED", 150, 150);
			
			TitleButton resume = new TitleButton(180, 300, 150, 55, Color.blue, "Continue");
			resume.draw(g);
			
			TitleButton quit = new TitleButton(180, 385, 150, 55, Color.red, "Quit");
			quit.draw(g);
			

			if (click != null && resume.contains(click)) {
				pauseScreen = false;

			} else if (click != null && quit.contains(click)) {
				pauseScreen = false;
				onTitleScreen = true;
			}




		} else {
			
			
			if (isKeyDown(KeyEvent.VK_P) || isKeyDown(KeyEvent.VK_Q)) {
				pauseScreen = true;
			}
			//If not on titlescreen, endScreen, or pause screen, then execute the game things! 

			//if a point was just won, reset ball to middle 
			if (ball.justInc) {
				startGame(onePl.wasClicked, endl.wasClicked, true, ball.scoreLP, ball.scoreRP);
			}
			
			ball.draw(g);
			LPaddle.draw(g, this, ball);
			RPaddle.draw(g, this, ball); //pass 'this' to give it GFX / access to window 

			//If game is in play to win mode
			if (this.endl.wasClicked == false) {
				score.drawScore(g, ball.scoreLP, false);
				score.drawScore(g,  ball.scoreRP, true);

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




	}
	
	public boolean getUp() {
		return this.up;
	}
	
	
	
	public List<TitleButton> createTitleButtons() {
		List<TitleButton> buttons = new ArrayList<>();
		TitleButton top = new TitleButton(60, 60, 340, 100, Color.black, "Click which modes to play");
		buttons.add(top);
		//top.draw(g);
		
		TitleButton first = new TitleButton(60, 200, 150, 100, Color.cyan, "1-player");
		//first.draw(g);
		this.onePl = first;
		buttons.add(first);
		
		TitleButton second = new TitleButton(250, 200, 150, 100, Color.red, "2-player");
		//second.draw(g);
		this.twoPl = second;
		buttons.add(second);
		
		TitleButton third= new TitleButton(60, 320, 150, 100, Color.green, "Endless");
		//third.draw(g);
		this.endl = third;
		buttons.add(third);
		
		TitleButton fourth = new TitleButton(250, 320, 150, 100, Color.magenta, "Play to win");
		//fourth.draw(g);
		this.plWin = fourth;
		buttons.add(fourth);
		
		return buttons;
	}
	
	

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
