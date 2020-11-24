
public class Submarine implements Ship {
	private final int SUBMARINE_SIZE = 2;
	private int xPos[];
	private int yPos[];
	private boolean isHit[];
	private int numHit;
	private boolean vertical;
	
	public Submarine (int xPosition, int yPosition, boolean vertical) {
		xPos = new int[SUBMARINE_SIZE];
		yPos = new int[SUBMARINE_SIZE];
		isHit = new boolean[SUBMARINE_SIZE];
		this.vertical = vertical;
		
		for (int i = 0; i < SUBMARINE_SIZE; i++) {
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
		for(int i = 0; i < SUBMARINE_SIZE; i++) {
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
		if(numHit == SUBMARINE_SIZE) {
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
		return SUBMARINE_SIZE;
	}

	@Override
	public int getGridDisplay() {
		return 4;
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
		for(int i = 0; i < SUBMARINE_SIZE; i++) {
			if(xPos[i] == x && yPos[i] == y) {
				return true;
			}
		}
		return false;
	}
}
