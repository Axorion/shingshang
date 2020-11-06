package shingshang.abstraction;

/** Represents a player.
 * @author Tanguy Mossion
 */

public class Player {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	final private String pseudonym;

	// METHODS

	/**
	 * Constructor initializing a Player
	 * @param  pseudonym	the player's pseudonym
	 */
	public Player(String pseudonym) {
		this.pseudonym = pseudonym;
	}
	
	/**
	 * Returns the pseudonym of the Player
	 * @return	the Player's pseudonym
	 * @see		Bushi
	 */
	public String getPseudonym()
	{
		return this.pseudonym;
	}
}