package View;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class ShipView extends SelectableShape{

    public ShipView(int length){
        squares = new ArrayList<>();
        this.length = length;
        vertical = false;
        for(int i = 0; i < length; i++)
         squares.add(new SquareShape(i * SquareShape.getWidth(), 0));
        initX = 0;
        initY = 0;


    }

    public ShipView(int length, int x, int y){
        this(length);
        initX = x;
        initY = y;
        setPosition(x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
        for (SquareShape s: squares){
            s.setColor(Color.GREEN);
            s.draw(g2);
        }
    }

    @Override
    public void drawSelection(Graphics2D g2) {}

    @Override
    public void translate(int dx, int dy) {
        for(SquareShape s: squares){
            s.translate(dx, dy);
        }
    }

    @Override
    public boolean contains(Point2D p) {

        for (SquareShape s: squares){
            if(s.contains(p))
                return true;
        }
        return false;
    }

    public void setPosition(int x, int y){
        if(isVertical()){
            for(int i = 0; i < length; i++)
                squares.get(i).setPosition(x, y + i * SquareShape.getWidth());
        }else{
            for(int i = 0; i < length; i++)
                squares.get(i).setPosition(x + i * SquareShape.getWidth(), y);
        }

    }

    public void setPosition(Point p){

        setPosition((int) p.getX(),(int) p.getY());
    }

    public void resetPosition(){
        xCoordinate = -1;
        yCoordinate = -1;
        setPosition(getInitPoint());
        setSelected(false);
        if(vertical)
            rotate();
    }

    public Point getInitPoint(){

        return new Point(initX, initY);
    }

    public int getLength(){
        return length;
    }




    public boolean isVertical(){

        return vertical;
    }



    public void rotate(){

        int sign = vertical? 1 : -1;
        int w = SquareShape.getWidth();
        for(int i = 0; i < length; i++){
            squares.get(i).translate(sign * i * w, -sign * i * w);
        }
        setVertical(!isVertical());
    }


    public void setVertical(boolean vertical) {

        this.vertical = vertical;
    }

//    public int indexOf(SquareShape s){
//
//        return squares.indexOf(s);
//    }

    public int[] indexOf(){

        return new int[] {xCoordinate,yCoordinate};
    }

    public void setIndex(int row, int column){
        xCoordinate = row;
        yCoordinate = column;
    }

    public SquareShape getSquare(int index){

        return squares.get(index);
    }

    private int initX;
    private int initY;
    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private int length;
    private boolean vertical;
    private ArrayList<SquareShape> squares;
}
