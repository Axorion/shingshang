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
	
	public BushiType getBushiType(){
		return this.bushiType;
	}
}