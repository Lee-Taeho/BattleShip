package View;

import java.awt.*;
import java.awt.geom.*;

/**
 A shape that is a part of a scene.
 */
public interface SceneShape
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

    int[] indexOf();

    void setIndex(int x, int y);

    void setVertical(boolean vertical);

    /**
     * a method that sets the position of the square
     * @param x The x position on the GUI
     * @param y The y position on the GUI
     */
    void setPosition(int x ,int y);

    /**
     * Sets the position of the ShipView object into point p
     * @param p Point object p
     */
    void setPosition(Point p);


    /**
     * A method that returns the position of the square's high-left corner with a Point datatype
     * @return A x,y position of the square in GUI into a Point datatype
     */
    Point getPosition();

    boolean isVertical();

    void setColor(Color color);


    /**
     * A method that returns the color of the square
     * @return The color of the square
     */
    Color getColor();
}

