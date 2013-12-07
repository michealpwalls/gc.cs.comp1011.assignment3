package gc.cs.comp1011.assignment3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Micheal Walls
 * @author Robert Berry
 */
public class CardGame extends JFrame {
	// Instance Variables
	private static final long serialVersionUID = -3755250864096119104L;	// Required by JFrame, allows serializing of the Object
	private Card previousCard;	// Reserve area in memory for a Card object
	private ArrayList<Integer> arrayList_selectedCards = new ArrayList<Integer>();
	private final String[] array_cardIdentifiers = {
			"card_1c.png", "card_1d.png", "card_1h.png", "card_1s.png", 
		   "card_2c.png", "card_2d.png", "card_2h.png", "card_2s.png", "card_3c.png", 
		   "card_3d.png", "card_3h.png", "card_3s.png", "card_4c.png", "card_4d.png", 
		   "card_4h.png", "card_4s.png", "card_5c.png", "card_5d.png", "card_5h.png", 
		   "card_5s.png", "card_6c.png", "card_6d.png", "card_6h.png", "card_6s.png", 
		   "card_7c.png", "card_7d.png", "card_7h.png", "card_7s.png", "card_8c.png", 
		   "card_8d.png", "card_8h.png", "card_8s.png", "card_9c.png", "card_9d.png", 
		   "card_9h.png", "card_9s.png", "card_10c.png", "card_10d.png", "card_10h.png", 
		   "card_10s.png", "card_11c.png", "card_11d.png", "card_11h.png", "card_11s.png", 
		   "card_12c.png", "card_12d.png", "card_12h.png", "card_12s.png", "card_13c.png", 
		   "card_13d.png", "card_13h.png", "card_13s.png"
	};
	private int pairsMatched = 0;
	private int playerScore = 0;
	private Timer gameTimer = new Timer();
	private int gameTime = 60;
	private JTextField textField_message;
	private JTextField textField_score;
	private JTextField textField_timer;
	private JPanel cardGroupBox;
	private JPanel textFieldGroupBox;

	/**
	 * This is the default, no-argument Constructor for CardGame objects
	 * 
	 * @throws IOException Throws when the card image could not be found
	 */
	public CardGame() throws IOException {
		// Call the super/parent/base Class' constructor
		super();
		
		// Initially, set the previousCard to null
		previousCard = null;

		// This instantiates an instance of our JFrame with a 4 x 4 Grid Layout
		setLayout( new GridLayout(2,1) );

		// Instantiate the textField JPanel
		textFieldGroupBox = new JPanel();
		
		// Add the Message textField to the textField Group Box
		textField_message = new JTextField( "Pick a Card, any card!" );
		textFieldGroupBox.add( textField_message );
		
		// Add the Score textField to the textField Group Box
		textField_score = new JTextField( "Your score is: " + String.valueOf(playerScore) );
		textFieldGroupBox.add( textField_score );
		
		// Add the Timer textField to the textField Group Box
		textField_timer = new JTextField( "Time remaining: " + String.valueOf(gameTime) );
		textFieldGroupBox.add( textField_timer );

		// Add the textField Group Box to the JFrame
		add(textFieldGroupBox);
		
		// Create and add the Game buttons to the JFrame
		drawGameBoard();
		
		/*
		 * Game Timer
		 */
		gameTimer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	        	if( gameTime < 2 ) {
	    			// Time is up!
	        		int userResponse = JOptionPane.showConfirmDialog( CardGame.this, String.format("Your time is up! Your score was: %d\n\nWould you like to play again?", playerScore), String.format("Congratulations!"), JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
					
					if( userResponse == 0 ) {
						// Reset the board to play again
						resetGame();
					} else {
						// Exit with 0 error code
						System.exit(0);
					}
	    		} else {
	    			// Decrement the game time and continue
	    			gameTime--;
	    			textField_timer.setText( "Time remaining: " + String.valueOf(gameTime) );
	    		}// end if
	        }// end run method

	    }, 1000, 1000);	// end gameTimer schedule definition
	}// end CardGame Constructor
	
	private void updateScore() {
		// Update the game score
		this.textField_score.setText( "Your score is " + this.playerScore );
	}// end setScore method
	
	/**
	 * This method initializes the game board
	 */
	private void drawGameBoard() throws IOException {
		int nextRandom = 0;

		boolean matchNotFound = true;

		while( arrayList_selectedCards.size() != 8 ) {
			// Generate a new random integer
			Random randomCard = new Random();
			nextRandom = randomCard.nextInt(51);

			// Make sure this card hasn't already been chosen
			for( int y = 0; y < arrayList_selectedCards.size(); y++ ) {
				if ( arrayList_selectedCards.get(y) == nextRandom ) {
					matchNotFound = false;
				}// end if
			}// end inner for loop

			if( matchNotFound )
				arrayList_selectedCards.add(nextRandom);
		}// end while loop
		
		// Duplicate the collection of 8 generated cards
		for( int i = 0; i < 8; i++ ) {
			arrayList_selectedCards.add( arrayList_selectedCards.get(i) );
		}// end for loop
		
		// Sort the Cards
		Collections.shuffle(arrayList_selectedCards);
		
		// Create a JPanel for our cards
		cardGroupBox = new JPanel();
		cardGroupBox.setLayout( new GridLayout(4,4) );
		//cardGroupBox.setSize(600, 800);
		
		// Instantiate all 16 of the cards
		for( int i = 0; i < 16; i++ ) {
			// When we instantiate these Card objects, it warns that magicalCardContainer is never used
			// I just suppress it because I am Awesome.
			@SuppressWarnings("unused")
			Card magicalCardContainer = new Card( array_cardIdentifiers[ arrayList_selectedCards.get(i) ] );
		}// end for loop

		add(cardGroupBox);
	}// end drawGameBoard method
	
	/**
	 * Method to reset the Game
	 */
	public void resetGame() {
		dispose();

		// Start a new game! :)
		try {
			CardGame newGame = new CardGame();
			
			// Set some properties of the JFrame (Window)
			newGame.setLocationRelativeTo(null);		// Center it by making it unrelated to any other window
			newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newGame.setSize(800,600);	// Window size
			newGame.setVisible(true);	// Verbose!
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end method resetGame
	
	/**
	 * This class defines a generic Card object
	 * Instances of this Object will be "Playing Cards"
	 * that players can click on to flip over and must find
	 * a matching Card instance.
	 * 
	 * @throws IOException Throws when the card image could not be found
	 */
	public class Card {
		// Instance variables
		private JButton cardButton;
		private String cardIdentifier;
		
		/**
		 * Constructor for instances of Card objects
		 * 
		 * @param cardIdentifier String to control what the card will be
		 * @throws IOException Throws when the card image could not be found
		 */
		public Card(String cardIdentifier) throws IOException {
			// Store the card's properties
			this.cardIdentifier = cardIdentifier;
			
			// Instantiate a new button for the Card
			cardButton = new JButton( new ImageIcon( ImageIO.read(new File("res/images/cardback.png")) ) );
			
			// Add the new Card to the window
			cardGroupBox.add(cardButton);
			//add(cardButton);
			
			// Register the click handler
			CardClickHandler clickHandler = new CardClickHandler();
			cardButton.addActionListener(clickHandler);
		}// end Card Constructor
		
		/**
		 * Method to compare one Card instance to another
		 * 
		 * @param cardToCompare	An instance of a Card object to compare to
		 * @return boolean True if cards math and False if they are not a match
		 */
		public boolean Compare(Card cardToCompare) {
			if( cardIdentifier == cardToCompare.cardIdentifier ) {
				return true;
			} else {
				return false;
			}
		}// end Compare method
		
		/**
		 * Inner class implementing a handler for the Click events
		 * First determine if this is the first card clicked on,
		 * if it's the first, simply flip it and record it.
		 * If it is the second, compare it to the first.
		 */
		private class CardClickHandler implements ActionListener {

			/**
			 * This overrides the abstract actionPerformed method declared in ActionListener interface
			 * 
			 * It Suppresses deprecation warnings so I can use hide() in peace :)
			 */
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// Flip the Card
				try {
					Card.this.cardButton.setIcon( new ImageIcon( ImageIO.read(new File("res/images/" + Card.this.cardIdentifier)) ));
				} catch (IOException e1) {
					System.out.println("Could not find the new ImageIcon!");
				}

				if( previousCard == null ) {
					// Store the selected card
					previousCard = Card.this;
					
					// Update the message
					CardGame.this.textField_message.setText( "Now, pick another card!" );
				} else {					
					if( Card.this == previousCard ) {
						// The user clicked the same card! Sneaky sneaky! :)
					} else {
						// Compare the cards
						if( Card.this.Compare( previousCard ) ) {
							Card.this.cardButton.hide();
							previousCard.cardButton.hide();

							// Clear the Previous Card
							previousCard = null;
							
							// Increment our match counter
							pairsMatched++;
							
							// Increase player's score
							CardGame.this.playerScore = CardGame.this.playerScore + 2;
							
							// Update score on the gameBoard
							CardGame.this.updateScore();
							
							// Update the message
							CardGame.this.textField_message.setText( "Pick a card, any card!" );
							
							// Find a winner!
							if( pairsMatched == 8 ) {
								// Alert the user and ask them if they'd like to play again
								int userResponse = JOptionPane.showConfirmDialog( CardGame.this, String.format("You won! Would you like to play again?"), String.format("Congratulations!"), JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
								
								if( userResponse == 0 ) {
									// Reset the board to play again
									resetGame();
								} else {
									// Exit with 0 error code
									System.exit(0);
								}
							}
						} else {
							// The cards did not match, so flip them back over
							resetPair(Card.this, previousCard);
							
							// Clear the Previous Card
							previousCard = null;
							
							// Update the message
							CardGame.this.textField_message.setText( "Pick a card, any card!" );
						}// end if
					}// end if
				}// end if
			}// end actionPerformed method
			
			public void resetPair(final Card cardOne, final Card cardTwo) {
				// Create and start a new thread
				new Thread() {
			        @Override
			        public void run() {
			        	// First sleep for 1 second
			            try {
			                sleep(1000);
			            } catch (InterruptedException e) {
			            	e.printStackTrace();
			            }
			            
						// Show the 2nd card's contents
						try {
							cardOne.cardButton.setIcon( new ImageIcon( ImageIO.read(new File("res/images/" + Card.this.cardIdentifier)) ));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						// Flip the unmatched pair of cards back
						try {
							cardOne.cardButton.setIcon( new ImageIcon( ImageIO.read(new File("res/images/cardback.png")) ));
							cardTwo.cardButton.setIcon( new ImageIcon( ImageIO.read(new File("res/images/cardback.png")) ));
						} catch (IOException e2) {
							e2.printStackTrace();
						}
			        }
			    }.start();
			}// end resetPair method
			
		}// end CardClickHandler class
		
	}// end Card Class
	
}// end CardGame Class
