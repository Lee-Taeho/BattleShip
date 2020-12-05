package View;

import Message.Message;
import Message.PlaceNewShipMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class CompositeComponent extends JComponent{


    public CompositeComponent (BlockingQueue<Message> q){
        queue = q;

        gridCoordinate = new SquareShape[ROWS][COLUMNS];

        playerGrid = new Grid(ROWS,COLUMNS,0,0);
        AIGrid = new Grid(ROWS,COLUMNS,(ROWS + 2) * SquareShape.getWidth(),0);

        int r = 0, c =0;
        for(Object s: playerGrid){
            if(r >= ROWS + 1){
                System.out.println("GridCoordinate assignment out of bound!!!");
            }
            gridCoordinate[r][c++] = (SquareShape) s;
            if (ROWS > r){
                if (COLUMNS <= c) {
                    r += 1;
                    c = 0;
                }
            }


        }

        ships = new ArrayList<>();


        addMouseMotionListener(new GridMouseMotionListener());
//        addMouseListener(new GridMouseClickListener());
        addMouseListener(new ShipMouseClickListener());
        addMouseMotionListener(new ShipMouseMotionListener());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AIGrid.draw(g2);
        playerGrid.draw(g2);
        for(ShipView s: ships){
            s.draw(g2);
        }

    }

    public void addShip(ShipView ship){

        ships.add(ship);
    }

    public ArrayList<ShipView> getShipList(){
        return ships;
    }


    public void reset(){
        gridCoordinate = new SquareShape[ROWS][COLUMNS];

        playerGrid = new Grid(ROWS,COLUMNS,0,0);
        AIGrid = new Grid(ROWS,COLUMNS,(ROWS + 2) * SquareShape.getWidth(),0);

        int r = 0, c =0;
        for(Object s: playerGrid){
            if(r >= ROWS + 1){
                System.out.println("GridCoordinate assignment out of bound!!!");
            }
            gridCoordinate[r][c++] = (SquareShape) s;
            if (ROWS > r){
                if (COLUMNS <= c) {
                    r += 1;
                    c = 0;
                }
            }


        }

        ships = new ArrayList<>();


        addMouseMotionListener(new GridMouseMotionListener());
//        addMouseListener(new GridMouseClickListener());
        addMouseListener(new ShipMouseClickListener());
        addMouseMotionListener(new ShipMouseMotionListener());

    }

    private class GridMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent event){
            Point mousePoint = event.getPoint();

            SquareShape s;
            for (Object temp : playerGrid){
                s = (SquareShape) temp;
                if(s.getColor() != Color.BLACK && s.getColor() != Color.RED) {
                    if (s.contains(mousePoint)) {
                        s.setColor(Color.LIGHT_GRAY);
                    } else {
                        s.setColor(Color.WHITE);
                    }
                }
            }
            repaint();
        }
    }

    private class GridMouseClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            mousePoint = event.getPoint();
            SquareShape s;
            for (Object temp : playerGrid){
                s = (SquareShape) temp;
                if(s.contains(mousePoint)){
                    if(!s.isSelected()){
                        s.setSelected(true);
                        s.setColor(Color.BLACK);
                    }else{
                        s.setSelected(false);
                        s.setColor(Color.WHITE);
                    }
                }
            }
            repaint();
        }
    }

    private class ShipMouseClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event)
        {

            mousePoint = event.getPoint();

            for (ShipView s: ships){
                if(s.contains(event.getPoint())){
                    if(event.getButton() == 1){
                        s.setSelected(true);
                        s.setPosition(event.getX() - SquareShape.getWidth() / 2, event.getY() -SquareShape.getWidth() / 2);
                    }else if(event.getButton() == 3){
                        if(s.isSelected()){
                            s.rotate();
                        }
                    }
                }

            }
            repaint();


        }


        @Override
        public void mouseReleased(MouseEvent event){
            if(event.getButton() == 1){
                Point p = event.getPoint();
                for(ShipView ship : ships){
                    if(ship.isSelected()){
                        if(!playerGrid.contains(event.getPoint())){
                            ship.resetPosition();
                            System.out.println("ship placing failed: ship is outside the grid");
                        }else {
                            for (int i = 0; i < ROWS; i++){
                                for (int j = 0; j < COLUMNS; j++){
                                    SquareShape square = gridCoordinate[i][j];
                                    if(square.contains(p))
                                         if( ((ROWS - i) >= ship.getLength() && ship.isVertical())
                                            || ( (COLUMNS - j) >= ship.getLength()) && !ship.isVertical() ){

                                                 int[] index = playerGrid.indexOf(square);
                                                 ship.setIndex(index[0], index[1]);
                                                 ship.setPosition(square.getPosition());
                                                 ship.setSelected(false);
                                                 System.out.println("ship placing SUCCEED: ["+ index[1] +"]["+index[0]+"]");

                                                 PlaceNewShipMessage message =
                                                         new PlaceNewShipMessage(ship);
                                                 try {
                                                     queue.put(message);
                                                 } catch (InterruptedException e) {
                                                     e.printStackTrace();
                                                 }

                                        }else{
                                             ship.resetPosition();
                                             System.out.println("ship placing failed: size doesn't fit");
                                         }
                                }
                            }
//                            for (View.SquareShape[] temp : gridCoordinate){
//                                for(View.SquareShape square : temp){
//                                    if(square.contains(p)){
//                                        s.setPosition(square.getPosition());
//                                        s.setSelected(false);
//                                    }
//
//                                }
//                            }
                        }

                    }
                }

                repaint();
            }

        }
    }

    private class ShipMouseMotionListener extends MouseMotionAdapter{

        @Override
        public void mouseDragged(MouseEvent event){
            Point lastMousePoint = mousePoint;
            mousePoint = event.getPoint();
            for(ShipView s : ships){
                if (s.isSelected())
                {
                    double dx = mousePoint.getX() - lastMousePoint.getX();
                    double dy = mousePoint.getY() - lastMousePoint.getY();
                    s.translate((int) dx, (int) dy);
                }

            }


            repaint();
        }
    }

// <Failed method. Does not work>
//    private boolean overlaps(int x, int y, int length, boolean vertical){
//        int[] coordinates;
//        int len;
//        for(Ship ship : ships){
//            coordinates = ship.indexOf();
//            len = ship.getLength() -1 ;
//
//            if(coordinates[0] != -1 && coordinates[1] != -1){
//                if(ship.isVertical()){
//                    if(vertical){
//                        for (int i = 0; i < length; i++){
//                            if(x == coordinates[0] && coordinates[1] <= (y + i) && (y + i) <= (coordinates[1] + len) ){
//                                return true;
//                            }
//                        }
//                    }else{
//                        for (int i = 0; i < length; i++){
//                            if((x + i) == coordinates[0] && coordinates[1] <= y && y <= (coordinates[1] + len) ){
//                                return true;
//                            }
//                        }
//                    }
//
//                }else{
//                    if(vertical){
//                        for (int i = 0; i < length; i++){
//                            if((y + i) == coordinates[1] && coordinates[0] <= x && x <= (coordinates[0] + len) ){
//                                return true;
//                            }
//                        }
//                    }else{
//                        for (int i = 0; i < length; i++){
//                            if( y  == coordinates[1] && coordinates[0] <= (x + i) && (x + i) <= (coordinates[0] + len) ){
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }



    private SquareShape[][] gridCoordinate;
    private Point mousePoint;
    private Grid playerGrid;
    private Grid AIGrid;
    private ArrayList<ShipView> ships;
    private BlockingQueue<Message> queue;
    private int ROWS = 10;
    private int COLUMNS = 10;

}
