package Model;

public class Cruiser implements Ship {
	private final int CRUISER_SIZE = 4;
	private int xPos[];
	private int yPos[];
	private boolean isHit[];
	private int numHit;
	private boolean vertical;
	
	public Cruiser (int xPosition, int yPosition, boolean vertical) {
		xPos = new int[CRUISER_SIZE];
		yPos = new int[CRUISER_SIZE];
		isHit = new boolean[CRUISER_SIZE];
		this.vertical = vertical;
		
		for (int i = 0; i < CRUISER_SIZE; i++) {
			xPos[i] = xPosition;
			yPos[i] = yPosition;
			isHit[i] = false;
			if(vertical) {
				yPosition++;
			}else {
				xPosition++;
			}
		}
	}
	
	@Override
	public boolean registerHit(int x, int y) {
		for(int i = 0; i < CRUISER_SIZE; i++) {
			if(xPos[i] == x && yPos[i] == y && isHit[i] == false) {
				numHit++;
				isHit[i] = true;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean checkIfSunk() {
		if(numHit == CRUISER_SIZE) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void shipSkill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		return CRUISER_SIZE;
	}

	@Override
	public int getGridDisplay() {
		return 2;
	}
	
	@Override
	public int[] getxPos() {
		return xPos;
	}

	@Override
	public int[] getyPos() {
		return yPos;
	}
}
