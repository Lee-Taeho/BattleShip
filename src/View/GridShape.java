package View;

import java.awt.*;
import java.awt.geom.*;

/**
 A shape that is a part of a scene.
 */
public interface GridShape
{
    /**
     Draws this item.
     @param g2 the graphics context
     */
    void draw(Graphics2D g2);
    /**
     Draws the selection adornment of this item.
     @param g2 the graphics context
     */
    void drawSelection(Graphics2D g2);
    /**
     Sets the selection state of this item.
     @param b true if this item is selected
     */
    void setSelected(boolean b);
    /**
     Gets the selection state of this item.
     @return true if this item is selected
     */
    boolean isSelected();
    /**
     Translates this item by a given amount.
     @param dx the amount to translate in x-direction
     @param dy the amount to translate in y-direction
     */
    void translate(int dx, int dy);
    /**
     Tests whether this item contains a given point.
     @param p a point
     @return true if this item contains p
     */
    boolean contains(Point2D p);

    /**
     * returns Array of grid coordinate of the item in a grid. returns {-1,-1} if it's not on a grid.
     * @return Array of grid coordinate. [0] is x coordinate, [1] is y coordinate.
     */
    int[] indexOf();

    /**
     * sets the coordinate the item in a grid.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    void setIndex(int x, int y);

    /**
     * sets the rotational orientation of the item.
     * @param vertical sets if the item is vertical or not.
     */
    void setVertical(boolean vertical);

    /**
     * checks if the item is vertical.
     * @return return true if vertical.
     */
    boolean isVertical();

    /**
     * a method that sets the position of the square.
     * @param x The x position on the GUI.
     * @param y The y position on the GUI.
     */
    void setPosition(int x ,int y);

    /**
     * Sets the position of the ShipView object into point p.
     * @param p Point object p.
     */
    void setPosition(Point p);


    /**
     * A method that returns the position of the square's high-left corner with a Point datatype.
     * @return A x,y position of the square in GUI into a Point datatype.
     */
    Point getPosition();


    /**
     * sets the default color of the item.
     * @param color default color.
     */
    void setColor(Color color);


    /**
     * A method that returns the color of the square
     * @return The color of the square
     */
    Color getColor();
}

