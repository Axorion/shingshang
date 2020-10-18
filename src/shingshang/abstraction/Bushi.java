package shingshang.abstraction;

/** Represents a bushi.
 * @author Tanguy Mossion
 */

public class Bushi {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	BushiType bushiType;
	Player owner;
	int movementPoint;
	Cell position;
	
	// METHODS
	
	public Bushi(BushiType bushiType, Player owner, int movementPoint, Cell position) {
		this.bushiType = bushiType;
		this.owner = owner;
		this.movementPoint = movementPoint;
		this.position = position;
	}
	
	public BushiType getBushiType(){
		return this.bushiType;
	}
}