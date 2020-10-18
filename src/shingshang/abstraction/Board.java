package shingshang.abstraction;

/** Represents the board.
 * @author Tanguy Mossion
 */

public class Board {
	
	// CONSTANTS
	public final static int NB_LIN = 10;
	public final static int NB_COL = 10;
	
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	private Cell board[][];
	
	// METHODS
	
	/**
	 * Constructor initializing the board with empty cells
	 */
	public Board() {
		reset();
	}
	
	public void reset() {
		this.board = new Cell[NB_LIN][NB_COL];
		for (int lin = 0 ; lin < NB_COL ; lin++ ) {
			for (int col = 0 ; col < NB_LIN ; col++) {
				// We initialize all cells which are not portals or invalid cells
				if(((col != 4 || col != 5) && (lin != 1 || lin != 8))
						&& ((col != 0 && col != 9) || (lin ==  4 || lin == 5)))
					this.board[col][lin] = new Cell(false, true);
				// We initialize portals here
				if((col == 4 || col == 5) && (lin == 1 || lin == 8))
					this.board[col][lin] = new Cell(true, true);
				// We initialize invalid cells here
				if((col == 0 || col == 9) && (lin != 4 && lin != 5))
					this.board[col][lin] = new Cell(false, false);
			}
		}		
	}
	
	public void displayBoard() {
		for (int lin = 0 ; lin < NB_COL ; lin++ ) {
			for (int col = 0 ; col < NB_LIN ; col++) {
				displayCell(board[col][lin]);
				System.out.print(" ");
			}
			System.out.print("\n\n");
		}
	}

	public void displayCell(Cell cell) {
		if(cell.isPortal())
		{
			System.out.print("P");
		}
		else if(!cell.isValid())
		{
			System.out.print("x");
		}
		
		else if(cell.isEmpty())
		{
			System.out.print("□");
		}
		else // Error
		{
			System.out.print("?");
		}
	}
}