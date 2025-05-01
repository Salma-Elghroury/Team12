package model.card.wild;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Burner extends Wild {

    public Burner(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager); 
    }
    
    //Milestone 2 Methods
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
    	
    	Colour playerColour = this.gameManager.getActivePlayerColour();
    	
    	for (int i = 0 ; i < marbles.size() ; i++) {
    		
    		if (marbles.get(i).getColour() == playerColour) {
    			
    			return false ;
    		}
    	}
    	
    	return true ;	
    	
    }
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	
    	this.boardManager.destroyMarble(marbles.get(0));
  
    }

}
