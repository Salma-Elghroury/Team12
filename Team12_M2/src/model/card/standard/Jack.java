package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    
    //Milestone 2 Methods 
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	if (marbles.size() == 1 || marbles.size() == 2) {return true;}
    	
    	else {return false;}
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
    	
    	Colour playerColour = this.gameManager.getActivePlayerColour();
    	Marble playerMarble = new Marble (playerColour);
    	int myMarbles = 0 ;
    	boolean flag = true ;
    	
    	for (int i = 0 ; i < marbles.size() ; i++) {
    		
    		if (myMarbles > 1) {flag = false ; break ;} 
    		else if (marbles.get(i) == playerMarble) {myMarbles ++ ;}
    	}
    	
    	return flag ;
    	
    }
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	
    	if (marbles.size() == 1 && this.validateMarbleColours(marbles)) {super.act(marbles);}
    	
    	else if (marbles.size() == 2 && this.validateMarbleColours(marbles)) {
    		
    		this.boardManager.swap(marbles.get(0), marbles.get(1));
    		
    	}
    	
    	else {throw new InvalidMarbleException ("Invalid Marbles");}
    }

}
