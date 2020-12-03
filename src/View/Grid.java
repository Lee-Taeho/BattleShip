package View;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class Grid implements Iterable {

    public Grid(){

        this(10,10, 0, 0);


    }

    public Grid(int rows, int columns, int x, int y){

        ROWS = rows;
        COLUMNS = columns;
        initX = x;
        initY = y;
        squares = new SquareShape[ROWS][COLUMNS];
        SquareShape square;
        for(int i = 0; i < ROWS ; i ++){
            for(int j = 0; j < COLUMNS; j ++){
                square = new SquareShape(j * SquareShape.getWidth() +initX,i * SquareShape.getWidth() + initY);
                squares[i][j] = square;
            }
        }

    }

    public SquareShape getSquare(int i, int j){

        return squares[i][j];
    }

    public int[] indexOf(SquareShape s){

        for(int i = 0; i < ROWS ; i ++){
            for(int j = 0; j < COLUMNS; j ++){
                if(s == squares[i][j]){
                    return new int[]{i, j};
                }
            }
        }
        System.out.println("method getRowColumns() did not found the square from the grid");
        return null;

    }


    public void draw(Graphics2D g2){

        for (SquareShape[] squareArray : squares){
            for(SquareShape s : squareArray){
                s.draw(g2);
            }
        }
    }

    public boolean contains(Point2D p){
        return initX <= p.getX() && p.getX() <= initX + COLUMNS * SquareShape.getWidth()
                && initY <= p.getY() && p.getY() <= initY + ROWS * SquareShape.getWidth();
    }


@Override
public Iterator iterator() {
    class GridIterator implements Iterator{

        @Override
        public boolean hasNext() {
            return (r + 1) * (c + 1) <= ROWS * COLUMNS;
        }

        @Override
        public Object next() {
            if (ROWS > r){
                if (COLUMNS <= c) {
                    r += 1;
                    c = 0;
                }
//                System.out.println("[" + r + "][" + c + "]");
                return squares[r][c++];
            }
            throw new IndexOutOfBoundsException();
        }
        private int r =0;
        private int c = 0;
    }

    return new GridIterator();
}

    private SquareShape[][] squares;
    private int ROWS ;
    private int COLUMNS ;
    private int initX;
    private int initY;



}
