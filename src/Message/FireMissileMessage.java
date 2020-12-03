package Message;

public class FireMissileMessage {
	int x;
	int y;
	boolean atPlayer;
	
	public FireMissileMessage() {
		x = -1;
		y = -1;
		atPlayer = false;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isAI() {
		return atPlayer;
	}
	
	public void setIsAI(boolean isAI) {
		this.atPlayer = atPlayer;
	}
}
