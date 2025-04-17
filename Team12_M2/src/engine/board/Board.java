package engine.board;

import java.util.ArrayList;

import engine.*;
import model.Colour;
import model.card.Card;
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

        for(int i = 0; i < 8; i++) {this.assignTrapCell();}

        for (int i = 0; i < 4; i++) {this.safeZones.add(new SafeZone(colourOrder.get(i)));}

        splitDistance = 3;
    }

    public ArrayList<Cell> getTrack() {return this.track;}

    public ArrayList<SafeZone> getSafeZones() {return this.safeZones;}
    
    @Override
    public int getSplitDistance() {return this.splitDistance;}

    public void setSplitDistance(int splitDistance) {this.splitDistance = splitDistance;}
   
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
   
    private int getBasePosition(Colour colour){
    	
	   ArrayList<SafeZone> safeZones = this.getSafeZones();
	   int basePosition=0;
	   
	   for(int i=0; i<4; i++, basePosition+=25){
		   
		  if (safeZones.get(i).getColour()==colour)
			  
			return basePosition;
	   }
	    return -1;
   }
    
    //getEntryPosition
    
    private ArrayList<Cell> validateSteps(Marble marble, int steps){
    	
    	Colour colour = this.gameManager.getActivePlayerColour();
    	SafeZone safeZone;
    	
    	for (int i=0; i<safeZones.size(); i++)
    		
    		if (safeZones.get(i).getColour()==colour) safeZone = safeZones.get(i);
    	
    	ArrayList<Cell> path = new ArrayList<Cell>();
    	int startPosition;
    	
    	if (getPositionInPath(track,marble)==-1 && this.getPositionInPath(safeZone,marble)==-1)
    		
    		throw new IllegalMovementException("Marble cannot be moved.");
    	
    	else if (this.getPositionInPath(safeZone,marble)==-1){
    		
    		startPosition = this.getPositionInPath(track,marble);
    		
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
					
					else if (colour == marble.getColour() && i == this.getEntryPosition(colour)){
						moveInSafeZone(marble, safeZone, steps-j, i, path);
						break;
					}
					
					else i++;
				}
    		}
    		
    	}
    	
    	else if (getPositionInPath(track,marble)==-1){
    		
    		startPosition = this.getPositionInPath(safeZone,marble);
    		
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
	   
	   //Self-Blocking
	   
	   if (fullPath.contains(marble) && fullPath.indexOf(marble) != 0 && destroy == false) {
		   
		   throw new IllegalMovementException ("Self-Blocking :- Cannot Destroy or Bypass Your Marbles");
	   }
	   
	   //Path-Blockage
	   
	   for (int i = 1 ; i < fullPath.size() ; i++) {
		   
		   if (fullPath.get(i) != null && destroy == false) {
			   
			   throw new IllegalMovementException ("Path Blockage :- Cannot Bypass Any Marble in Path");
			   
		   }
	   }
	   
	   //Safe Zone Entry
	   
	   if (safeZones.get(0) != null) {
		   
		   throw new IllegalMovementException ("Safe Zone Entry :- Safe Zone Marbles Cannot be Bypassed or Destroyed (Even if King)");
	   }
	   
	   //Base Cell Blockage
	   
	   int basePosition = this.getBasePosition(marble.getColour());
	   Cell playerBase = this.track.get(basePosition);
	   
	   for (int i = 1 ; i < fullPath.size() ; i++) {
		   
		   if (fullPath.contains(playerBase) && fullPath.get(fullPath.indexOf(playerBase)) != null && 
				   fullPath.get(fullPath.indexOf(playerBase)).getMarble().getColour() != marble.getColour()) {
			   
			   throw new IllegalMovementException ("Base Cell Blockage :- Cannot Bypass Other Players' Marbles if Placed in Your Base Cell");
			   
		   }
    	
    	}
	   
   }
    
   private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException{
		
	   int position = getPositionInPath(fullPath, marble);
	    	
	   //remove from current cell
	    	
	   fullPath.get(position).setMarble(null); 
	    	
	   //handle king
	    	
	   if(destroy){
	    		
		   for(int i=0; i<fullPath.size();i++){
	    			
			   if (fullPath.get(i).getMarble()!=null)
	    				 
				   destroyMarble(fullPath.get(i).getMarble());	
	    		}
	    	}
	    	
	   //places the marble in the calculated target cell and handles traps
	    	
	   if(fullPath.get(fullPath.size()-1).isTrap()) {
	    		
		   destroyMarble(marble);
	    		
		   fullPath.get(fullPath.size()-1).setTrap(false);
	    		
		   this.assignTrapCell();
	    	}
	    	
	   else fullPath.get(fullPath.size()-1).setMarble(marble);
	}
    
   private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
	  
	  if(marble_1.getColour()==marble_2.getColour()) throw new IllegalSwapException("Nothing will be changed!");
	  
	  else if( !isInTrack(marble_1) || !isInTrack(marble_2)) throw new IllegalSwapException("The marbles are not on the track!");
	  
	  else if( (isInSafe(marble_1) && !isInSafe(marble_2))||(isInSafe(marble_2) && !isInSafe(marble_1)) ) throw new IllegalSwapException("Your opponent�s marble is safe in its Base Cell.");	 
}
   
   public boolean isInTrack(Marble marble){
	   
	   //checks that the marble is not in home zone  and not in safe zone, meaning they are in track
	   
	  return (getPositionInPath(this.track, marble)!=-1 && !isInSafe(marble));
   }
   
   public boolean isInSafe(Marble marble){
	   
	  //checks if a marble is in its safe zone
	  return getPositionInPath(getSafeZone(marble.getColour()), marble)!=-1;  
	  
   }
    
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
    
    public void destroyMarble(Marble marble) throws IllegalDestroyException {
    	
    	try {
    		
    		if (this.gameManager.getActivePlayerColour() != marble.getColour()) {
    			
    			int marblePosition = this.getPositionInPath(this.track, marble);
    			validateDestroy(marblePosition);
    			
    		}
    		
    		this.track.remove(marble);
    		this.gameManager.sendHome(marble);
    	}
    	
    	catch (IllegalDestroyException e) {
    		
    		System.out.print(e.getMessage()) ;
    	}
    }
    
 public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException {
	 
	    int base = getBasePosition(marble.getColour());
	    
		try{
			 validateFielding(this.track.get(base));
			 if(this.track.get(base).getMarble()!=null){
				 destroyMarble(this.track.get(base).getMarble()); 
			 }
			 
			 this.track.get(base).setMarble(marble);
		}
		
		catch(CannotFieldException e){
			throw new CannotFieldException("Base cell is already occupied by your marbles.");
		}
 }
    
    //sendToSafe
    
    public ArrayList<Marble> getActionableMarbles(){
    	
    	ArrayList<Marble> list = new ArrayList<Marble>();
    	
    	for (int i=0; i<track.size(); i++)
    		
    		if (track.get(i).getMarble()!=null) list.add(track.get(i).getMarble());
 
    	Colour colour = this.gameManager.getActivePlayerColour();
    	SafeZone safeZone;
    	
    	for (int i=0; i<safeZones.size(); i++)
    		
    		if (safeZones.get(i).getColour()==colour) safeZone = safeZones.get(i);
    	
    	for (int i=0; i<safeZone.getCells().size(); i++)
    		
    		if (safeZone.getCells().get(i).getMarble()!=null)
    			
    			list.add(safeZone.getCells().get(i).getMarble());
    	
    	return list;
    }
    
}
