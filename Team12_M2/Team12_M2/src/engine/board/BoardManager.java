package engine.board;

import java.util.ArrayList;

import exception.*;
import model.player.Marble;


public interface BoardManager { 
	
    public int getSplitDistance();
    public void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException;
    public void swap(Marble marble1, Marble marble2) throws IllegalSwapException;
    public void destroyMarble(Marble marble) throws IllegalDestroyException;
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException;
    public void sendToSafe(Marble marble) throws InvalidMarbleException;
    public ArrayList<Marble> getActionableMarbles();

}
