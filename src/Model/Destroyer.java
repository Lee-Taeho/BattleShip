package Model;

public class Destroyer implements Ship {
	private final int DESTROYER_SIZE = 3;
	private int xPos[];
	private int yPos[];
	private boolean isHit[];
	private int numHit;
	private boolean vertical;
	
	public Destroyer (int xPosition, int yPosition, boolean vertical) {
		xPos = new int[DESTROYER_SIZE];
		yPos = new int[DESTROYER_SIZE];
		isHit = new boolean[DESTROYER_SIZE];
		this.vertical = vertical;
		
		for (int i = 0; i < DESTROYER_SIZE; i++) {
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
		for(int i = 0; i < DESTROYER_SIZE; i++) {
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
		if(numHit == DESTROYER_SIZE) {
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
		return DESTROYER_SIZE;
	}

	@Override
	public int getGridDisplay() {
		return 3;
	}
	
	@Override
	public int[] getxPos() {
		return xPos;
	}

	@Override
	public int[] getyPos() {
		return yPos;
	}
	
	public boolean contains(int x, int y) {
		for(int i = 0; i < DESTROYER_SIZE; i++) {
			if(xPos[i] == x && yPos[i] == y) {
				return true;
			}
		}
		return false;
	}
}
