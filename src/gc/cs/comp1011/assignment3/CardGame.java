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

/**
 * @author Micheal Walls
 * @author Robert Berry
 */
public class CardGame extends JFrame {
	
	// Instance Variables
	private static final long serialVersionUID = -3755250864096119104L;	// Required by JFrame, allows serializing of the Object
	private Card previousCard;	// Reserve area in memory for a Card object

	/**
	 * This is the default, no-argument Constructor for CardGame objects
	 * 
	 * @throws IOException Throws when the card image could not be found
	 */
	public CardGame() throws IOException {
		super();
		
		// Initially, set the previousCard to null
		previousCard = null;
		
		// This instantiates an instance of our JFrame with a 4 x 4 Grid Layout
		setLayout( new GridLayout(4,4) );
		
		// We instantiate our cards but the compiler warns we never use them. Just suppress it :P
		@SuppressWarnings("unused")
		Card testCard1 = new Card(13,'h');
		@SuppressWarnings("unused")
		Card testCard2 = new Card(12,'c');
		@SuppressWarnings("unused")
		Card testCard3 = new Card(10,'d');
		@SuppressWarnings("unused")
		Card testCard4 = new Card(11,'s');
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
		private int cardValue;
		private char cardSuit;
		private String cardTitle;
		
		/**
		 * Constructor for instances of Card objects
		 * 
		 * @param cardValueIn Card Value as a number (1 - 13:ace - king)
		 * @param cardSuitIn Card Suit as a single character (h:hearts)
		 * @throws IOException Throws when the card image could not be found
		 */
		public Card(int cardValueIn, char cardSuitIn) throws IOException {
			// Store the card's properties
			this.cardValue = cardValueIn;
			this.cardSuit = cardSuitIn;
			this.cardTitle = String.valueOf( cardValue ) +  String.valueOf( cardSuit );
			
			Icon cardIcon = new ImageIcon(ImageIO.read(new File("res/images/card_" + this.cardValue + this.cardSuit + ".png")));
			
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
					JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed a SECOND card.\nCompare this card to " + previousCard.cardTitle));
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
