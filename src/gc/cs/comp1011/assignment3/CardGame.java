package gc.cs.comp1011.assignment3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Random;

/**
 * @author Micheal Walls
 * @author Robert Berry
 */
public class CardGame extends JFrame {
	
	// Instance Variables
	private static final long serialVersionUID = -3755250864096119104L;	// Required by JFrame, allows serializing of the Object
	private Card previousCard;	// Reserve area in memory for a Card object
	private int[] array_selectedCards = new int[8];
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
		setLayout( new GridLayout(4,4) );
		
		// Instantiate an instance of the Random object
		Random randomCard = new Random();
		int nextRandom = 0;
		
		for( int i = 0; i < 8; i++ ) {
			// A flag for the while loop
			boolean uniqueCardFound = true;

			while( uniqueCardFound ) {
				// Generate a random integer
				nextRandom = randomCard.nextInt(51);

				// Make sure this card hasn't already been chosen
				for( int y = 0; y < array_selectedCards.length; y++ ) {

					if ( array_selectedCards[y] != nextRandom ) {
						uniqueCardFound = false;
					}// end if

				}// end inner for loop

			}// end while loop

			// The compiler warns we never use them. Just suppress it :P
			@SuppressWarnings("unused")
			Card testCard1 = new Card( array_cardIdentifiers[nextRandom] );
		}// end outer for loop

	}// end CardGame Constructor
	
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
		 * @param cardValueIn Card Value as a number (1 - 13:ace - king)
		 * @param cardSuitIn Card Suit as a single character (h:hearts)
		 * @throws IOException Throws when the card image could not be found
		 */
		public Card(String cardIdentifier) throws IOException {
			// Store the card's properties
			this.cardIdentifier = cardIdentifier;
			
			Icon cardIcon = new ImageIcon( ImageIO.read(new File("res/images/" + this.cardIdentifier)) );
			
			cardButton = new JButton(cardIcon);
			add(cardButton);
			
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
			//
			// TODO: Compare the two card objects together
			//
			return false;
			
		}// end Compare method
		
		/**
		 * Inner class implementing a handler for the Click events
		 * First determine if this is the first card clicked on,
		 * if it's the first, simply flip it and record it.
		 * If it is the second, compare it to the first.
		 */
		private class CardClickHandler implements ActionListener {

			/**
			 * This implements the abstract actionPerformed method declared in ActionListener interface
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if( previousCard == null ) {
					//
					// DEBUG: Show that the First card was clicked. Remove later on...
					//
					JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed the FIRST card!") );
					previousCard = Card.this;
					//
					// TODO: Flip the card to reveal what it is.
					//
				} else {
					//
					// DEBUG: Show that the Second card was clicked. Remove later on...
					//
					JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed a SECOND card.\nCompare this card to " + previousCard.cardIdentifier));
					//
					// TODO: Flip the card.
					//
					// TODO: Compare this card to the previousCard.
					//
					// TODO: If cards match, remove them both and clear previousCard.
					//
					// TODO: IF cards do not match, flip them both back and clear previousCard.
					//
				}
			}// end actionPerformed method
			
		}// end CardClickHandler class
		
	}// end Card Class
	
}// end CardGame Class
