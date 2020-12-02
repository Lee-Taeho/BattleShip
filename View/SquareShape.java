package View;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SquareShape extends SelectableShape{

    public SquareShape (int x, int y ){
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw(Graphics2D g2){
        Rectangle2D.Double square = new Rectangle2D.Double(x ,y ,WIDTH , WIDTH);
        g2.setColor(Color.BLACK);
        g2.draw(square);
        g2.setColor(c);
        g2.fill(square);
    }

    @Override
    public void drawSelection(Graphics2D g2){
        setColor(Color.BLACK);
        draw(g2);
    }

    @Override
    public boolean contains(Point2D p){
        return x <= p.getX() && p.getX() <= x + WIDTH && y <= p.getY() && p.getY() <= y + WIDTH;
    }


    @Override
    public void translate(int dx, int dy ){
        x += dx;
        y += dy;
    }

    public void setColor(Color color){
        c = color;
    }

    public Color getColor(){
        return c;
    }

    public void setPosition(int x ,int y){
        this.x = x;
        this.y = y;
    }

    public Point getPosition(){
        return new Point(x,y);
    }

    public static int getWidth(){
        return WIDTH;
    }

    private int x;
    private int y;
    private static int WIDTH = 50;
    private Color c = Color.WHITE;

}