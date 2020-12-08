package Model;

public class ShipModel {
	private int xPos[];
	private int yPos[];
	private int length;
	private int numHit;
	private boolean vertical;
	//private boolean isHit[];
	
	//Ship constructor for AIPlayerModel
	public ShipModel(int length) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		//isHit = new boolean[length];
	}
	
	//Ship constructor for PlayerModel
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
	
	/**
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
