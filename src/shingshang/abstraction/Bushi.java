package shingshang.abstraction;

/** Represents a bushi.
 * @author Tanguy Mossion
 */

public class Bushi {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	final private BushiType bushiType;
	final private Player owner;
	final private int movementPoint;
	private Cell position;
	
	// METHODS
	
	/**
	 * Constructor initializing a Bushi (it also sets the bushi to the Cell position)
	 * @param 	bushiType	the bushi's type
	 * @param  	owner		the bushi's owner
	 * @param  	position	the bushi's position
	 * @see		BushiType
	 * @see		Player
	 * @see		Cell
	 */
	public Bushi(BushiType bushiType, Player owner, Cell position) {
		this.bushiType = bushiType;
		this.owner = owner;
		
		switch(this.bushiType)
		{
			case MONKEY:
				this.movementPoint = 2;
				break;
			case LION:
				this.movementPoint = 1;
				break;
			case DRAGON:
				this.movementPoint = 0;
				break;
			default:
				this.movementPoint = 0;
		}
		
		this.position = position;
		position.setBushi(this);
	}

	/**
	 * Returns 	the BushiType of the Bushi
	 * @return	the bushi's type
	 * @see		BushiType
	 */
	public BushiType getBushiType() {
		return this.bushiType;
	}
	
	/**
	 * Returns 	the movement points of the Bushi
	 * @return	the bushi's movement points
	 */
	public int getMovementPoint() {
		return this.movementPoint;
	}

	/**
	 * Returns 	the BushiType of the Bushi
	 * @return	the bushi's type
	 * @see		Cell
	 */
	public Cell getPosition() {
		return this.position;
	}
	
	/**
	 * Set a Cell object to the Bushi
	 * @param 	position the object Cell to set to the bushi property
	 * @see		Cell
	 */
	public void setPosition(Cell position) {
		this.position = position;
	}
	
	/**
	 * Move the bushi to the Cell
	 * @param 	position the object Cell where to move the bushi
	 * @see		Cell
	 */
	public void moveToCell(Cell position) {
		this.getPosition().removeBushi();
		this.setPosition(position);
		position.setBushi(this);
	}
	
	/**
	 * Returns a boolean indicating if the bushi is bigger or equal than the other bushi
	 * @param 	otherBushi	the other bushi to compare with the bushi
	 * @return 	a boolean 	indicating if the bushi is bigger or equal than the other bushi
	 */
	public boolean isBiggerOrEqualThan(Bushi otherBushi)
	{
		return(this.bushiType.ordinal() >= otherBushi.bushiType.ordinal());
	}
}