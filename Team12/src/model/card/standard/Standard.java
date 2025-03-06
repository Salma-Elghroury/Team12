package model.card.standard;

import engine.GameManager;  
import engine.board.BoardManager;
import model.card.Card;

public class Standard extends Card {
	
	//Attributes
	
	private final int rank;
	private final Suit suit;
	
	//Constructors
	
	public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager, GameManager gameManager) {
		
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
        
    }
	
	//Methods

    public int getRank() {return rank;}

    public Suit getSuit() {return suit;}
    
}
