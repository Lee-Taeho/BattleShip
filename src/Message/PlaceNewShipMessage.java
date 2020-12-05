package Message;

import View.ShipView;

public class PlaceNewShipMessage implements Message{ // might have to add new variable that reference to the ShipView

	private ShipView ship;

	public ShipView getShip() {
		return ship;
	}

	public PlaceNewShipMessage(ShipView ship){

		this.ship = ship;
	}
	
	public int getX() {

		return ship.indexOf()[1];
	}
	
	public void setX(int x) {

		ship.indexOf()[1] = x;
	}
	
	public int getY() {

		return ship.indexOf()[0];
	}
	
	public void setY(int y) {

		ship.indexOf()[0] = y;
	}
	
	public int getLength() {

		return ship.getLength();
	}

	public boolean isVertical(){

		return ship.isVertical();
	}
	

}
