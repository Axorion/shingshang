package abstraction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BushiTest {
	Game game;
	@BeforeEach
	void setUp() throws Exception {
		this.game = new Game(true, new Player("Player1"), new Player("Player2"));
	}

	@Test
	public void monkey_can_jump_over_monkey() {
		Bushi monkey1 = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][4]);
		Bushi monkey2 = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][5]);
		
		boolean monkeyCanJumpOverMonkey = monkey1.isBiggerOrEqualThan(monkey2);
		
		assertEquals(monkeyCanJumpOverMonkey, true);
	}
	
	@Test
	public void lion_can_jump_over_monkey() {
		Bushi lion = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][4]);
		Bushi monkey = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][5]);
		
		boolean lionCanJumpOverMonkey = lion.isBiggerOrEqualThan(monkey);
		
		assertEquals(lionCanJumpOverMonkey, true);
	}
	
	@Test
	public void lion_can_jump_over_lion() {
		Bushi lion1 = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][4]);
		Bushi lion2 = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][5]);
		
		boolean lionCanJumpOverLion = lion1.isBiggerOrEqualThan(lion2);
		
		assertEquals(lionCanJumpOverLion, true);
	}
	
	@Test
	public void dragon_can_jump_over_monkey() {
		Bushi dragon = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][4]);
		Bushi monkey = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][5]);
		
		boolean dragonCanJumpOverMonkey = dragon.isBiggerOrEqualThan(monkey);
		
		assertEquals(dragonCanJumpOverMonkey, true);
	}
	
	@Test
	public void dragon_can_jump_over_lion() {
		Bushi dragon = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][4]);
		Bushi lion = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][5]);
		
		boolean dragonCanJumpOverLion = dragon.isBiggerOrEqualThan(lion);
		
		assertEquals(dragonCanJumpOverLion, true);
	}
	
	@Test
	public void dragon_can_jump_over_dragon() {
		Bushi dragon1 = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][4]);
		Bushi dragon2 = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][5]);
		
		boolean dragonCanJumpOverDragon = dragon1.isBiggerOrEqualThan(dragon2);
		
		assertEquals(dragonCanJumpOverDragon, true);
	}

	@Test
	public void monkey_cant_jump_over_lion() {
		Bushi monkey = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][4]);
		Bushi lion = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][5]);
		
		boolean monkeyCantJumpOverLion = monkey.isBiggerOrEqualThan(lion);
		
		assertEquals(monkeyCantJumpOverLion, false);
	}

	@Test
	public void monkey_cant_jump_over_dragon() {
		Bushi monkey = new Bushi(BushiType.MONKEY, Game.player1, Game.board.board[4][4]);
		Bushi dragon = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][5]);
		
		boolean monkeyCantJumpOverDragon = monkey.isBiggerOrEqualThan(dragon);
		
		assertEquals(monkeyCantJumpOverDragon, false);
	}
	
	@Test
	public void lion_cant_jump_over_dragon() {
		Bushi lion = new Bushi(BushiType.LION, Game.player1, Game.board.board[4][4]);
		Bushi dragon = new Bushi(BushiType.DRAGON, Game.player1, Game.board.board[4][5]);
		
		boolean lionCantJumpOverDragon = lion.isBiggerOrEqualThan(dragon);
		
		assertEquals(lionCantJumpOverDragon, false);
	}

}
