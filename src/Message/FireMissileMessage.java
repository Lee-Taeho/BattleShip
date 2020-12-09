package Message;
/**
 * This is the message class responsible for passing player firing position from View to Model.
 * @author JiaJun Dai
 * @author Tae Ho Lee
 */
public class FireMissileMessage implements Message{
	int x;
	int y;
	boolean playerToAI;
	public static final boolean TO_AI = true;
	
	/**
	 * Fire missile message default constructor
	 */
	public FireMissileMessage() {
		x = -1;
		y = -1;
	}

	/**
	 * Fire missile message constructor with x and y firing coordinate
	 * @param x x coordinate of the firing position
	 * @param y y coordinate of the firing position
	 */
	public FireMissileMessage(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * gets the x position of the shot
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * sets the x position of the shot
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * gets the y position of the shot
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * sets the y position of the shot
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
