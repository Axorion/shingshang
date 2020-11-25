package abstraction;

/** Represents a player.
 * @author Tanguy Mossion
 */

public class Player {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	private String pseudonym;

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
	public String getPseudonym() {
		return this.pseudonym;
	}
	
	/**
	 * Set a the pseudonym of the Player
	 * @param 	pseudonym the player's pseudonym
	 */
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}
}