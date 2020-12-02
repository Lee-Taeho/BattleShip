package View;

import Message.NewGameMessage;

import javax.swing.*;
import java.awt.*;

public class View {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        JButton startButton = new JButton("Start");
//        startButton.addActionListener( (event) ->);
        JButton shootButton = new JButton("Shoot");

        JTextField textField = new JTextField("Initial text field");


        JPanel buttons = new JPanel();
        buttons.add(startButton);
        buttons.add(shootButton);


        CompositeComponent temp = new CompositeComponent();
        temp.addShip(new Ship(5,0,10 * SquareShape.getWidth()));
        temp.addShip(new Ship(4,6* SquareShape.getWidth(), 10 *SquareShape.getWidth()));
        temp.addShip(new Ship(3,0,12 * SquareShape.getWidth() - 25));
        temp.addShip(new Ship(3,4 * SquareShape.getWidth(),12 * SquareShape.getWidth()- 25));
        temp.addShip(new Ship(3,0,14 * SquareShape.getWidth() - 50));
        temp.addShip(new Ship(2,4 * SquareShape.getWidth(),14 * SquareShape.getWidth() - 50));
        temp.addShip(new Ship(2,7 * SquareShape.getWidth(),14 * SquareShape.getWidth() - 50));
        temp.addShip(new Ship(2,8 * SquareShape.getWidth(),12 * SquareShape.getWidth() - 25));


        frame.add(buttons,BorderLayout.NORTH);
        frame.add(temp, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.setSize(1200, 1000);



    }


}
