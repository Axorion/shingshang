package abstraction;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import presentation.MainConsole;

/** Represents a Shing Shang game.
 * @author Tanguy Mossion
 */

public class Game {
	
	// CONSTANTS
	private final static int X = 0;
	private final static int Y = 1;
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	/**
	 * The board of the game
	 */
	static public Board board;
	
	/**
	 * The player 1 of the game
	 */
	static public Player player1;
	
	/**
	 * The player 2 of the game
	 */
	static public Player player2;
	
	/**
	 * The current player of the game
	 */
	static public Player currentPlayer;
	
	/**
	 * The winner of the game
	 */
	static public Player winner;
	
	/**
	 * The cell selected by the current player
	 */
	static public Cell cellSelected;
	
	/**
	 * Boolean indicating if you interacts by the console
	 */
	private boolean isConsoleDisplay;
	
	// METHODS
	/**
	 * Constructor initializing the Game with the board and the players
	 * @param  isConsoleDisplay	boolean indicating if you interacts by the console
	 * @param  aPlayer1			the player 1 of the game
	 * @param  aPlayer2			the player 2 of the game
	 */
	public Game(boolean isConsoleDisplay, Player aPlayer1, Player aPlayer2) {
		this.isConsoleDisplay = isConsoleDisplay;
		
		player1 = aPlayer1;
		player2 = aPlayer2;
		
		board = new Board();
	}
	
	/**
	 * Starts the game and plays rounds until there is a winner
	 */
	public void play() {
		currentPlayer = player1;

		System.out.println(player1.getPseudonym() + " plays at the top");
		System.out.println(player2.getPseudonym() + " plays at the bottom \n");
		
		MainConsole.displayBoard(board);

		while(winner == null)
		{
			winner = playRound();
		}
		
		displayWinner();
	}
	
	/**
	 * Plays a round of the game
	 * @return	the winner with a Player object
	 * @see    	Player
	 */
	public Player playRound() {
		Player winner = null;
		boolean isShingShang = false;
		boolean hasEaten = false;
		
		List<Bushi> bushisWhichHaveEatenDuringTheRound = new ArrayList<Bushi>();
		
		displayCurrentPlayer();
		
		Bushi bushiSelected = null;
		
		do {
			hasEaten = false;
			cellSelected = null;
			bushiSelected = null;
			
			while(cellSelected == null || cellSelected.getBushi() == null 
					|| cellSelected.getBushi().hasEaten() ||  !board.checkIfPlayerOwnsBushiSelected(currentPlayer))
			{
				int[] coordinatesOfCellToSelect = getCoordinatesOfBushiToSelect();
				cellSelected = board.getCell(coordinatesOfCellToSelect[X], coordinatesOfCellToSelect[Y]);
			}
			bushiSelected = cellSelected.getBushi();
			
			do {
				List<Cell> cellsWhereBushiSelectedCanMove = board.getCellsWhereBushiSelectedCanMove();
				List<Cell> cellsWhereBushiSelectedCanJump = board.getCellsWhereBushiSelectedCanJump();
				
				if(isShingShang)
				{
					ListIterator<Cell> itCell = cellsWhereBushiSelectedCanMove.listIterator();
					while(itCell.hasNext())
					{
						if(!cellsWhereBushiSelectedCanJump.contains(itCell.next()))
							itCell.remove();
					}
				}
				
				displayCellsWhereBushiSelectedCanMove(cellsWhereBushiSelectedCanMove);
				
				cellSelected = null;
				
				while(cellSelected == null || !cellsWhereBushiSelectedCanMove.contains(cellSelected))
				{
					int[] coordinatesOfCellToSelect = getCoordinatesWhereToMoveBushiSelected();
					cellSelected = board.getCell(coordinatesOfCellToSelect[X], coordinatesOfCellToSelect[Y]);
				}
				
				if(cellsWhereBushiSelectedCanJump.contains(cellSelected))
				{
					Bushi bushiWhichHaveBeenJumpedOver = board.getBushiWhichHaveBeenJumpedOverByOtherBushi(bushiSelected, cellSelected);
					
					if(bushiWhichHaveBeenJumpedOver.getOwner() != currentPlayer)
					{
						bushiWhichHaveBeenJumpedOver.getPosition().removeBushi();
						bushiSelected.setHasEaten(true);
						bushisWhichHaveEatenDuringTheRound.add(bushiSelected);
					}
					
					bushiSelected.moveToCell(cellSelected);
					MainConsole.displayBoard(board);
					
					winner = board.checkVictory();
					
					if(winner == null)
					{
						if(bushiWhichHaveBeenJumpedOver.getOwner() != currentPlayer)
						{
							hasEaten = alertHasEatenBushi();
						}
						else
						{
							isShingShang = alertShingShang();
						}
					}
				}
				else {
					bushiSelected.moveToCell(cellSelected);
					MainConsole.displayBoard(board);
				}
				
			} while(isShingShang);
			
		} while(hasEaten);
		
		if(winner == null)
		{
			if(currentPlayer == player1)
				changeCurrentPlayer(player2);
			else
				changeCurrentPlayer(player1);			
		}
		
		for (Bushi bushiWhichHaveEateanDuringTheRound : bushisWhichHaveEatenDuringTheRound)
		{
			bushiWhichHaveEateanDuringTheRound.setHasEaten(false);
		}
		
		return winner;
	}
	
	/**
	 * Changes the current player to the newCurrentPlayer (his opponent)
	 * @param 	newCurrentPlayer	the next player
	 */
	public void changeCurrentPlayer(Player newCurrentPlayer) {
		currentPlayer = newCurrentPlayer;
	}
	
	/**
	 * Displays the current player. The way to display it depend on the boolean isConsoleDisplay
	 */
	public void displayCurrentPlayer() {
		if(isConsoleDisplay)
		{
			MainConsole.displayCurrentPlayer();
		}
		else
		{
			// IHM TO COMPLETE
		}
	}
	
	/**
	 * Displays the winner. The way to display it depend on the boolean isConsoleDisplay
	 */
	public void displayWinner() {
		if(isConsoleDisplay)
		{
			MainConsole.displayWinner();
		}
		else
		{
			// IHM TO COMPLETE
		}
	}
	
	/**
	 * Displays the cells where bushi selected can move. The way to display it depend on the boolean isConsoleDisplay
	 * @param  cellsWhereBushiSelectedCanMove	the cells  to display
	 * @see    Cell
	 */
	public void displayCellsWhereBushiSelectedCanMove(List<Cell> cellsWhereBushiSelectedCanMove) {
		if(isConsoleDisplay)
		{
			MainConsole.displayCellsWhereBushiSelectedCanMove(cellsWhereBushiSelectedCanMove);
		}
		else
		{
			// IHM TO COMPLETE
		}
	}
	
	/**
	 * Alerts the player that he is in Shing Shang, also ask if he wants to continue to jump or not. The way to get it depend on the boolean isConsoleDisplay
	 * @return	the choice of the player to continue to jump or not
	 */
	public boolean alertShingShang() {
		if(isConsoleDisplay)
		{
			return MainConsole.alertShingShang();
		}
		else
		{
			// IHM TO COMPLETE
			return false;
		}
	}
	
	/**
	 * Alerts the player that he ate an opponent's bushi, also ask if he wants to continue to play the round or not. The way to get it depend on the boolean isConsoleDisplay
	 * @return	the choice of the player to continue to play the round or not
	 */
	public boolean alertHasEatenBushi() {
		if(isConsoleDisplay)
		{
			return MainConsole.alertHasEatenBushi();
		}
		else
		{
			// IHM TO COMPLETE
			return false;
		}
	}

	/**
	 * Returns the coordinates of the bushi to select. The way to get it depend on the boolean isConsoleDisplay
	 * @return	the coordinates of the bushi to select
	 */
	public int[] getCoordinatesOfBushiToSelect() {		
		if(isConsoleDisplay)
		{
			return MainConsole.askCoordinatesOfBushiToSelect();
		}
		else
		{
			// IHM TO COMPLETE
			return null;
		}
	}
	
	/**
	 * Returns the coordinates where to move bushi selected. The way to get it depend on the boolean isConsoleDisplay
	 * @return	the coordinates where to move bushi selected
	 */
	public int[] getCoordinatesWhereToMoveBushiSelected() {		
		if(isConsoleDisplay)
		{
			return MainConsole.askCoordinatesWhereToMoveBushiSelected();
		}
		else
		{
			// IHM TO COMPLETE
			return null;
		}
	}
}
