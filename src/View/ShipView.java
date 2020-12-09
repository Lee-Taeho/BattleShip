package View;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * A Class for Ship objects in the View
 * @author Taeho Lee
 */
public class ShipView extends SelectableShape{

    /**
     * A constructor of the ShipView class. initial position set to (0,0) in GUI.
     * @param length number consecutive Squares in the object.
     */
    public ShipView(int length){
        setColor(Color.GREEN);
        squares = new ArrayList<>();
        this.length = length;
        setVertical(false);
        SquareShape square;
        for(int i = 0; i < length; i++){
            square = new SquareShape(i * SquareShape.getWidth(), 0);
            square.setIndex(indexOf()[0],indexOf()[1]);
            squares.add(square);
        }


        initX = 0;
        initY = 0;


    }

    /**
     * A constructor of the ShipView class.
     * @param length number consecutive Squares in the object.
     * @param x initial X position of the ShipView class.
     * @param y initial Y position of the ShipView class.
     */
    public ShipView(int length, int x, int y){
        this(length);
        initX = x;
        initY = y;
        setPosition(x, y);
    }

    /**
     * A method for drawing the ShipView object
     * @param g2 the graphics context
     */
    @Override
    public void draw(Graphics2D g2) {
        for (SceneShape s: squares){
            s.setColor(getColor());
            s.draw(g2);
        }
    }

    /**
     * A method for drawing selected ShipView object. does nothing in this class
     * @param g2 the graphics context
     */
    @Override
    public void drawSelection(Graphics2D g2) {}

    /**
     * A method to translate the ShipView Object by parameters.
     * @param dx the amount to translate in x-direction
     * @param dy the amount to translate in y-direction
     */
    @Override
    public void translate(int dx, int dy) {
        for(SceneShape s: squares){
            s.translate(dx, dy);
        }
    }

    /**
     * A method to check if the ShipView object contains the point p
     * @param p a point
     * @return returns true if the square contains the point p
     */
    @Override
    public boolean contains(Point2D p) {

        for (SceneShape s: squares){
            if(s.contains(p))
                return true;
        }
        return false;
    }

    /**
     * A method that sets the position of the ShipView object.
     * @param x X position.
     * @param y Y position.
     */
    @Override
    public void setPosition(int x, int y){
        if(isVertical()){
            for(int i = 0; i < length; i++)
                squares.get(i).setPosition(x, y + i * SquareShape.getWidth());
        }else{
            for(int i = 0; i < length; i++)
                squares.get(i).setPosition(x + i * SquareShape.getWidth(), y);
        }

    }

    /**
     * returns the position of the head square in ShipView.
     * @return highe-left corner Point of the head SquareShape.
     */
    @Override
    public Point getPosition(){
        return squares.get(0).getPosition();
    }

    /**
     * Sets the position of the ShipView object into point p
     * @param p Point object p
     */
    public void setPosition(Point p){

        setPosition((int) p.getX(),(int) p.getY());
    }

    /**
     * Resets the position of the ShipView object in to its initial position when constructed.
     */
    public void resetPosition(){
        setIndex(-1,-1);
        setPosition(getInitPoint());
        setSelected(false);
        if(isVertical())
            rotate();
    }

    /**
     * Returns the initial point of the ShipView object when constructed
     * @return initial point in a Point object.
     */
    public Point getInitPoint(){

        return new Point(initX, initY);
    }

    /**
     * Returns the length of the ShipView object.
     * @return length of the ship.
     */
    public int getLength(){
        return length;
    }



    /**
     * Rotates the ship from vertical to horizontal and vice versa.
     */
    public void rotate(){

        int sign = isVertical()? 1 : -1;
        int w = SquareShape.getWidth();
        for(int i = 0; i < length; i++){
            squares.get(i).setVertical(!isVertical());
            squares.get(i).translate(sign * i * w, -sign * i * w);
        }
        setVertical(!isVertical());
    }



    private int initX;
    private int initY;
    private int length;
    private ArrayList<SceneShape> squares;
}
