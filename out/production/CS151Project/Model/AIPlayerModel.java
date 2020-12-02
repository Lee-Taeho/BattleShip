
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
		int[] xPos = s.getxPos();
		int[] yPos = s.getyPos();
		for(int i = 0; i < s.getLength(); i++) {
			map[yPos[i]][xPos[i]] = s.getGridDisplay();
		}
		numOfShips++;
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
