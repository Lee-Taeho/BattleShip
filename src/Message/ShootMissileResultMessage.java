package Message;

public class ShootMissileResultMessage implements Message{
	public boolean hit;
	public int x;
	public int y;

	public boolean isHit() {
		return hit;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
