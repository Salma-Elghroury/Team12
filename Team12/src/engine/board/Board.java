package engine.board;

import engine.GameManager;  
import engine.board.BoardManager;
import model.Colour;

import java.util.ArrayList;
import java.util.Random;

public class Board implements BoardManager {
	
	private final GameManager gameManager;
	private final ArrayList<Cell> track;
	private final ArrayList<SafeZone> safeZones;
	private int splitDistance;
	
	public Board(ArrayList<Colour> colourOrder, GameManager gameManager){
		this.gameManager = gameManager;
		this.track = new ArrayList<Cell>();
		this.safeZones = new ArrayList<SafeZone>();
		splitDistance = 3;
		
		
		for  (int i=0; i<8; i++) assignTrapCell();
		SafeZone safeZone1 = new SafeZone(Colour.RED);
		SafeZone safeZone2 = new SafeZone(Colour.YELLOW);
		SafeZone safeZone3 = new SafeZone(Colour.BLUE);
		SafeZone safeZone4 = new SafeZone(Colour.GREEN);
		safeZones.add(safeZone1);
		safeZones.add(safeZone2);
		safeZones.add(safeZone3);
		safeZones.add(safeZone4);
	}
	
	public void assignTrapCell(){
		Random random = new Random();
		int index;
		do {
			index = random.nextInt(100);
		} while (track.get(index).getCellType()!=CellType.NORMAL);
		track.get(index).setTrap(true);
	}

	public int getSplitDistance() {
		return splitDistance;
	}

	public void setSplitDistance(int splitDistance) {
		this.splitDistance = splitDistance;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public ArrayList<Cell> getTrack() {
		return track;
	}

	public ArrayList<SafeZone> getSafeZones() {
		return safeZones;
	}

}
