package model.card.standard;

import engine.GameManager; 
import engine.board.BoardManager;

public class Four extends Standard {
	
	//Attributes Inherited
	
	//Constructor
	
	public Four (String name, String description, Suit suit, BoardManager boardManager, 
			GameManager gameManager){
		
		super(name, description, 4, suit, boardManager, gameManager) ; 
		
	}

}
