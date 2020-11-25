package abstraction;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.lang.Math;

/** Represents the board.
 * @author Tanguy Mossion
 */

public class Board {
	
	// CONSTANTS
	private final static int NB_LIN = 10;
	private final static int NB_COL = 10;
	private final static int X = 0;
	private final static int Y = 1;
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	/**
	 * The board of the game, it's a two dimension tab of Cell
	 */
	public Cell board[][];
	
	// METHODS
	/**
	 * Constructor initializing the Board with the beginning placement of the bushis
	 */
	public Board() {
		reset();
	}

	/**
	 * Resets the board placing the bushis to their beginning placement
	 */
	public void reset() {
		resetBoard();
		resetBushis();
	}
	
	/**
	 * Part of the constructor initializing the board with empty cells 
	 */
	private void resetBoard() {
		board = new Cell[NB_COL][NB_LIN];
		for (int col = 0 ; col < NB_LIN ; col++ ) {
			for (int lin = 0 ; lin < NB_COL ; lin++) {
				// Initialize all cells which are not portals or invalid cells
				if(((col != 4 || col != 5) && (lin != 1 || lin != 8))
						&& ((col != 0 && col != 9) || (lin ==  4 || lin == 5)))
					board[col][lin] = new Cell(false, true);
				// Initialize portals
				if((col == 4 || col == 5) && (lin == 1 || lin == 8))
					board[col][lin] = new Cell(true, true);
				// Initialize invalid cells
				if((col == 0 || col == 9) && (lin != 4 && lin != 5))
					board[col][lin] = new Cell(false, false);
			}
		}		
	}
	
	/**
	 * Part of the constructor initializing the bushis on the board
	 */
	private void resetBushis() {
		new Bushi(BushiType.DRAGON, Game.player1, board[1][0]);
		new Bushi(BushiType.DRAGON, Game.player1, board[8][0]);
		new Bushi(BushiType.DRAGON, Game.player2, board[1][9]);
		new Bushi(BushiType.DRAGON, Game.player2, board[8][9]);
		
		new Bushi(BushiType.LION, Game.player1, board[2][0]);
		new Bushi(BushiType.LION, Game.player1, board[7][0]);
		new Bushi(BushiType.LION, Game.player1, board[1][1]);
		new Bushi(BushiType.LION, Game.player1, board[8][1]);
		new Bushi(BushiType.LION, Game.player2, board[2][9]);
		new Bushi(BushiType.LION, Game.player2, board[7][9]);
		new Bushi(BushiType.LION, Game.player2, board[1][8]);
		new Bushi(BushiType.LION, Game.player2, board[8][8]);
		
		new Bushi(BushiType.MONKEY, Game.player1, board[3][0]);
		new Bushi(BushiType.MONKEY, Game.player1, board[6][0]);
		new Bushi(BushiType.MONKEY, Game.player1, board[2][1]);
		new Bushi(BushiType.MONKEY, Game.player1, board[7][1]);
		new Bushi(BushiType.MONKEY, Game.player1, board[1][2]);
		new Bushi(BushiType.MONKEY, Game.player1, board[8][2]);
		new Bushi(BushiType.MONKEY, Game.player2, board[3][9]);
		new Bushi(BushiType.MONKEY, Game.player2, board[6][9]);
		new Bushi(BushiType.MONKEY, Game.player2, board[2][8]);
		new Bushi(BushiType.MONKEY, Game.player2, board[7][8]);
		new Bushi(BushiType.MONKEY, Game.player2, board[1][7]);
		new Bushi(BushiType.MONKEY, Game.player2, board[8][7]);
	}
	
	/**
	 * Returns a Cell object
	 * @param  col	the column of the cell
	 * @param  lin	the line of the cell
	 * @return		the cell at the position [col][lin]
	 * @see    Cell
	 */
	public Cell getCell(int col, int lin) {
		return board[col][lin];
	}
	
	/**
	 * Returns the coordinates in the board of a Cell object
	 * @param  	cell	the cell you want to get the coordinates
	 * @return	int[]	the coordinates of the cell
	 * @see    	Cell
	 */
	public int[] getCoordinatesOfCell(Cell cell) {
		int[] coordinates = new int[2];
		for (int col = 0 ; col < NB_LIN ; col++ ) {
			for (int lin = 0 ; lin < NB_COL ; lin++) {
				if(getCell(col,lin) == cell) {
					coordinates[X] = col;
					coordinates[Y] = lin;
				}
			}
		}
		return coordinates;
	}
	
	/**
	 * Returns a list of cells where the bushi selected can move
	 * @return	the cells where the bushi selected can move
	 * @see    	Cell
	 */
	public List<Cell> getCellsWhereBushiSelectedCanMove() {
		List<Cell> cellsWhereBushiSelectedCanMove = new ArrayList<Cell>();
		cellsWhereBushiSelectedCanMove.addAll(getCellsWhichBushiSelectedCanReach());
		this.removeCellsBlockedByOtherBushi(cellsWhereBushiSelectedCanMove);
		cellsWhereBushiSelectedCanMove.addAll(getCellsWhereBushiSelectedCanJump());
		this.removeCellsInvalidInList(cellsWhereBushiSelectedCanMove);
		this.removeCellsOccupiedInList(cellsWhereBushiSelectedCanMove);
		if(Game.cellSelected.getBushi().getBushiType() != BushiType.DRAGON)
			this.removeCellsPortalInList(cellsWhereBushiSelectedCanMove);
		return cellsWhereBushiSelectedCanMove;
	}
	
	/**
	 * Returns a list of cells which the bushi selected can reach
	 * @return	the cells which the bushi selected can reach
	 * @see		Cell
	 */
	private List<Cell> getCellsWhichBushiSelectedCanReach() {
		List<Cell> cellsWhichBushiSelectedCanReach = new ArrayList<Cell>();
		int[] coordinatesOfCellSelected = getCoordinatesOfCell(Game.cellSelected);
		Bushi bushiSelected = Game.cellSelected.getBushi();
		int movementPoint = bushiSelected.getMovementPoint();
		for(int i = coordinatesOfCellSelected[X] - movementPoint ; 
				i <= coordinatesOfCellSelected[X] + movementPoint ; i++)
		{

			for(int j = coordinatesOfCellSelected[Y] - movementPoint ; 
					j <= coordinatesOfCellSelected[Y] + movementPoint ; j++)
			{
				if(i >= 0  && i < NB_LIN && j >= 0 && j < NB_LIN) 
				{
					// Exclude the cellSelected
					if(!(coordinatesOfCellSelected[X] == i
							&& coordinatesOfCellSelected[Y] == j)) 
					{
						// Add cells which the Bushi can reach horizontally and vertically
						if(coordinatesOfCellSelected[X] == i
								|| coordinatesOfCellSelected[Y] == j)
						{
							cellsWhichBushiSelectedCanReach.add(getCell(i,j));
						}
						// Add cells which the Bushi can reach diagonally
						else if((Math.abs(coordinatesOfCellSelected[X] - i) == 1
								&& (Math.abs(coordinatesOfCellSelected[Y] - j) == 1 || (Math.abs(coordinatesOfCellSelected[Y] + j) == 1))
								|| (Math.abs(coordinatesOfCellSelected[X] + i) == 1
										&& (Math.abs(coordinatesOfCellSelected[Y] - j) == 1 || (Math.abs(coordinatesOfCellSelected[Y] + j) == 1)))))
						{
							cellsWhichBushiSelectedCanReach.add(getCell(i,j));
						}
						else if ((Math.abs(coordinatesOfCellSelected[X] - Math.abs(i)) == 2)
								&& (Math.abs(coordinatesOfCellSelected[Y] - Math.abs(j)) == 2)) 
						{
							cellsWhichBushiSelectedCanReach.add(getCell(i,j));
						}
					}
				}
			}
		}
		return cellsWhichBushiSelectedCanReach;
	}
	
	/**
	 * Returns a list of cells where the bushi selected can jump
	 * @return	the cells where the bushi selected can jump
	 * @see   	Cell
	 */
	public List<Cell> getCellsWhereBushiSelectedCanJump() {
		List<Cell> cellsWhereBushiSelectedCanJump = new ArrayList<Cell>();
		
		List<Cell> cellsCloseToBushiSelected = new ArrayList<Cell>();
		cellsCloseToBushiSelected.addAll(this.getCellsCloseToBushiSelected());
		
		ListIterator<Cell> itCell = cellsCloseToBushiSelected.listIterator();
		
		while(itCell.hasNext())
		{
			Cell currentCell = itCell.next();
			if(currentCell.getBushi() != null)
			{
				if(!this.checkIfBushiSelectedCanJumpOver(currentCell.getBushi()))
				{
					itCell.remove();
				}
				else
				{
					int[] coordinatesOfBushiSelectedAfterJumpingOverOtherBushi = getCoordinatesOfBushiSelectedAfterJumpingOver(currentCell.getBushi());
					Cell cellWhereBushiSelectedJump = this.getCell(coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[X], 
							coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[Y]);
					cellsWhereBushiSelectedCanJump.add(cellWhereBushiSelectedJump);
				}
			}
			else
			{
				itCell.remove();
			}
		}
		
		return cellsWhereBushiSelectedCanJump;
	}
	
	/**
	 * Returns a list of cells close to the bushi selected
	 * @return	the cells close to the bushi selected
	 * @see   	Cell
	 */
	private List<Cell> getCellsCloseToBushiSelected() {
		List<Cell> cellsCloseToBushiSelected = new ArrayList<Cell>();
		int[] coordinatesOfCellSelected = getCoordinatesOfCell(Game.cellSelected);
		 
		for(int i = coordinatesOfCellSelected[X] - 1 ; i <= coordinatesOfCellSelected[X] + 1 ; i++)
		{
			for(int j = coordinatesOfCellSelected[Y] - 1 ; j <= coordinatesOfCellSelected[Y] + 1 ; j++)
			{
				if(i >= 0  && i < NB_LIN && j >= 0 && j < NB_LIN) 
				{
					// Exclude the cellSelected
					if(!(coordinatesOfCellSelected[X] == i
							&& coordinatesOfCellSelected[Y] == j)) 
					{
						cellsCloseToBushiSelected.add(getCell(i,j));
					}
				}
			}
			
		}
		
		return cellsCloseToBushiSelected;
	}
	
	/**
	 * Returns a boolean indicating if the bushi selected can jump over the other bushi
	 * @param	otherBushi	the other bushi over which the bushi selected jumps
	 * @return	a boolean 	indicating if the bushi selected can jump over the other bushi
	 * @see    	Bushi
	 */
	private boolean checkIfBushiSelectedCanJumpOver(Bushi otherBushi) {
		boolean bushiSelectedCanMoveOverOtherBushi = false;
		boolean bushiIsBiggerOrEqualThanOtherBushi = false;
		boolean bushiSelectedWillLandOnTheBoard = false;
		
		if(Game.cellSelected.getBushi() != null)
		{
			Bushi bushiSelected = Game.cellSelected.getBushi();
			bushiIsBiggerOrEqualThanOtherBushi = bushiSelected.isBiggerOrEqualThan(otherBushi);
			
			int[] coordinatesOfBushiSelectedAfterJumpingOverOtherBushi = getCoordinatesOfBushiSelectedAfterJumpingOver(otherBushi);
			
			bushiSelectedWillLandOnTheBoard = coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[X] >= 0  
					&& coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[X] < NB_LIN 
					&& coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[Y] >= 0 
					&& coordinatesOfBushiSelectedAfterJumpingOverOtherBushi[Y] < NB_LIN;
		}
		
		bushiSelectedCanMoveOverOtherBushi = bushiIsBiggerOrEqualThanOtherBushi && bushiSelectedWillLandOnTheBoard;
		return bushiSelectedCanMoveOverOtherBushi;
	}
	
	/**
	 * Returns the coordinates in the board of the bushi selected after jumping overt the other bushi
	 * @param	otherBushi	the other bushi over which the bushi selected jumps
	 * @return	int[]		the coordinates of the cell
	 * @see    	Bushi
	 */
	private int[] getCoordinatesOfBushiSelectedAfterJumpingOver(Bushi otherBushi) {
		Bushi bushiSelected = Game.cellSelected.getBushi();
		
		int[] coordinatesOfBushiSelected = this.getCoordinatesOfCell(bushiSelected.getPosition());
		int[] coordinatesOfOtherBushi = this.getCoordinatesOfCell(otherBushi.getPosition());
		
		if(coordinatesOfBushiSelected[X] < coordinatesOfOtherBushi[X])
		{
			coordinatesOfBushiSelected[X] = coordinatesOfOtherBushi[X] + 1;
		}
		else if(coordinatesOfBushiSelected[X] > coordinatesOfOtherBushi[X])
		{
			coordinatesOfBushiSelected[X] = coordinatesOfOtherBushi[X] - 1;
		}
		if(coordinatesOfBushiSelected[Y] < coordinatesOfOtherBushi[Y])
		{
			coordinatesOfBushiSelected[Y] = coordinatesOfOtherBushi[Y] + 1;
		}
		else if(coordinatesOfBushiSelected[Y] > coordinatesOfOtherBushi[Y])
		{
			coordinatesOfBushiSelected[Y] = coordinatesOfOtherBushi[Y] - 1;
		}
		
		return coordinatesOfBushiSelected;
	}
	
	/**
	 * Removes the cells occupied from a list of cells
	 * @param 	cells	a list of cell
	 * @return	a list of cell where none is occupied
	 * @see    	Cell
	 */
	private List<Cell> removeCellsOccupiedInList(List<Cell> cells) {
		ListIterator<Cell> itCell = cells.listIterator();
		while(itCell.hasNext())
		{
			if(!itCell.next().isEmpty())
				itCell.remove();
		}
		return cells;
	}
	
	/**
	 * Removes the cells which are portals from a list of cells
	 * @param 	cells	a list of cell
	 * @return	a list of cell where none is a portal
	 * @see    	Cell
	 */
	private List<Cell> removeCellsPortalInList(List<Cell> cells) {
		ListIterator<Cell> itCell = cells.listIterator();
		while(itCell.hasNext())
		{
			if(itCell.next().isPortal())
				itCell.remove();
		}
		return cells;
	}
	
	/**
	 * Removes the invalid cells from a list of cells
	 * @param 	cells	a list of cell
	 * @return	a list of cell where none is invalid
	 * @see    	Cell
	 */
	private List<Cell> removeCellsInvalidInList(List<Cell> cells) {
		ListIterator<Cell> itCell = cells.listIterator();
		while(itCell.hasNext())
		{
			if(!itCell.next().isValid())
				itCell.remove();
		}
		return cells;
	}
	
	/**
	 * Returns the cell between the two cells passed in parameters
	 * @param 	Cell	the first cell
	 * @param 	Cell	the second cell
	 * @return	the cell between the two cells passed in parameters
	 * @see    	Cell
	 */
	private Cell getCellBetweenTwoCells(Cell firstCell, Cell secondCell) {
		int[] coordinatesOfCellBetweenCellSelectedAndOtherCell = new int[2];
		
		int[] coordinatesOfFirstCell = getCoordinatesOfCell(firstCell);
		int[] coordinatesOfSecondCell = getCoordinatesOfCell(secondCell);	
		
		if(coordinatesOfSecondCell[X] < coordinatesOfFirstCell[X] && coordinatesOfFirstCell[X] - 2 == coordinatesOfSecondCell[X])
		{
			coordinatesOfCellBetweenCellSelectedAndOtherCell[X] = coordinatesOfFirstCell[X]-1;
		}
		else if (coordinatesOfSecondCell[X] > coordinatesOfFirstCell[X] && coordinatesOfFirstCell[X] + 2 == coordinatesOfSecondCell[X])
		{
			coordinatesOfCellBetweenCellSelectedAndOtherCell[X] = coordinatesOfFirstCell[X]+1;
		}
		else
			coordinatesOfCellBetweenCellSelectedAndOtherCell[X] = coordinatesOfFirstCell[X];
		
		if(coordinatesOfSecondCell[Y] < coordinatesOfFirstCell[Y] && coordinatesOfFirstCell[Y] - 2 == coordinatesOfSecondCell[Y])
		{
			coordinatesOfCellBetweenCellSelectedAndOtherCell[Y] = coordinatesOfFirstCell[Y]-1;
		}
		else if (coordinatesOfSecondCell[Y] > coordinatesOfFirstCell[Y] && coordinatesOfFirstCell[Y] + 2 == coordinatesOfSecondCell[Y])
		{
			coordinatesOfCellBetweenCellSelectedAndOtherCell[Y] = coordinatesOfFirstCell[Y]+1;
		}
		else
			coordinatesOfCellBetweenCellSelectedAndOtherCell[Y] = coordinatesOfFirstCell[Y];
		
		if(!(coordinatesOfCellBetweenCellSelectedAndOtherCell[X] == coordinatesOfFirstCell[X] && 
				coordinatesOfCellBetweenCellSelectedAndOtherCell[Y] == coordinatesOfFirstCell[Y]))
		{
			return getCell(coordinatesOfCellBetweenCellSelectedAndOtherCell[X], coordinatesOfCellBetweenCellSelectedAndOtherCell[Y]);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Removes the  cells blocked by other bushi (there is a bushi between the bushi selected and the cell he can reach)
	 * @param 	cells	a list of cell
	 * @return	a list of cell blocked by other bushi
	 * @see    	Cell
	 */
	private List<Cell> removeCellsBlockedByOtherBushi(List<Cell> cells) {
		ListIterator<Cell> itCell = cells.listIterator();
		while(itCell.hasNext())
		{
			Cell currentCell = itCell.next();
			
			Cell cellBetweenCurrentCellAndCellSelected = getCellBetweenTwoCells(Game.cellSelected, currentCell);
			if(cellBetweenCurrentCellAndCellSelected != null)
			{
				if(cellBetweenCurrentCellAndCellSelected.getBushi() != null)
					itCell.remove();
			}
		}
		return cells;
	}
	
	/**
	 * Returns the bushi which have been jumped over by the other bushi
	 * @param	otherBushi						the other bushi which have jumped over the bushi returned by the function
	 * @param	cellWhereOtherBushiWillJump 	the cell where the other bushi will jump after jumping over
	 * @return	cellWhereOtherBushiWillJump 	the cell where the other bushi will jump after jumping over
	 * @see    	Bushi
	 * @see    	Cell
	 */
	public Bushi getBushiWhichHaveBeenJumpedOverByOtherBushi(Bushi otherBushi, Cell cellWhereOtherBushiWillJump) {
		Bushi bushiWhichHaveBeenJumpedOver = null;
		
		Cell cellOfOtherBushi = otherBushi.getPosition();
		
		Cell cellOfBushiWhichHaveBeenJumpedOver = getCellBetweenTwoCells(cellOfOtherBushi, cellWhereOtherBushiWillJump);
		
		if(cellOfBushiWhichHaveBeenJumpedOver != null)
			bushiWhichHaveBeenJumpedOver = cellOfBushiWhichHaveBeenJumpedOver.getBushi();
		
		return bushiWhichHaveBeenJumpedOver;
	}
	
	/**
	 * If there is one of the win condition, returns the player who wins
	 * @return	the winner with a Player object
	 * @see    	Player
	 */
	public Player checkVictory() {
		Player winner;
		
		winner = checkIfOneDragonIsOnPortal();
		if(winner == null)
			winner = checkIfOnePlayerHasNoDragon();
				
		return winner;
	}
	
	/**
	 * If one dragon is on a portal, returns his owner who wins
	 * @return	the winner with a Player object
	 * @see    	Player
	 */
	private Player checkIfOneDragonIsOnPortal() {
		Player winner = null;
		for (int col = 0 ; col < NB_LIN ; col++ ) {
			for (int lin = 0 ; lin < NB_COL ; lin++) {
				Cell currentCell = getCell(col, lin);
				if(currentCell.isPortal())
				{
					if(currentCell.getBushi() != null)
					{
						Bushi currentBushi = currentCell.getBushi();
						if(currentBushi.getBushiType() == BushiType.DRAGON)
						{
							winner = currentBushi.getOwner();
						}
					}
				}
			}
		}		
		return winner;
	}
	
	/**
	 * If a player has no dragon, returns his opponent who wins
	 * @return	the winner with a Player object
	 * @see    	Player
	 */
	private Player checkIfOnePlayerHasNoDragon() {
		Player winner = null;
		
		int nbDragonPlayer1 = 0;
		int nbDragonPlayer2 = 0;
		
		for (int col = 0 ; col < NB_LIN ; col++ ) {
			for (int lin = 0 ; lin < NB_COL ; lin++) {
				Cell currentCell = getCell(col, lin);
				if(currentCell.getBushi() != null)
				{
					Bushi currentBushi = currentCell.getBushi();
					if(currentBushi.getBushiType() == BushiType.DRAGON)
					{
						if(currentBushi.getOwner() == Game.player1)
						{
							nbDragonPlayer1++;
						}
						else if(currentBushi.getOwner() == Game.player2)
						{
							nbDragonPlayer2++;
						}
					}
				}
			}
		}	
		
		if(nbDragonPlayer1 == 0)
			winner = Game.player2;
		
		if(nbDragonPlayer2 == 0)
			winner = Game.player1;
		
		return winner;
	}
	
	/**
	 * Returns a boolean indicating if the bushi selected is owned by the player in parameter
	 * @param	player		the player which we want to know if he is the owner of bushi selected
	 * @return	a boolean 	indicating if the bushi selected is owned by player
	 * @see    	Player
	 */
	public boolean checkIfPlayerOwnsBushiSelected(Player player) {
		if(Game.cellSelected.getBushi() != null)
		{
			return(player == Game.cellSelected.getBushi().getOwner());
		}
		else
		{
			return false;
		}
	}
	
}