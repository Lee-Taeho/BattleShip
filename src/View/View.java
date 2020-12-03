package View;

import Message.NewGameMessage;
import Message.PlaceNewShipMessage;

import javax.swing.*;
import java.awt.*;

public class View {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);





        CompositeComponent temp = new CompositeComponent();
        temp.addShip(new ShipView(5,0,10 * SquareShape.getWidth()));
        temp.addShip(new ShipView(4,6* SquareShape.getWidth(), 10 *SquareShape.getWidth()));
        temp.addShip(new ShipView(3,0,12 * SquareShape.getWidth() - 25));
        temp.addShip(new ShipView(3,4 * SquareShape.getWidth(),12 * SquareShape.getWidth()- 25));
        temp.addShip(new ShipView(3,0,14 * SquareShape.getWidth() - 50));
        temp.addShip(new ShipView(2,4 * SquareShape.getWidth(),14 * SquareShape.getWidth() - 50));
        temp.addShip(new ShipView(2,7 * SquareShape.getWidth(),14 * SquareShape.getWidth() - 50));
        temp.addShip(new ShipView(2,8 * SquareShape.getWidth(),12 * SquareShape.getWidth() - 25));

        JButton finished_placing_button = new JButton("Finished placing");
        finished_placing_button.addActionListener( (event) -> {
            for(ShipView ship: temp.getShipList()){
                int[] coordinate = ship.indexOf();
                PlaceNewShipMessage message = new PlaceNewShipMessage();
                message.x = coordinate[0];
                message.y = coordinate[1];
                message.length = ship.getLength();
                message.vertical = ship.isVertical();
                // Add the message into the queue such as queue.put(message);
            }
        }

        );
        JButton shootButton = new JButton("Shoot");

        JTextField textField = new JTextField("Initial text field");


        JPanel buttons = new JPanel();
        buttons.add(finished_placing_button);
        buttons.add(shootButton);


        frame.add(buttons,BorderLayout.NORTH);
        frame.add(temp, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.setSize(1200, 1000);

        System.out.println("Checking if the commit is working");


    }


}
