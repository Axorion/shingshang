package application;

import java.util.ArrayList;
import java.util.List;

import abstraction.Game;
import abstraction.Player;
import presentation.MainConsole;

/** Main used to show the development of the project
 * @author Tanguy Mossion
 */

public class ShingShang {
	public static void main(String[] args) {
		
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		MainConsole.askPseudonyms(players);
		
		Game game = new Game(true, player1, player2);
		game.play();
		
	}
}
