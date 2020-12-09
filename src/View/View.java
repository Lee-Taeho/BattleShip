package View;

import Message.*;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class that directly interact with the Controller class
 * @author Taeho Lee
 */
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


    /**
     * Method for resetting the View.
     */
    public void reset(){
        battleshipBoard.reset();
    }

    /**
     * Method for shooting player.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param hit whether the missile is hit or not.
     */
    public void shootPlayer(int x, int y, boolean hit){
        battleshipBoard.shootPlayer(x, y, hit);
    }

    /**
     * Method for shooting AI.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param hit whether the missile is hit or not.
     */
    public void shootAI(int x, int y, boolean hit){
        battleshipBoard.shootAI(x, y, hit);
    }

    /**
     * Method for adding 5 ShipView objects for the game.
     */
    public void addShips(){

        int width = SquareShape.getWidth();
        battleshipBoard.addShip(new ShipView(5,0,11 * width));
        battleshipBoard.addShip(new ShipView(4,6* width, 11 * width));
        battleshipBoard.addShip(new ShipView(3,0,13 * width - 25));
        battleshipBoard.addShip(new ShipView(3,4 * width,13 * width- 25));
        battleshipBoard.addShip(new ShipView(2,8 * width,13 * width - 25));
    }

    /**
     * Method that changes the text in the textField in the View.
     * @param string a substitute String that will be inserted into textField.
     */
    public void setTextField(String string){
        textField.setText(string);
    }

    /**
     * Method that enables clicking the AI grid.
     */
    public void enableAIGridClicking(){
        battleshipBoard.enableClicking();
    }

    /**
     * Method that disables clicking the AI grid.
     */
    public void disableAIGridClicking(){
        battleshipBoard.disableClicking();
    }

    /**
     * Method that sets all the position of the ships to their initial position.
     */
    public void resetShipPosition(){
        for(ShipView ship : battleshipBoard.getShipList()){
            ship.resetPosition();
        }
    }

    /**
     * A Method that dispose the View attributes. not implemented as it wasn't needed.
     */
    public void dispose(){

    }

    /**
     * Method that converts all the ships into green squares inside the grid. used after finished placing.
     */
    public void convertsShipsToGrid(){
        battleshipBoard.convertShipsToGridColors();
    }

    private CompositeComponent battleshipBoard;
    private BlockingQueue<Message> queue;
    JTextField textField;
}
