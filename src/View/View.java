package View;

import Message.*;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class View {

    public static View init(BlockingQueue<Message> q) {
        return new View(q);
    }

    private View(BlockingQueue<Message> q){

        queue = q;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        battleshipBoard = new CompositeComponent(queue);
        addShips();

        JButton finishedPlacingButton = new JButton("Finished placing");
        finishedPlacingButton.addActionListener((event) ->{
            try {
                queue.put(new PlacingFinishedMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            battleshipBoard.convertShipsToGridColors();
        });

        JButton startNewGameButton = new JButton("Start New Game");
        startNewGameButton.addActionListener((event) -> {
            try {
                queue.put(new NewGameMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        textField = new JTextField("<Battleship>: please place all the ships in the left grid to start the game. right click to rotate the ships.");
        textField.setEnabled(false);


        JPanel buttons = new JPanel();
        buttons.add(finishedPlacingButton);
        buttons.add(startNewGameButton);


        frame.add(buttons,BorderLayout.NORTH);
        frame.add(battleshipBoard, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.setSize(800, 600);


    }

    public static void main(String[] args){

        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        View view = View.init(queue);
    }

    public void reset(){
        battleshipBoard.reset();
    }

    public void shootPlayer(int x, int y, boolean hit){
        battleshipBoard.shootPlayer(x, y, hit);
    }

    public void shootAI(int x, int y, boolean hit){
        battleshipBoard.shootAI(x, y, hit);
    }

    public void addShips(){

        int width = SquareShape.getWidth();
        battleshipBoard.addShip(new ShipView(5,0,11 * width));
        battleshipBoard.addShip(new ShipView(4,6* width, 11 * width));
        battleshipBoard.addShip(new ShipView(3,0,13 * width - 25));
        battleshipBoard.addShip(new ShipView(3,4 * width,13 * width- 25));
        battleshipBoard.addShip(new ShipView(2,8 * width,13 * width - 25));
    }

    public void setTextField(String string){
        textField.setText(string);
    }

    public void enableAIGridClicking(){
        battleshipBoard.enableClicking();
    }

    public void disableAIGridClicking(){
        battleshipBoard.disableClicking();
    }

    public void resetShipPosition(){
        for(ShipView ship : battleshipBoard.getShipList()){
            ship.resetPosition();
        }
    }

    public void dispose(){

    }

    public void convertsShipsToGrid(){
        battleshipBoard.convertShipsToGridColors();
    }

    private CompositeComponent battleshipBoard;
    private BlockingQueue<Message> queue;
    JTextField textField;
}
