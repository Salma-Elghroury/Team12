package engine.board;

import java.util.ArrayList;

import engine.*;
import model.Colour;
import model.player.Marble;
import exception.*;

@SuppressWarnings("unused")
public class Board implements BoardManager {
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
	private final GameManager gameManager;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.gameManager = gameManager;
        
        for (int i = 0; i < 100; i++) {
            this.track.add(new Cell(CellType.NORMAL));
            
            if (i % 25 == 0) 
                this.track.get(i).setCellType(CellType.BASE);
            
            else if ((i+2) % 25 == 0) 
                this.track.get(i).setCellType(CellType.ENTRY);
        }

        for(int i = 0; i < 8; i++)
            this.assignTrapCell();

        for (int i = 0; i < 4; i++)
            this.safeZones.add(new SafeZone(colourOrder.get(i)));

        splitDistance = 3;
    }

    public ArrayList<Cell> getTrack() {
        return this.track;
    }

    public ArrayList<SafeZone> getSafeZones() {
        return this.safeZones;
    }
    
    @Override
    public int getSplitDistance() {
        return this.splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
   
    private void assignTrapCell() {
        int randIndex = -1;
        
        do
            randIndex = (int)(Math.random() * 100); 
        while(this.track.get(randIndex).getCellType() != CellType.NORMAL || this.track.get(randIndex).isTrap());
        
        this.track.get(randIndex).setTrap(true);
    }
    
    private ArrayList<Cell> getSafeZone(Colour colour){
    	for (int i=0; i<safeZones.size(); i++){
    		if (safeZones.get(i).getColour()==colour) return safeZones.get(i).getCells();
    	}
    	return null;
    }
    
    private int getPositionInPath(ArrayList<Cell> path, Marble marble) {
    	
    	int position;
    	
    	if (path.contains(marble)) {position = path.indexOf(marble);}
    	else {position = -1;}
    	
    	return position ;
    	
    }
    
    //getBasePosition
    
    //getEntryPosition
    
    private ArrayList<Cell> validateSteps(Marble marble, int steps){
    	Colour colour = getActivePlayerColour();
    	SafeZone safeZone;
    	for (int i=0; i<safeZones.size(); i++)
    		if (safeZones.get(i).getColour()==colour) safeZone = safeZones.get(i);
    	ArrayList<Cell> path = new ArrayList<Cell>();
    	int startPosition;
    	
    	if (getPositionInPath(track,marble)==-1 && getPositionInPath(safeZone,marble)==-1)
    		throw new IllegalMovementException("Marble cannot be moved.");
    	
    	else if (getPositionInPath(safeZone,marble)==-1){
    		
    		startPosition = getPositionInPath(track,marble);
    		
    		if (steps==4){
    			int targetPosition = startPosition-steps;
				for (int i=startPosition, j=0; j<steps ; j++){
					Cell current = track.get(i);
					path.add(current);
					if (i==0) i=99;
					else i--;
				}
    		}
    		
    		else{
    			int targetPosition = startPosition+steps;
    			for (int i=startPosition, j=0; j<steps ; j++){
					Cell current = track.get(i);
					path.add(current);
					if (i==99) i=0;
					else if (colour==marble.getColour() && i==getEntryPosition(colour)){
						moveInSafeZone(marble, safeZone, steps-j, i, path);
						break;
					}
					else i++;
				}
    		}
    		
    	}
    	
    	else if (getPositionInPath(track,marble)==-1){
    		
    		startPosition = getPositionInPath(safeZone,marble);
    		if (steps==4)
        		throw new IllegalMovementException("Can not move backwards in Safe Zone.");
    		
    		else if (colour!=marble.getColour())
    			throw new IllegalMovementException("Can not move opponent marble in Safe Zone.");
    		
    		else moveInSafeZone(marble, safeZone, steps, startPosition, path);
    		
    	}
    		
    }
    
    private void moveInSafeZone(Marble marble, SafeZone safeZone, int steps, int startPosition, ArrayList<Cell> path) throws IllegalMovementException{
		int availablePosition = -1;
		for (int i=3; availablePosition==-1 && i>=0; i--)
			if(safeZone.getCells().get(i).getMarble()==null) availablePosition = i;
		
		if (startPosition+steps>availablePosition){
			path = null;
			throw new IllegalMovementException("Rank of selected card is too high.");
		}
		
		else{
			int targetPosition = startPosition+steps;
			for (int i=startPosition; i<=targetPosition; i++){
				Cell current = safeZone.getCells().get(i);
				path.add(current);
			}
			
		}
		
	}
    
private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException {
    	
    	
    	Card Selected; //Check if card is king or not
    	
    	//Self-Blocking
    	
    	int myMarblesInFullPath = 1 ;
    		
    	for (int i = 1 ; i < fullPath.size() ; i++) {if (fullPath.get(i).getMarble() == marble) {myMarblesInFullPath ++ ;}}
    		
    	if (myMarblesInFullPath > 1 && Selected.getName() != "King") {
    		
    		throw new IllegalMovementException ("Self-Blocking: A marble cannot move if there is another marble owned by the "
    				                             + "same player either in its path or at the target position. Meaning, I, as a "
    				                             + "player cannot bypass or destroy my own marble.");}
    	
    	//Path Blockage
    	
    	for (int i = 1 ; i < fullPath.size() ; i++) {if (fullPath.get(i).getMarble() != null) {myMarblesInFullPath ++ ;}}
    	
    	if (myMarblesInFullPath > 1 && Selected.getName() != "King") {
    		
    		throw new IllegalMovementException ("Path Blockage: Movement is invalid if there is more than one marble "
    				                             + "(owned byany player) blocking the path");
    	}
    	
    	//Safe Zone Entry
    
    }
    
    //move
    
    //validateSwap
    
    //validateDestroy
    
    //validateFielding
    
    //validateSaving
    
    //moveBy
    
    public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
    	try{
    		validateSwap(marble_1,marble_2);
    		Marble temp = marble_1;
    		marble_1 = marble_2;
    		marble_2 = temp;
    	}
    	catch (IllegalSwapException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    //destroyMarble
    
    //sendToBase
    
    //sendToSafe
    
    public ArrayList<Marble> getActionableMarbles(){
    	ArrayList<Marble> list = new ArrayList<Marble>();
    	for (int i=0; i<track.size(); i++)
    		if (track.get(i).getMarble()!=null) list.add(track.get(i).getMarble());
    	Colour colour = getActivePlayerColour();
    	SafeZone safeZone;
    	for (int i=0; i<safeZones.size(); i++)
    		if (safeZones.get(i).getColour()==colour) safeZone = safeZones.get(i);
    	for (int i=0; i<safeZone.getCells().size(); i++)
    		if (safeZone.getCells().get(i).getMarble()!=null)
    			list.add(safeZone.getCells().get(i).getMarble());
    	return list;
    }
    
}
