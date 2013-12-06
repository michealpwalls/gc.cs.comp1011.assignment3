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

	/**
	 * This is the default, no-argument Constructor for CardGame objects
	 * 
	 * @throws IOException
	 */
	public CardGame() throws IOException {
		super();
		setLayout( new GridLayout(4,4) );
		
		Card testCard = new Card("13","h");
	}// end CardGame Constructor
	
	/**
	 * This class defines a generic Card object
	 * 
	 * @throws IOException
	 */
	public class Card {
		// Instance variables
		private JButton cardButton;
		
		public Card(String cardValue, String cardSuit) throws IOException {
			
			Icon cardIcon = new ImageIcon(ImageIO.read(new File("res/images/card_" + cardValue + cardSuit + ".png")));
			
			cardButton = new JButton(cardValue, cardIcon);
			//fancyButton.setRolloverIcon(mpwIcon2);
			add(cardButton);
			
			CardClickHandler clickHandler = new CardClickHandler();
			cardButton.addActionListener(clickHandler);
		}// end Card Constructor
		
		/**
		 * Inner class implementing a handler for OnClick events
		 */
		private class CardClickHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog( CardGame.this, String.format("You pressed %s", arg0.getActionCommand()) );
			}// end actionPerformed method
			
		}// end CardClickHandler class
		
	}// end Card Class
	
}// end CardGame Class
