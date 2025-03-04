package engine; 

import engine.GameManager;   
import engine.board.Board;
import engine.board.BoardManager;
import model.Colour;
import model.player.*;
import model.card.*;

import java.util.*;
import java.io.IOException ;

public class Game implements GameManager {
	
	private final Board board ;	
	private final ArrayList<Player> players;
	private final ArrayList<Card> firePit;
	private int currentPlayerIndex;
	private int turn;
	
	public Game(String playerName) throws IOException {
		ArrayList<Colour> colourOrder = new ArrayList<Colour>();
		colourOrder.add(Colour.RED);
		colourOrder.add(Colour.GREEN);
		colourOrder.add(Colour.BLUE);
		colourOrder.add(Colour.YELLOW);
		Collections.shuffle(colourOrder);
		board = new Board(colourOrder, (GameManager)this);
		Deck.loadCardPool((BoardManager)board, (GameManager)this);
		players = new ArrayList<Player>();
		players.add(new Player(playerName,colourOrder.get(0)));
		players.add(new CPU("CPU 1",colourOrder.get(1), (BoardManager)board));
		players.add(new CPU("CPU 2",colourOrder.get(2), (BoardManager)board));
		players.add(new CPU("CPU 3",colourOrder.get(3), (BoardManager)board));
		for (int i=0; i<4; i++) players.get(i).setHand(Deck.drawCards());
		currentPlayerIndex = 0;
		turn = 0;
		firePit = new ArrayList<Card>();
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

}
