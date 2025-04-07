package engine.board;

import java.util.ArrayList; 

import engine.GameManager;
import exception.IllegalMovementException;
import model.Colour;
import model.card.Card;
import model.player.*;

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
    
    private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
		 // unfinished 
		 if(marble_1.getColour()==marble_2.getColour()) throw new IllegalSwapException("Nothing will be changed!");
		 else if() throw new IllegalSwapException("The two marbles are not on the track!");
		 else if() throw new IllegalSwapException("Your opponent�s marble is safe in its Base Cell.");
		 
	 }
    
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException {
		//finished (although needs more checking)
    	int base = getBasePosition(marble.getColour());
		
		if(this.track.get(base)!=null) {
			try{
				validateFielding(this.track.get(base));
				this.track.get(base).setMarble(marble);
			}
			catch(CannotFieldException e){
				if(this.track.get(base).getMarble().getColour()==marble.getColour())
					throw new CannotFieldException("Base cell is already occupied by your marbles.");
				else{
					destroyMarble(this.track.get(base).getMarble());
					this.track.get(base).setMarble(marble);
				}
			}
		}
		else this.track.get(base).setMarble(marble);
		
	}
    

/*
– Safe Zone Entry: A marble cannot enter its player’s Safe Zone if any marble is
stationed at its player’s Safe Zone Entry.
– Base Cell Blockage: A marble’s movement is blocked if another player’s marble is
in its player’s Base cell, either in the path or target position.
*/

    
}
