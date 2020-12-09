package View;

import java.awt.*;

public abstract class SelectableShape implements SceneShape
{
    public void setSelected(boolean b)
    {
        selected = b;
    }

    public boolean isSelected()
    {
        return selected;
    }

    /**
     * A method that returns coordinate of the Ship in a Grid in array.
     * @return array of coordinates. [0] is x coordinate, and [1] is y coordinate.
     */
    public int[] indexOf(){

        return new int[] {xCoordinate,yCoordinate};
    }

    /**
     * sets the coordinates of the first square of the grid.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setIndex(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
    }
    /**
     * Sets the rotational orientation of the ShipView.
     * @param vertical setting whether the ShipView should be vertical or not.
     */
    public void setVertical(boolean vertical){
        this.vertical = vertical;
    }
    /**
     * A method for checking if the ship is vertical or not
     * @return Returns true if the ship is vertical
     */
    public boolean isVertical(){
        return vertical;
    }
    /**
     * A method that sets the color of the square, but it does not invoke draw method inside it
     * @param color The color of the square
     */
    public void setColor(Color color){
        c = color;
    }


    /**
     * A method that returns the color of the square
     * @return The color of the square
     */
    public Color getColor(){
        return c;
    }


    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private boolean selected;
    private boolean vertical;
    private Color c;
}

