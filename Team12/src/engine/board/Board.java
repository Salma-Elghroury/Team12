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
	
	public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
		
		this.gameManager = gameManager;
		track = new ArrayList<Cell>();
		safeZones = new ArrayList<SafeZone>();
		splitDistance = 3;
		
		for (int i=0; i<100; i++) {track.add(new Cell(CellType.NORMAL));}
		for (int i=0; i<100; i+=25) {track.get(i).setCellType(CellType.BASE);}
		for (int i=23; i<100; i+=25) {track.get(i).setCellType(CellType.ENTRY);}
		for  (int i=0; i<8; i++) {assignTrapCell();}
		
		safeZones.add(new SafeZone(colourOrder.get(0)));
		safeZones.add(new SafeZone(colourOrder.get(1)));
		safeZones.add(new SafeZone(colourOrder.get(2)));
		safeZones.add(new SafeZone(colourOrder.get(3)));
	}
	
	private void assignTrapCell(){
		
		Random random = new Random();
		int index;
		
		do {
			index = random.nextInt(100);
			
		} while (track.get(index).getCellType()!=CellType.NORMAL || track.get(index).isTrap());
		
		track.get(index).setTrap(true);
	}

	public ArrayList<Cell> getTrack() {return track;}

	public ArrayList<SafeZone> getSafeZones() {return safeZones;}
	
	public int getSplitDistance() {return splitDistance;}

	public void setSplitDistance(int splitDistance) {this.splitDistance = splitDistance;}

}
