package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Four  extends Standard {

    public Four(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 4, suit, boardManager, gameManager);
    }
    
    //Milestone 2 Methods
    
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
    	this.boardManager.moveBy(marbles.get(0),-4,false);
    }

}
