package gc.cs.comp1011.assignment3;

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
		cardGame.setLocationRelativeTo(null);		// Center it by making it unrelated to any other window
		cardGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardGame.setSize(800,600);	// Window size
		cardGame.setVisible(true);	// Verbose!

	}

}
