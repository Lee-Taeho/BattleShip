package Message;

import View.ShipView;

/**
 * This is the message class responsible for passing player's 
 * ship placing positions from View to Model.
 */
public class PlaceNewShipMessage implements Message{ // might have to add new variable that reference to the ShipView

	private ShipView ship;
	
	/**
	 * gets the ship position from view
	 * @return ship returns a shipView
	 */
	public ShipView getShip() {
		return ship;
	}
	
	/**
	 * constructor for place new ship message
	 * @param ship the shipView sent within the message
	 */
	public PlaceNewShipMessage(ShipView ship){

		this.ship = ship;
	}
	
	/**
	 * gets the x position of the shipView that player placed on grid
	 * @return ship.indexOf()[0] the first x position of the shipView
	 */
	public int getX() {

		return ship.indexOf()[1];
	}
	
	/**
	 * sets the x position of the shipView that player placed on grid
	 * @param x
	 */
	public void setX(int x) {

		ship.indexOf()[1] = x;
	}
	
	/**
	 * gets the y position of the shipView that player placed on grid
	 * @return ship.indexOf()[0] the first y position of the shipView
	*/
	public int getY() {

		return ship.indexOf()[0];
	}
	
	/**
	 * sets the y position of the shipView that player placed on grid
	 * @param y
	 */
	public void setY(int y) {

		ship.indexOf()[0] = y;
	}
	
	/**
	 * gets the length of the shipView that player placed on grid
	 * @return ship.getLength() returns the length of the shipView
	 */
	public int getLength() {

		return ship.getLength();
	}

	/**
	 * check if the shipView is placed vertical
	 * @return ship.isVertical()
	 */
	public boolean isVertical(){

		return ship.isVertical();
	}
	

}
