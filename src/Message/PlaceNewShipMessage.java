package Message;

public class PlaceNewShipMessage implements Message{
	public int x;
	public int y;
	public int length;
	public boolean vertical;
	
	public PlaceNewShipMessage() {
		x = -1;
		y = -1;
		length = -1;
		vertical = false;
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
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public boolean vertical() {
		return vertical;
	}
	
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
}
