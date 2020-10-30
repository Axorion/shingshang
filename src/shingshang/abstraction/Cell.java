package shingshang.abstraction;

/** Represents a cell.
 * @author Tanguy Mossion
 */

public class Cell {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	private Bushi bushi;
	final private boolean isPortal;
	final private boolean isValid;
		
	// METHODS
		
	/**
	 * Constructor initializing a Cell
	 * @param  isPortal indicates if the cell is a portal
	 * @param  isValid	indicates if the cell is valid
	 */
	public Cell(Boolean isPortal, Boolean isValid)
	{
		this.bushi = null;
		this.isPortal = isPortal;
		this.isValid = isValid;
	}
	
	/**
	 * Returns the Bushi object on the cell
	 * @return	the bushi on the cell
	 * @see		Bushi
	 */
	public Bushi getBushi()
	{
		return this.bushi;
	}
	
	/**
	 * Set a Bushi object on the cell
	 * @param 	bushi	the object Bushi to set to the bushi property
	 * @see		Bushi
	 */
	public void setBushi(Bushi bushi)
	{
		this.bushi = bushi;
	}
 
	/**
	 * Remove the Bushi object from the cell
	 * @see	Bushi
	 */
	public void removeBushi()
	{
		this.bushi = null;
	}
	
	/**
	 * Returns a boolean indicating if the cell is a portal
	 * @return a boolean indicating if the cell is a portal
	 */
	public boolean isPortal()
	{
		return this.isPortal;
	}
	
	/**
	 * Returns a boolean indicating if the cell is valid
	 * @return a boolean indicating if the cell is valid
	 */
	public boolean isValid()
	{
		return this.isValid;
	}

	/**
	 * Returns a boolean indicating if the cell is empty
	 * @return a boolean indicating if the cell is empty
	 */
	public boolean isEmpty()
	{
		return (bushi == null);
	}
}