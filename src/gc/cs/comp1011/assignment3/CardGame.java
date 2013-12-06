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
 *
 */
public class CardGame extends JFrame {
	
	// Instance Variables
	private static final long serialVersionUID = -3755250864096119104L;
	private Card previousCard;

	/**
	 * This is the default, no-argument Constructor for CardGame objects
	 * 
	 * @throws IOException
	 */
	public CardGame() throws IOException {
		super();
		
		// Initially, set the previousCard to null
		previousCard = null;
		
		setLayout( new GridLayout(4,4) );
		
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
	 * 
	 * @throws IOException
	 */
	public class Card {
		// Instance variables
		private JButton cardButton;
		private int cardValue;
		private char cardSuit;
		private String cardTitle;
		
		public Card(int cardValueIn, char cardSuitIn) throws IOException {
			// Store the card's properties
			this.cardValue = cardValueIn;
			this.cardSuit = cardSuitIn;
			this.cardTitle = String.valueOf( cardValue ) +  String.valueOf( cardSuit );
			
			Icon cardIcon = new ImageIcon(ImageIO.read(new File("res/images/card_" + this.cardValue + this.cardSuit + ".png")));
			
			cardButton = new JButton(cardIcon);
			//fancyButton.setRolloverIcon(mpwIcon2);
			add(cardButton);
			
			CardClickHandler clickHandler = new CardClickHandler();
			cardButton.addActionListener(clickHandler);
		}// end Card Constructor
		
		public boolean Compare(Card cardToCompare) {
			//
			// TODO: Compare the two card objects
			//
			return false;
			
		}// end Compare method
		
		/**
		 * Inner class implementing a handler for OnClick events
		 */
		private class CardClickHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				//TODO Something very profound and awesome
				//
				if( previousCard == null ) {
					JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed the FIRST card!") );
					previousCard = Card.this;
				} else {
					JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed a SECOND card.\nCompare this card to " + previousCard.cardTitle));
				}
			}// end actionPerformed method
			
		}// end CardClickHandler class
		
	}// end Card Class
	
}// end CardGame Class
