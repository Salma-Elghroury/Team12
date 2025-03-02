package engine;

import java.util.ArrayList;

public class Game implements GameManager {
	
	private final Board boPlayer	private final ArrayList<Player> players;
	private final ArrayList<Card> firePit;
	private int currentPlayerIndex;
	private int turn;
	
	public Game(String playerName) throws IOException{
		ArrayList<Colour> colourOrder = new ArrayList<Colour>(4);
		colourOrder.add(Colour.RED);
		colourOrder.add(Colour.GREEN);
		colourOrder.add(Colour.BLUE);
		colourOrder.add(Colour.YELLOW);
		colourOrder.shuffle();
		board = new Board(colourOrder,GameManager);
		Deck.loadCardPool(BoardManager,GameManager);
		Player human = new Player(playerName,colourOrder[0]);
		CPU cpu1 = new CPU("CPU 1",colourOrder[1],BoardManager);
		CPU cpu2 = new CPU("CPU 2",colourOrder[2],BoardManager);
		CPU cpu3 = new CPU("CPU 3",colourOrder[2],BoardManager);
		players = new ArrayList<Player>(4);
		players.add(human);
		players.add(cpu1);
		players.add(cpu2);
		players.add(cpu3);
		human.setHand(Deck.drawCards());
		cpu1.setHand(Deck.drawCards());
		cpu2.setHand(Deck.drawCards());
		cpu3.setHand(Deck.drawCards());
		currentPlayerIndex = 0;
		turn = 0;
		firePit = new ArrayList<Card>();
	}

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Board getBoPlayer() {
		return boPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getFirePit() {
		return firePit;
	}

}
