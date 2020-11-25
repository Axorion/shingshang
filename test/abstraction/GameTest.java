package abstraction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GameTest {
	Game game;
	@BeforeEach
	void setUp() throws Exception {
		this.game = new Game(true, new Player("Player1"), new Player("Player2"));
	}

	@Test
	public void cells_where_bushi_can_move() {
		List<Cell> cellsWhereBushiCanMove = new ArrayList<Cell>();
		
		Bushi bushi = Game.board.getCell(2, 1).getBushi(); 
		// This bushi can theorically move to the cells at coordinates :														   
			// (0,-1) (2,-1) (4,-1) 	(All are outside the board)
			// (0,1) (0,3) 				(All are invalid cells)
			// (1,0) (1,1) (1,2) 		(All are already occupied)
			// (2,0) (2,2) (2,3) 		((2,0) is already occupied))
			// (3,0) (3,1) (3,2) 		((3,0) is already occupied))
			// (4,3)
		// So in reality, he can move to the cells at coordinates :
			// (2,2) (2,3)
			// (3,1) (3,2)
			// (4,3)
		Game.cellSelected = bushi.getPosition();
		
		List<Cell> cellsWhereBushiShouldBeAbleToMove = new ArrayList<Cell>();
		Cell c2l2 = Game.board.getCell(2, 2);
		Cell c2l3 = Game.board.getCell(2, 3);
		Cell c3l1 = Game.board.getCell(3, 1);
		Cell c3l2 = Game.board.getCell(3, 2);
		Cell c4l3 = Game.board.getCell(4, 3);

		cellsWhereBushiShouldBeAbleToMove.add(c2l2);
		cellsWhereBushiShouldBeAbleToMove.add(c2l3);
		cellsWhereBushiShouldBeAbleToMove.add(c3l1);
		cellsWhereBushiShouldBeAbleToMove.add(c3l2);
		cellsWhereBushiShouldBeAbleToMove.add(c4l3);
		
		cellsWhereBushiCanMove.addAll(Game.board.getCellsWhereBushiSelectedCanMove());
		
		assertEquals(cellsWhereBushiCanMove, cellsWhereBushiShouldBeAbleToMove);
	}
	
	@Test
	public void check_if_player_owns_bushi_selected() {
		Cell cellWhereAPlayer1BushiIs = Game.board.getCell(1, 0);
		Bushi bushiOwnedByPlayer1 = cellWhereAPlayer1BushiIs.getBushi();
		Cell cellWhereAPlayer2BushiIs = Game.board.getCell(1, 8);
		Bushi bushiOwnedByPlayer2 = cellWhereAPlayer2BushiIs.getBushi();
		
		assertEquals(bushiOwnedByPlayer1.getOwner(), Game.player1);
		assertEquals(bushiOwnedByPlayer2.getOwner(), Game.player2);
	}
	
	@Test
	public void player1_wins_because_player2_has_no_dragon() {
		Cell cellWhereDragon1OfPlayer2Is = Game.board.getCell(1, 9);
		Cell cellWhereDragon2OfPlayer2Is = Game.board.getCell(8, 9);
		cellWhereDragon1OfPlayer2Is.removeBushi();
		cellWhereDragon2OfPlayer2Is.removeBushi();
		
		Player winner = Game.board.checkVictory();
		
		assertEquals(Game.player1, winner);
	}

	@Test
	public void player1_wins_because_has_dragon_on_opponent_portal() {
		Cell cellWhereADragonOfPlayer1Is = Game.board.getCell(1, 0);
		Bushi aDragonOfPlayer1 = cellWhereADragonOfPlayer1Is.getBushi();
		
		Cell aPortalOfPlayer2 = Game.board.getCell(4, 8);
		aDragonOfPlayer1.moveToCell(aPortalOfPlayer2);
		
		Player winner = Game.board.checkVictory();
		
		assertEquals(Game.player1, winner);
	}


}
