package Controller;
import View.View;
import Message.*;
import Model.AIPlayerModel;
import Model.PlayerModel;
import Model.ShipModel;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

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
        valves.add(new DoPlacingFinishValve());
        valves.add(new DoFireMissileValve());
        valves.add(new DoShootMissileResultValve());
        valves.add(new DoUpdateStringMessageValve());
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
                if (response != ValveResponse.MISS) {
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
        public ValveResponse execute(Message message);
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
            //view.reset()?
            
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
            //if this is the turn for AI fire onto Player grid
            if(fireMissileMessage.isAI()) {
            	boolean AIHits = player.attackedByAI();
            	if(AIHits) {
            		//update the string
            		//color the hit grid to red
            		return ValveResponse.EXECUTED;
            	}else {
            		//update the miss grid to black
            		//call player attack method to do its turns and consecutive firing
            		AIPlayer.attackedByPlayer(fireMissileMessage.getX(), fireMissileMessage.getY());
            		//update the grid based on the results and update strings
            		return ValveResponse.EXECUTED;
            	}
            //if this is the turn for Player fire onto AI grid
            }else {
            	boolean playerHits = AIPlayer.attackedByPlayer(fireMissileMessage.getX(), fireMissileMessage.getY());
            	if(playerHits) {
            		//update the string
            		//color the hit grid to red
            		return ValveResponse.EXECUTED;
            	}else {
            		//update the miss grid to black
            		//call AI attack method to do its turns and consecutive firing
            		player.attackedByAI();
            		//update the grid based on the results and update strings
            		return ValveResponse.EXECUTED;
            	}
            }
            //return ValveResponse.EXECUTED;
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
                view.setTextField("Ship overlaps with other ships, try again!");
            }
            


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
     
            //If player have not finish placing all the ships, the system print out error message in textfield
            //telling player to finish placing.
            if(player.numOfShips < NUMBER_OF_SHIPS) {
            	//update the string to print error message?
            	//then maybe reset all ShipView and all ShipModel and ask player to place them again?
            }
            
            //after pressing "finish placing" button, this AI.setShip method will randomly place
            //all ships at once
            AIPlayer.setShip();
            
            
            return ValveResponse.EXECUTED;
        }
    }
    
    //Are we going to combine this with the DoFireMissile valve?
    private class DoShootMissileResultValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ShootMissileResultMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of FinishPlaceMessage type
            ShootMissileResultMessage shootMissileResultMessage = (ShootMissileResultMessage) message;
            // actions in Model
            
            // actions in View
            
            
            return ValveResponse.EXECUTED;
        }
    }
    
    //updates string in the textfield
    private class DoUpdateStringMessageValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != UpdateStringMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of FinishPlaceMessage type
            UpdateStringMessage updateStringMessage = (UpdateStringMessage) message;
            // actions in Model
            
            // actions in View
            
            
            return ValveResponse.EXECUTED;
        }
    }
}

