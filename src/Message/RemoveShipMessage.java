package Message;

import View.ShipView;
/**
 * This is a message class responsible for generate message 
 * when player drag the ship on the grid again to reposition it or to remove it
 */
public class RemoveShipMessage implements Message {
	private ShipView ship;

	/**
	 * constructor for remove ship message
	 * @param ship
	 */
	public RemoveShipMessage(ShipView ship) {
        this.ship = ship;
    }
	
	/**
	 * gets the x position of the head of the ship player is dragging
	 * @return x x position of the ship
	 */
    public int getX() {

        return ship.indexOf()[1];
    }

    /**
	 * gets the y position of the head of the ship player is dragging
	 * @return y y position of the ship
	 */
    public int getY() {

        return ship.indexOf()[0];
    }

    /**
     * gets the length of the shipView player is dragging
     * @return the length of the ship
     */
    public int getLength() {

        return ship.getLength();
    }

    /**
     * check if the shipView was placed vertically
     * @return true if it was placed vertically
     * @return false if it was not
     */
    public boolean isVertical(){

        return ship.isVertical();
    }

    /**
     * gets the shipView from view class
     * @return ship
     */
    public ShipView getShip(){

	    return ship;
    }


    }

