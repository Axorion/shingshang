package shingshang.abstraction;

/** Main used to show the development of the project
 * @author Tanguy Mossion
 */

public class ShingShang {

	public static void main(String[] args) {
		Board board = new Board();
		board.displayBoard();
		
		Bushi monBushi = board.getCell(3, 0).getBushi();
		System.out.println(monBushi.getBushiType());
		monBushi.moveToCell(board.getCell(3, 2));
		board.displayBoard();
	}
}
