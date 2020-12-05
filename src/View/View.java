package View;

import Message.Message;
import Message.PlaceNewShipMessage;

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

        JButton finished_placing_button = new JButton("Finished placing");
        finished_placing_button.addActionListener( (event) -> {
                    for(ShipView ship: battleshipBoard.getShipList()){

                        PlaceNewShipMessage message =
                                new PlaceNewShipMessage(ship);
                        try {
                            queue.put(message); // <--- take next message from the queue
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        // Add the message into the queue such as queue.put(message);
                    }
                }

        );
        JButton shootButton = new JButton("Shoot");

        textField = new JTextField("Initial text field");
        textField.setEnabled(false);


        JPanel buttons = new JPanel();
        buttons.add(finished_placing_button);
        buttons.add(shootButton);


        frame.add(buttons,BorderLayout.NORTH);
        frame.add(battleshipBoard, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.setSize(1200, 1000);


    }

    public static void main(String[] args){

        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        View view = View.init(queue);
    }

    public void reset(){
        battleshipBoard.reset();
    }

    public void addShips(){

        int width = SquareShape.getWidth();
        battleshipBoard.addShip(new ShipView(5,0,11 * width));
        battleshipBoard.addShip(new ShipView(4,6* width, 11 * width));
        battleshipBoard.addShip(new ShipView(3,0,13 * width - 25));
        battleshipBoard.addShip(new ShipView(3,4 * width,13 * width- 25));
        battleshipBoard.addShip(new ShipView(3,0,15 * width - 50));
        battleshipBoard.addShip(new ShipView(2,4 * width,15 * width - 50));
        battleshipBoard.addShip(new ShipView(2,7 * width,15 * width - 50));
        battleshipBoard.addShip(new ShipView(2,8 * width,13 * width - 25));
    }

    public void setTextField(String string){
        textField.setText(string);
    }

    public void dispose(){

    }

    private CompositeComponent battleshipBoard;
    private BlockingQueue<Message> queue;
    JTextField textField;
}
