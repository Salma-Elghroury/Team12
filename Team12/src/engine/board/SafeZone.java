package engine.board;

import model.Colour;
import java.util.*;

public class SafeZone {
	
	//Attributes
	
	private final Colour colour; 
	private final ArrayList<Cell> cells;
	
	//Constructors
	
	public SafeZone(Colour colour) {
		
		this.colour = colour;
        this.cells = new ArrayList<>();
        for (int i = 0; i < 4; i++)  {cells.add(new Cell(CellType.SAFE));}
        
    }
	
	//Methods
	
	public Colour getColour() {return colour;}
	
	public ArrayList<Cell> getCells() {return cells;}
	
}
