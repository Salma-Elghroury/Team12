package engine.board;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
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
		SafeZone safeZone1 = new SafeZone(colourOrder[0]);
		SafeZone safeZone2 = new SafeZone(colourOrder[1]);
		SafeZone safeZone3 = new SafeZone(colourOrder[2]);
		SafeZone safeZone4 = new SafeZone(colourOrder[3]);
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
		} while (track[index].getCellType()!=CellType.NORMAL);
		track[index].setTrap(true);
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
