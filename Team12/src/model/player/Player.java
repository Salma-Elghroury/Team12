package model.player;

import engine.GameManager; 
import engine.board.BoardManager;
import model.card.Card;
import model.Colour;

import java.util.ArrayList;

public class Player {
	
	private final String name;
	private final Colour colour;
	private ArrayList<Card> hand;
	private final ArrayList<Marble> marbles;
	private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;
	
	public Player(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
		hand = new ArrayList<Card>();
		selectedMarbles = new ArrayList<Marble>();
		marbles = new ArrayList<Marble>();
		for (int i=0; i<4; i++) marbles.add(new Marble(colour));
		selectedCard = null;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<Marble> getMarbles() {
		return marbles;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}

}
