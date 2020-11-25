package presentation;

import java.util.List;

import abstraction.Board;
import abstraction.BushiType;
import abstraction.Cell;
import abstraction.Game;
import abstraction.Player;

/** Class gathering all the stuff linked to the console (output and input)
 * @author Tanguy Mossion
 */

public class MainConsole {

	// CONSTANTS
	private final static int NB_LIN = 10;
	private final static int NB_COL = 10;
	private final static int X = 0;
	private final static int Y = 1;

	// METHODS
	/**
	 * Displays the board in the console
	 * @param  board	the board to display on console
	 * @see    Board
	 */
	static public void displayBoard(Board board) {
		for (int lin = 0 ; lin < NB_LIN ; lin++ ) {
			for (int col = 0 ; col < NB_COL ; col++) {
				displayCell(board.board[col][lin]);
				System.out.print("  ");
			}
			System.out.print("\n\n");
		}
	}
	
	/**
	 * Displays a cell in the console. There are different representations depending on the cell state.
	 * @param  cell	the cell to display on console
	 * @see    Cell
	 */
	static private void displayCell(Cell cell) {
		String cellString = "";
		if(cell.getBushi() != null)
		{
			if(cell.getBushi().getOwner()==Game.player1)
				cellString += "P1";
			else if(cell.getBushi().getOwner()==Game.player2)
				cellString += "P2";
			else
				cellString += "P?";
		}
		if(cell.isPortal() && cell.getBushi() == null)
		{
			cellString += ("|P|");
		}
		else if(!cell.isValid())
		{
			cellString += (" ⬛ ");
		}
		else if(cell.isEmpty())
		{
			cellString += (" □ ");
		}
		else if(cell.getBushi().getBushiType() == BushiType.DRAGON)
		{
			cellString += ("D");
		}
		else if(cell.getBushi().getBushiType() == BushiType.LION)
		{
			cellString += ("L");
		}
		else if(cell.getBushi().getBushiType() == BushiType.MONKEY)
		{
			cellString += ("M");
		}
		else // Unrecognized
		{
			cellString += (" ? ");
		}
		System.out.print(cellString);
	}
	
	/**
	 * Displays the current player of the game in the console
	 */
	static public void displayCurrentPlayer() {
		System.out.println(Game.currentPlayer.getPseudonym() + " it is your turn!");
	}
	
	/**
	 * Displays the current winner of the game in the console
	 */
	static public void displayWinner() {
		System.out.println("The player " + Game.winner.getPseudonym() + " wins! Congratulations!");
	}
	
	/**
	 * Displays the cells where bushi selected can move in the console
	 * @param  cellsWhereBushiSelectedCanMove	the cells  to display
	 * @see    Cell
	 */
	static public void displayCellsWhereBushiSelectedCanMove(List<Cell> cellsWhereBushiSelectedCanMove) {
		System.out.println("Cells were bushi selected can move : ");
		for(Cell cell : cellsWhereBushiSelectedCanMove)
		{
			int[] coordinatesOfCell = Game.board.getCoordinatesOfCell(cell);
			System.out.println("(" + coordinatesOfCell[X] + "," + coordinatesOfCell[Y] + ")");
		}
	}
	
	/**
	 * Alerts the player that he is in Shing Shang, also ask if he wants to continue to jump or not in the console.
	 * @return	the choice of the player to continue to jump or not
	 */
	static public boolean alertShingShang() {		
		boolean continueShingShang = false;
		char response = 'X';
		while(response != 'Y' && response != 'y' && response != 'N'  && response != 'n')
		{
			System.out.println("Shing Shang! \n"
					+ "Do you want to jump again?! (Y/N)");
			response = Clavier.lireChar();
			if(response == 'Y'  || response == 'y' || response == 'N'|| response == 'n')
			{
				if(response == 'Y' || response == 'y')
					continueShingShang = true;
				else if(response == 'N' || response == 'n')
					continueShingShang = false;				
			}
		}
		return continueShingShang;
	}
	
	/**
	 * Alerts the player that he is ate an opponent's bushi, also ask if he wants to continue to play the round or not in the console.
	 * @return	the choice of the player to continue to jump or not
	 */
	static public boolean alertHasEatenBushi() {		
		boolean continueShingShang = false;
		char response = 'X';
		while(response != 'Y' && response != 'y' && response != 'N'  && response != 'n')
		{
			System.out.println("You have eaten a bushi! \n"
					+ "Do you want to continue to play the round (with another bushi)?! (Y/N)");
			response = Clavier.lireChar();
			if(response == 'Y'  || response == 'y' || response == 'N'|| response == 'n')
			{
				if(response == 'Y' || response == 'y')
					continueShingShang = true;
				else if(response == 'N' || response == 'n')
					continueShingShang = false;				
			}
		}
		return continueShingShang;
	}
	
	/**
	 * Asks to the 2 players their pseudonyms
	 * @param  players	the players of the game
	 * @see    Player
	 */
	static public void askPseudonyms(List<Player> players) {		
		int currentNbPlayer = 1;
		for(Player player : players)
		{
			System.out.println("Player" + currentNbPlayer + ", enter your pseudonym : ");
			String playerPseudonym = Clavier.lireString();
			if(!playerPseudonym.isBlank())
				player.setPseudonym(playerPseudonym);
			currentNbPlayer++;
		}
	}
	
	/**
	 * Asks the coordinates of the bushi to select
	 * @return	the coordinates of the bushi to select
	 */
	static public int[] askCoordinatesOfBushiToSelect() {
		int[] coordinatesOfCellToSelect = new int[2];
		int x = -1;
		int y = -1;
		coordinatesOfCellToSelect[X] = x;
		coordinatesOfCellToSelect[Y] = y;
		System.out.println("\nPlease select one of your bushi (that hasn't eaten this round)");
		
		while(coordinatesOfCellToSelect[X] == -1)
		{
			System.out.println("Enter the column (>=0 && =<9) : ");
			x = Clavier.lireInt();
			if(x >= 0 && x <= 9)
				coordinatesOfCellToSelect[X] = x;
		}
		
		while(coordinatesOfCellToSelect[Y] == -1)
		{
			System.out.println("Enter the line (>=0 && =<9) : ");
			y = Clavier.lireInt();
			if(y >= 0 && y <= 9)
				coordinatesOfCellToSelect[Y] = y;
		}
		
		return coordinatesOfCellToSelect;
	}
	
	/**
	 * Asks the coordinates where the player wants to move the bushi selected
	 * @return	the coordinates where to move bushi selected
	 */
	static public int[] askCoordinatesWhereToMoveBushiSelected() {
		int[] coordinatesOfCellToSelect = new int[2];
		int x = -1;
		int y = -1;
		coordinatesOfCellToSelect[X] = x;
		coordinatesOfCellToSelect[Y] = y;
		System.out.println("\nPlease select the cell where to move your bushi");
		
		while(coordinatesOfCellToSelect[X] == -1)
		{
			System.out.println("Enter the column (>=0 && =<9) : ");
			x = Clavier.lireInt();
			if(x >= 0 && x <= 9)
				coordinatesOfCellToSelect[X] = x;
		}
		
		while(coordinatesOfCellToSelect[Y] == -1)
		{
			System.out.println("Enter the line (>=0 && =<9) : ");
			y = Clavier.lireInt();
			if(y >= 0 && y <= 9)
				coordinatesOfCellToSelect[Y] = y;
		}
		
		return coordinatesOfCellToSelect;
	}
}