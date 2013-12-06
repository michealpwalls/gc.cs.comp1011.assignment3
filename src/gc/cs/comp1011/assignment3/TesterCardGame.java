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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		CardGame cardGame = new CardGame();
		cardGame.setLocationRelativeTo(null);
		cardGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardGame.setSize(800,600);
		cardGame.setVisible(true);

	}

}
