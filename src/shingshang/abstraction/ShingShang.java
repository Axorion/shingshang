package shingshang.abstraction;

import java.util.List;

/** Main used to show the development of the project
 * @author Tanguy Mossion
 */

public class ShingShang {

	public static void main(String[] args) {
		Board board = new Board();
		board.displayBoard();

		/*Bushi monBushi = board.getCell(3, 0).getBushi();
		System.out.println(monBushi.getBushiType());
		monBushi.moveToCell(board.getCell(3, 2));
		board.displayBoard();*/
		
		board.cellSelected = board.getCell(2, 1);
		List<Cell> cellsWhereBushiSelectedCanMove = board.getCellsWhereBushiSelectedCanMove();
		for(Cell cell : cellsWhereBushiSelectedCanMove)
		{
			int[] coordinatesOfCell = board.getCoordinatesOfCell(cell);
			System.out.println("MAIN : x : " + coordinatesOfCell[0] + " y : " + coordinatesOfCell[1]);
		}
	}
}
