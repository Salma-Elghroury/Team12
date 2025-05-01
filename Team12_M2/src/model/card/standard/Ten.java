package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Ten extends Standard {

    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
    
    //Milestone 2 Methods
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	if (marbles.size() == 1 || marbles.size() == 0) return true;
    	else return false;
    	
    }
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	
    	if (marbles.size() == 1) super.act(marbles);
    	else if (marbles.size() == 0) this.gameManager.discardCard(this.gameManager.getNextPlayerColour());
    
    }

}
