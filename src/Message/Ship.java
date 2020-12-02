package Message;

public class Ship {

    public Ship(int length){
        this.length = length;
        this.vertical = true;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public boolean isVertical(){
        return vertical;
    }

    public int getLength() {
        return length;
    }

    public void setVertical(boolean vertical){
        this.vertical = vertical;
    }

    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private int length;
    private boolean vertical;
}
