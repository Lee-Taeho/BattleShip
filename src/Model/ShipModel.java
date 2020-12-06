package Model;
/** Represents a ship.
 * @author JiaJun Dai
*/
public class ShipModel {
	private int xPos[];
	private int yPos[];
	private int length;
	private int numHit;
	private boolean vertical;
	private boolean isHit[];
	
	/**
	* Constructor for AIPlayerModel
	*/
	public ShipModel(int length) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		isHit = new boolean[length];
	}
	
	/**
	* Constructor for playerModel
	*/
	public ShipModel(int x, int y, int length, boolean vertical) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		isHit = new boolean[length];
		this.vertical = vertical;
		
		for (int i = 0; i < length; i++) {
			xPos[i] = x;
			yPos[i] = y;
			isHit[i] = false;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		vertical = true;
	}
	
	/**
	* Register the hit into a boolean array isHit[]
	* @param x x position of the hit
	* @param y y position of the hit
	* @return true if 
	*/
	public boolean registerHit(int x, int y) {
		for(int i = 0; i < length; i++) {
			if(xPos[i] == x && yPos[i] == y && isHit[i] == false) {
				numHit++;
				isHit[i] = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfSunk() {
		if(numHit == length) {
			return true;
		}else {
			return false;
		}
	}
	
	public int getGridDisplay() {
		if(length == 2) {
			return 2;
		}else if(length == 3) {
			return 3;
		}else if(length == 4) {
			return 4;
		}else if(length == 5) {
			return 5;
		}else
			return 0; //should never get here
	}
	
	//To separate our two length-3 ships
	public boolean contains(int x, int y) {
		for(int i = 0; i < length; i++) {
			if(xPos[i] == x && yPos[i] == y) {
				return true;
			}
		}
		return false;
	}
	
	public int[] getXPos() {
		return xPos;
	}
	
	public void setXPos(int[] xPos) {
		this.xPos = xPos;
	}

	public int[] getYPos() {
		return yPos;
	}
	
	public void setYPos(int[] yPos) {
		this.yPos = yPos;
	}
	
	public boolean[] getIsHit() {
		return isHit;
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
	
	public int getLength() {
		return length;
	}
}
