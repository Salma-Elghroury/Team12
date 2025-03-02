package model.card.standard;

public class Ace extends Standard{
	
	public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager
			 gameManager){
		super(name,description,1,suit,boardManager,gameManager);
	}
}
