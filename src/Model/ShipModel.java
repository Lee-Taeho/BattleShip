package Model;

/** Represents a ship.
 * @author JiaJun Dai
*/
public class ShipModel {
	private int xPos[];
	private int yPos[];
	private int length;
	private boolean vertical;
	//private boolean isHit[];
	
	/**
	 * Constructor for class ShipModel, use for AIPlayerModel.setShip()
	 * @param length length of the ship
	 */
	public ShipModel(int length) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		//isHit = new boolean[length];
	}
	
	/**
	 * Constructor for class ShipModel, use for PlayerModel.setShip()
	 * @param x
	 * @param y
	 * @param length
	 * @param vertical
	 */
	public ShipModel(int x, int y, int length, boolean vertical) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		//isHit = new boolean[length];
		this.vertical = vertical;
		
		for (int i = 0; i < length; i++) {
			xPos[i] = x;
			yPos[i] = y;
			//isHit[i] = false;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		vertical = true;
	}
	
	/*
	public void registerHit(int x, int y) {
		for(int i = 0; i < length; i++) {
			if(xPos[i] == x && yPos[i] == y && isHit[i] == false) {
				numHit++;
				isHit[i] = true;
			}
		}
	}
	
	public boolean checkIfSunk() {
		if(numHit == length) {
			return true;
		}else {
			return false;
		}
	}
	*/
	
	/**
	 * get the grid display number for each ship with different length
	 * @return int the numbers representing each ship, whether it's length 2,3,4,5
	 */
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
	
	/*
	//To separate our two length-3 ships
	public boolean contains(int x, int y) {
		for(int i = 0; i < length; i++) {
			if(xPos[i] == x && yPos[i] == y) {
				return true;
			}
		}
		return false;
	}
	*/
	
	/**
	 * get all x positions of the ship
	 * @return xPos an array contains all ship's x position
	 */
	public int[] getXPos() {
		return xPos;
	}
	
	/**
	 * set all x position of the ship
	 * @param xPos
	 */
	public void setXPos(int[] xPos) {
		this.xPos = xPos;
	}

	/**
	 * get all y positions of the ship
	 * @return yPos an array contains all ship's y position
	 */
	public int[] getYPos() {
		return yPos;
	}
	
	/**
	 * set all y position of the ship
	 * @param yPos
	 */
	public void setYPos(int[] yPos) {
		this.yPos = yPos;
	}
	
	/**
	 * check if the ship placement is vertical or horizontal
	 * @return true the ship placement is vertical
	 * @return false the ship placement is horizontal
	 */
	public boolean isVertical() {
		return vertical;
	}
	
	/**
	 * set the ship placement to be vertical or horizontal
	 * @param vertical 
	 */
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
	
	/**
	 * get the ship length
	 * @return length ship length
	 */
	public int getLength() {
		return length;
	}
}
