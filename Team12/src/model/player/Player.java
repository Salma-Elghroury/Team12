package model.player;

import engine.GameManager; 
import engine.board.BoardManager;
import model.card.Card;
import model.Colour;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private Colour colour;
	private ArrayList<Card> hand;
	private ArrayList<Marble> marbles;
	private Card selectedCard;
	private ArrayList<Marble> selectedMarbels;
	
	public Player(String name, Colour colour){
		this.hand = new ArrayList<Card>();
		this.selectedMarbels = new ArrayList<Marble>();
		this.marbles = new ArrayList<Marble>();
		Marble marble1 = new Marble(colour);
		Marble marble2 = new Marble(colour);
		Marble marble3 = new Marble(colour);
		Marble marble4 = new Marble(colour);
		marbles.add(marble1);
		marbles.add(marble2);
		marbles.add(marble3);
		marbles.add(marble4);
		this.selectedCard = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
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

	public void setMarbles(ArrayList<Marble> marbles) {
		this.marbles = marbles;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}

	public ArrayList<Marble> getSelectedMarbels() {
		return selectedMarbels;
	}

	public void setSelectedMarbels(ArrayList<Marble> selectedMarbels) {
		this.selectedMarbels = selectedMarbels;
	}

}
