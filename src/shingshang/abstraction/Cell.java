package shingshang.abstraction;

/** Represents a cell.
 * @author Tanguy Mossion
 */


public class Cell {
	
	// CONSTANTS
	
	// INSTANCE VARIABLES (ATTRIBUTES)
	private Bushi bushi;
	private boolean isPortal;
	private boolean isValid;
	
	// METHODS
	
	public Cell(Boolean isPortal, Boolean isValid)
	{
		this.bushi = null;
		this.isPortal = isPortal;
		this.isValid = isValid;
	}
	
	public boolean isPortal()
	{
		return this.isPortal;
	}
	
	public boolean isValid()
	{
		return this.isValid;
	}
	
	public boolean isEmpty()
	{
		return (bushi == null);
	}
	
}