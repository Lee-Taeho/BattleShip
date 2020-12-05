package View;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



/**
 *  A smallest unit of square shape object that consist the Grid, Ship shapes
 */
public class SquareShape extends SelectableShape{

    /**
     * A constructor for SquareShape
     * @param x The x position on the GUI
     * @param y The y position on the GUI
     */
    public SquareShape (int x, int y ){
        setPosition(x, y);

    }

    /**
     * A  method for drawing default white square
     * @param g2 the graphics context
     */
    @Override
    public void draw(Graphics2D g2){
        Rectangle2D.Double square = new Rectangle2D.Double(x ,y ,WIDTH , WIDTH);
        g2.setColor(Color.BLACK);
        g2.draw(square);
        g2.setColor(c);
        g2.fill(square);
    }

    /**
     * A method for drawing a selected version of square which draws black square
     * @param g2 the graphics context
     */
    @Override
    public void drawSelection(Graphics2D g2){
        setColor(Color.BLACK);
        draw(g2);
    }

    /**
     * A method to check if the square contains the point p
     * @param p a point
     * @return returns true if the square contains the point p
     */
    @Override
    public boolean contains(Point2D p){
        return x <= p.getX() && p.getX() <= x + WIDTH && y <= p.getY() && p.getY() <= y + WIDTH;
    }

    /**
     * A method that translate the position to a designated quantity
     * @param dx the amount to translate in x-direction
     * @param dy the amount to translate in y-direction
     */
    @Override
    public void translate(int dx, int dy ){
        x += dx;
        y += dy;
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

    /**
     * a method that sets the position of the square
     * @param x The x position on the GUI
     * @param y The y position on the GUI
     */
    public void setPosition(int x ,int y){
        this.x = x;
        this.y = y;
    }

    /**
     * A method that returns the position of the square's high-left corner with a Point datatype
     * @return A x,y position of the square in GUI into a Point datatype
     */
    public Point getPosition(){
        return new Point(x,y);
    }

    /**
     * A static method that returns the width of all the squares which is used in initializing the Grid and Ship classes
     * @return Width of the squares
     */
    public static int getWidth(){
        return WIDTH;
    }

    private int x;
    private int y;
    private static int WIDTH = 30;
    private Color c = Color.WHITE;

}