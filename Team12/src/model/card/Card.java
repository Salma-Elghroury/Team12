package model.card;

import engine.GameManager;
import engine.board.BoardManager;

public abstract class Card {
	
	//Attributes
	
	private final String name; 
    private final String description; 
    protected BoardManager boardManager; 
    protected GameManager gameManager;
    
    //Constructors
    
    public Card(String name, String description, BoardManager boardManager, GameManager gameManager) {
    	
        this.name = name;
        this.description = description;
        this.boardManager = boardManager;
        this.gameManager = gameManager;
    }
    
    //Methods
    
    public String getName() {return name;}

    public String getDescription() {return description;}
    
}
