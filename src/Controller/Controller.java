package Controller;
import View.View;
import Message.*;
import Model.AIPlayerModel;
import Model.PlayerModel;
import Model.ShipModel;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Controller of the Battleship Game.
 */
public class Controller {
	private final int NUMBER_OF_SHIPS = 5;
    private BlockingQueue<Message> queue;
    private View view; // Direct reference to view
    //private Model model; // Direct reference to model
    private PlayerModel player;
    private AIPlayerModel AIPlayer;

    
    //private GameInfo gameInfo; // Direct reference to the state of the Game/Application

    private List<Valve> valves = new LinkedList<>();

    public Controller(View view, PlayerModel player, AIPlayerModel AIPlayer, BlockingQueue<Message> queue) {
        this.view = view;
        //this.model = model;
        this.player = player;
        this.AIPlayer = AIPlayer;
        
        this.queue = queue;
        valves.add(new DoPlaceNewShipValve());
        valves.add(new DoRemoveShipValve());
        valves.add(new DoPlacingFinishValve());
        valves.add(new DoFireMissileValve());
        //valves.add(new DoShootMissileResultValve());
        //valves.add(new DoUpdateStringMessageValve());
        valves.add(new DoNewGameValve());
    }

    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take(); // <--- take next message from the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Look for a Valve that can process a message
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS || player.isGameOver() || AIPlayer.isGameOver()) {
                    if(player.isGameOver()) {
                    	view.setTextField("Unfortunately, AI wins.....");
                    }else if(AIPlayer.isGameOver()) {
                    	view.setTextField("Congratulations! Player wins!!!!!");
                    }
                	break;
                }
            }
        }
    }

    
    //Valve interface ================================================
    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        ValveResponse execute(Message message);
    }
    //Valve interface =================================================


    private class DoNewGameValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) { //if it is not a NewGameMessage
                return ValveResponse.MISS;
            }
            // otherwise it means that it is a NewGameMessage message
            NewGameMessage doNewGameMessage = (NewGameMessage) message;
            //AIPlayerModel.se
            // actions in Model
            player.reset();
            AIPlayer.reset();
            
            // actions in View
            view.reset();
            view.addShips();
            System.out.println("Restarting the game");
            
            return ValveResponse.EXECUTED;
        }
    }
    
    //"fire shot" function: by left clicking on the grid
    private class DoFireMissileValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != FireMissileMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of ShootMessage type
            FireMissileMessage fireMissileMessage = (FireMissileMessage) message;
            
            // actions in Model and in View
            //Player fire first
            //If player hits
            if(AIPlayer.attackedByPlayer(fireMissileMessage.getX(),
            		fireMissileMessage.getY())) {
            	System.out.println("Player hits AI ship in [" + fireMissileMessage.getX() + "]["
                    	+ fireMissileMessage.getY() + "], Please continue to fire.");
            	AIPlayer.display();//for testing
            	//update the string
            	view.setTextField("Player hits AI ship in [" + fireMissileMessage.getX() + "]["
            	+ fireMissileMessage.getY() + "], Please continue to fire.");
            	//update the hit grid to red
                view.shootAI(fireMissileMessage.getX(), fireMissileMessage.getY(), true);
           
            //If player misses
            }else {
            	System.out.println("Player missed the shot in [" + fireMissileMessage.getX() + "]["
                        + fireMissileMessage.getY() + "], AI's turn to fire now."); //display in console
            	AIPlayer.display();//for testing
            	view.setTextField("Player missed the shot in [" + fireMissileMessage.getX() + "]["
                    + fireMissileMessage.getY() + "], AI's turn to fire now."); //display in text field
            	//update the miss grid to black
            	view.shootAI(fireMissileMessage.getX(), fireMissileMessage.getY(), false);
            	
            	//===================================================================================
            	
            	//after player misses, then it's AI's turn
            	//If AI hits
            	while(player.attackedByAI()) {
            		System.out.println("AI hits player ship in [" + player.getXPos() 
            		+ "][" + player.getYPos() + "], AI contiune to fire"); //display in console
            		player.display();//for testing
            		view.setTextField("AI hits player ship in [" + player.getXPos() 
            		+ "][" + player.getYPos() + "], AI contiune to fire"); //display in text field
            		//update the hit grid to blue
            		view.shootPlayer(player.getXPos(), player.getYPos(), true);
            	}
            	
            	//If AI misses
            	System.out.println("AI missed the shot in [" + player.getXPos() + "]["
                        + player.getYPos() + "], player's turn to fire now."); //display in console
            	player.display();//for testing
            	view.setTextField("AI missed the shot in [" + player.getXPos() + "]["
                        + player.getYPos() + "], player's turn to fire now."); //display in text field
            	//update the hit grid to black
            	view.shootPlayer(player.getXPos(), player.getYPos(), false);
            }
           return ValveResponse.EXECUTED;
        }
    }
    
    private class DoPlaceNewShipValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != PlaceNewShipMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of PlaceMessage type
            PlaceNewShipMessage placeNewShipMessage = (PlaceNewShipMessage) message;
            
            // actions in Model
            ShipModel s = new ShipModel(placeNewShipMessage.getX(), placeNewShipMessage.getY(), 
            		placeNewShipMessage.getLength(), placeNewShipMessage.isVertical());
            // actions in View
            if(!player.setShip(s)){ // if the ship overlapped with previous ships
                placeNewShipMessage.getShip().resetPosition();
                view.setTextField("Failed, Ship overlaps with other ships, try again!");
            }else if(player.setShip(s)){
            	String vertical = (placeNewShipMessage.isVertical()) ? "bottom" : "right";
            	view.setTextField("Succeed, Ship placed on [" + placeNewShipMessage.getX()
            	+ "][" + placeNewShipMessage.getY() + "], towards " + vertical);
            }
            
            player.display(); //testing for the model

            return ValveResponse.EXECUTED;
        }
    }

    private class DoRemoveShipValve implements Valve{

        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != RemoveShipMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of PlaceMessage type
            RemoveShipMessage removeShipMessage = (RemoveShipMessage) message;

            // actions in Model
            player.removeShip(removeShipMessage.getX(),removeShipMessage.getY(),removeShipMessage.getLength(),removeShipMessage.isVertical());
            // actions in View


            player.display(); //testing for the model

            return ValveResponse.EXECUTED;
        }
    }
    
    //After pressing the "finish & start" button, AI grid randomly place all ships
    private class DoPlacingFinishValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != PlacingFinishedMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of FinishPlaceMessage type
            PlacingFinishedMessage placingFinishMessage = (PlacingFinishedMessage) message;
            
            // actions in Model and View
     
            //If player have not finish placing all the ships, the system reset all
            //ShipView and all ShipModel and ask player to place them all again
            
            if(player.numOfShips < NUMBER_OF_SHIPS) {
            	//update the string to print error message?
                view.setTextField("Incomplete ship placement, please start over again!");
            	//then maybe reset all ShipView and all ShipModel and ask player to place them again?
                player.reset();
                AIPlayer.reset();
                //reset the position of all the ShipView
                view.resetShipPosition();
                return ValveResponse.EXECUTED;
            }
            //Once ship placement complete, this AI.setShip method will randomly place
            //all ships at once
            AIPlayer.setShip();

            //actions in View
            view.enableAIGridClicking();
            view.convertsShipsToGrid();
            view.setTextField("Game start!");


            return ValveResponse.EXECUTED;
        }
    }
}

