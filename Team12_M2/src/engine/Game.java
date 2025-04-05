package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import engine.board.Board;
import exception.*;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.*;

@SuppressWarnings("unused")
public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
	private int currentPlayerIndex;
    private final ArrayList<Card> firePit;
    private int turn;

    public Game(String playerName) throws IOException {
        turn = 0;
        currentPlayerIndex = 0;
        firePit = new ArrayList<>();

        ArrayList<Colour> colourOrder = new ArrayList<>();
        
        colourOrder.addAll(Arrays.asList(Colour.values()));
        
        Collections.shuffle(colourOrder);
        
        this.board = new Board(colourOrder, this);
        
        Deck.loadCardPool(this.board, (GameManager)this);
        
        this.players = new ArrayList<>();
        this.players.add(new Player(playerName, colourOrder.get(0)));
        
        for (int i = 1; i < 4; i++) 
            this.players.add(new CPU("CPU " + i, colourOrder.get(i), this.board));
        
        for (int i = 0; i < 4; i++) 
            this.players.get(i).setHand(Deck.drawCards());
        
    }
    
    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getFirePit() {
        return firePit;
    }
    
    public void selectCard(Card card) throws InvalidCardException {
    	try {
    		players.get(currentPlayerIndex).selectCard(card);
    	}
    	catch (InvalidCardException e){
    		System.out.println(e.getMessage());
    	}
    }
    
    //selectMarble
    
    //deselectAll()
    
    //editSplitDistance
    
    public boolean canPlayTurn(){
    	if (players.get(currentPlayerIndex).getHand().size()==0) return false;  //What does turn refer to???? and why is currentPlayerIndex referring to the NEXT player??
    	return true;
    }
    
    //playPlayerTurn
    
    //endPlayerTurn
    
    //checkWin
    
    //sendHome
    
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException{
    	if (players.get(currentPlayerIndex).getMarbles().get(0)==null)
    		throw new CannotFieldException();
    	else{
    		try{
    			board.sendToBase(players.get(currentPlayerIndex).getMarbles().get(0));
    			players.get(currentPlayerIndex).getMarbles().remove(0);
    		}
    		catch (CannotFieldException e){}
    		catch (IllegalDestroyException e){}
    	}
    }
    
    //discardCard
    
    //discardCard
    
    public Colour getActivePlayerColour(){
    	return players.get(currentPlayerIndex).getColour();
    }
    
    public Colour nextActivePlayerColour(){
    	if (currentPlayerIndex==3) return players.get(0).getColour();
    	return players.get(currentPlayerIndex+1).getColour();
    }
    
}
