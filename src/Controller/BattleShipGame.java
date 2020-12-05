package Controller;

import Controller.Controller;
import Message.Message;
import Model.AIPlayerModel;
import Model.PlayerModel;
import View.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BattleShipGame {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static PlayerModel p1;
    private static AIPlayerModel p2;

    public static void main(String[] args) {
    	//creates View
        view = View.init(queue);
        //creates Model
        p1 = new PlayerModel();
        p2 = new AIPlayerModel();
        Controller controller = new Controller(view, p1, p2, queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}

