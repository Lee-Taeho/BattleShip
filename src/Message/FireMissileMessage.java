package Message;

public class FireMissileMessage implements Message{
	int x;
	int y;
	boolean playerToAI;
	public static final boolean TO_AI = true;
	
	public FireMissileMessage() {
		x = -1;
		y = -1;
		playerToAI = false;
	}

	public FireMissileMessage(int x, int y, boolean isPlayer){
		this.x = x;
		this.y = y;
		playerToAI = isPlayer;
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
	
	public boolean isPlayerToAI() {
		return playerToAI;
	}
	
	public void setplayerToAI(boolean playerToAI) {
		this.playerToAI = playerToAI;
	}
}
