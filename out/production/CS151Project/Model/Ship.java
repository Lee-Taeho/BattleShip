
public class Ship {
	private int xPos[];
	private int yPos[];
	private int length;
	private boolean vertical;
	
	public Ship(int x, int y, int length, boolean vertical) {
		this.length = length;
		xPos = new int[length];
		yPos = new int[length];
		this.vertical = vertical;
		
		for (int i = 0; i < length; i++) {
			xPos[i] = x;
			yPos[i] = y;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		this.length = length;
		vertical = true;
	}
	
	public int[] getxPos() {
		return xPos;
	}

	public int[] getyPos() {
		return yPos;
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
	
	public int getGridDisplay() {
		return 1;
	}
}