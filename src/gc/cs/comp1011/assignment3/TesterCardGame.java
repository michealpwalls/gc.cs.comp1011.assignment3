package gc.cs.comp1011.assignment3;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * @author Micheal Walls
 * @author Robert Berry
 *
 */
public class TesterCardGame {

	/**
	 * @param args
	 * @throws IOException Throws when the card image could not be found
	 */
	public static void main(String[] args) throws IOException {
		// Instantiate an our CardGame object, which is our JFrame (Window)
		CardGame cardGame = new CardGame();
		
		// Set some properties of the JFrame (Window)
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		cardGame.setLocation( (int)screenDimension.getWidth()/4, (int)screenDimension.getHeight()/4 );		// Center it by making it
		cardGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardGame.setSize(600,750);	// Window size
		cardGame.setVisible(true);	// Verbose!
	}

}
