
public class AIPlayerModel {
	private final int MAP_SIZE;
	private int map[][];
	int numOfShots;
	int numOfHits;
	int numOfShips;
	
	public AIPlayerModel() {
		numOfShots = 0;
		numOfHits = 0;
		numOfShips = 0;
		MAP_SIZE = 10;
		map = new int [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
			}
		}
	}
	
	//For now we use this version of setShip only for testing
	public void setShip (Ship s) {
		boolean success = false;									//used to check for obstruction to selected position
		boolean vertical;
		
		while(numOfShips != 5){										//stop when 5 ships have been placed on map
			while(!success) {
				int[] xPos = rand.nextInt(10)        	  			     	   	//set to random position
				int[] yPos = rand.nextInt(10)
				boolean vertical = rand.nextBoolean();				 		//horizontal or vertical

				if (vertical) {
					if (((yPos - 1) + s.getLength()) > 10) { 		 		//if vertical and if ship extends past the board
						success = false;						//retry
					}
					else{									//if ship does not go beyond the board
						for(int i = yPos; i < ((yPos - 1) + s.getLength()); i++){	//from selected yPos to end of Ship size
							if(map[i][xPos] != 0){					//check if there are any ships placed on path
								success = false;				//retry if not empty
							}
						}
					}
				}
				else if(!vertical){
					if ((xPos - 1) + s.getLength() > 10) {	  				//if horizontal and if ship extends past the board
						success = false;						//retry
					}
					else{									//if ship does not go beyond the board
						for(int i = xPos; i < ((xPos - 1) + s.getLength()); i++){	//from selected xPos to end of Ship size
							if(map[yPos][i] != 0){					//check if there are any ships placed on path
								success = false;				//retry if not empty
							}
						}
					}
				}
				else{
					success = true;								//if ship does not go beyond the board and no ships in chosen spot, then select spot
				}

				for(int i = 0; i < s.getLength(); i++) {					//place ship once done checking
					if(vertical){								//if orientation is vertical
						map[i][xPos] = s.getGridDisplay();				//place ship vertically tarting from chosen spot
					}
					if(!vertical){								//if orientation is not vertical
						map[yPos][i] = s.getGridDisplay();				//place ship horizontally starting from chosen spot
					}
				}
				numOfShips++;									//once ship has been placed, add to total number of ships on map
			}
		}
	}
		
	public boolean isGameOver() {
		return numOfHits == 15;
	}
	
	public boolean attackedByPlayer(int xPos, int yPos) {
		if(map[yPos][xPos] == 0) {  //hits empty space -> 0
			map[yPos][xPos] = -1;  //change it to missed shot -> -1
			numOfShots++;
			return false;
			
		}else if(map[yPos][xPos] == 1) {     //hits Ship part -> 1
			map[yPos][xPos] = 2; //change it to Ship was Hit -> 2
			numOfShots++;
			numOfHits++;
			return true;
			
		}else
			return false;
	}
}
