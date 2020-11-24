
public class Carrier implements Ship {
	private final int CARRIER_SIZE = 5;
	private int xPos[];
	private int yPos[];
	private boolean isHit[];
	private int numHit;
	private boolean vertical;
	
	public Carrier (int xPosition, int yPosition, boolean vertical) {
		xPos = new int[CARRIER_SIZE];
		yPos = new int[CARRIER_SIZE];
		isHit = new boolean[CARRIER_SIZE];
		this.vertical = vertical;
		
		for (int i = 0; i < CARRIER_SIZE; i++) {
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
		for(int i = 0; i < CARRIER_SIZE; i++) {
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
		if(numHit == CARRIER_SIZE) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void shipSkill() {
		// TODO Auto-generated method stub
		
	}
	
	public int getSize() {
		return CARRIER_SIZE;
	}
	
	public int getGridDisplay() {
		return 1;
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
