package Message;

import View.ShipView;

public class RemoveShipMessage implements Message {


        private ShipView ship;

	public RemoveShipMessage(ShipView ship) {
        this.ship = ship;
    }

    public int getX() {

        return ship.indexOf()[1];
    }

    public int getY() {

        return ship.indexOf()[0];
    }

    public int getLength() {

        return ship.getLength();
    }

    public boolean isVertical(){

        return ship.isVertical();
    }

    public ShipView getShip(){

	    return ship;
    }


    }

