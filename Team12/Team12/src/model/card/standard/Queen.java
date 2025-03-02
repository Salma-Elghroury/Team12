package model.card.standard;

public class Queen extends Standard{
	
	public Queen(String name, String description, Suit suit, BoardManager boardManager, GameManager
			 gameManager){
		super(name,description,12,suit,boardManager,gameManager);
	}
}
