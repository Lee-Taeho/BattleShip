package Model;
import java.util.Random;

/** Represents a player.
 * @author JiaJun Dai
 * @author Kevin Lopez
*/
public class PlayerModel {
	private final int MAP_SIZE = 10;
	private int map[][];
	private boolean checkSetShip[][]; //This is a boolean map only use in setShip method
	int numOfShots;
	int numOfHits;
	int numOfSunk;
	public int numOfShips;
	public int xPos;
	public int yPos;
	Random rand = new Random();
	ShipModel s1;
	ShipModel s2;
	ShipModel s3;
	ShipModel s4;
	ShipModel s5;
	
	/**
	 * Constructor for class PlayerModel
	 */
	public PlayerModel() {
		numOfShots = 0;
		numOfHits = 0;
		numOfShips = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		checkSetShip = new boolean[MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
			}
		}
	}
	
	/**
	* Display method to display player grid in 2d-array for testing
	* @param 
	* @return 
	*/
	public void display() {
		int colNum = 0;
		System.out.print("play ");
		for(;colNum < MAP_SIZE; colNum++) {
			System.out.print(colNum + "   ");
		}
		System.out.println();
		System.out.println();
		for(int x = 0; x < MAP_SIZE; x++) {
			System.out.print(x + "    ");
			for(int y = 0; y < MAP_SIZE; y++) {
				if(map[x][y] == 0) {
					System.out.print(".   "); //empty space
				}else if(map[x][y] == -1) {
					System.out.print("X   "); //missed shot
				}else if(map[x][y] == 2) {
					System.out.print("S   "); //length-2 ship part that has not been hit
				}else if(map[x][y] == 3) {
					System.out.print("D   "); //length-3 ship part that has not been hit
				}else if(map[x][y] == 4) {
					System.out.print("C   "); //length-4 ship part that has not been hit
				}else if(map[x][y] == 5) {
					System.out.print("A   "); //length-5 ship part that has not been hit
				}else if(map[x][y] == -2) {
					System.out.print("s   "); //length-2 ship part that's been hit
				}else if(map[x][y] == -3) {
					System.out.print("d   "); //length-3 ship part that's been hit
				}else if(map[x][y] == -4) {
					System.out.print("c   "); //length-4 ship part that's been hit
				}else if(map[x][y] == -5) {
					System.out.print("a   "); //length-5 ship part that's been hit
				}

			}
			System.out.println();
			System.out.println();
		}
		System.out.println("numOfShots: " + numOfShots + ", " + "numOfHits: " + numOfHits);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	/**
	* This set ship method will generate one ship position
	* @param s ship that is added into PlayerModel
	* @return true if ship does not overlap with another ship on grid
	* @return false if ship overlaps with another ship on grid
	*/
	public boolean setShip (ShipModel s) {
		int[] xPos = s.getXPos();
		int[] yPos = s.getYPos();
		
		if(setShipHelper(xPos, yPos, s.getLength())) {
			//if ship part is not overlapping, we then set this ship
			for(int i = 0; i < s.getLength(); i++) {
				map[yPos[i]][xPos[i]] = s.getGridDisplay();
				checkSetShip[yPos[i]][xPos[i]] = true;
			}
			numOfShips++;
			return true;
		}else {
			return false;
		}
	}
	
	/**
	* check the player ship part position one by one to see
	* if it overlaps with another ship, and also check if
	* the xPos[i] and yPos[i] is out of bound
	* @param xPos[] an array storing all x coordinates of the ship
	* @param yPos[] an array storing all y coordinates of the ship
	* @param length length of the ship
	* @return true - if overlaps or out of bound return true
	* @return false - if no overlap or no out of bound return false
	*/
	private boolean setShipHelper (int xPos[], int yPos[], int length) {
		//first check the ship part position one by one to see
		//if it overlaps with another ship, if yes return false
		for(int i = 0; i < length; i++) {
			if(checkSetShip[yPos[i]][xPos[i]]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	* check if player is game over. numOfHits is the number of player ship
	* parts that has been hit.
	* @param 
	* @return true - numOfHits is equal to 17
	* @return false - numOfHits is not equal(below) to 17
	*/
	public boolean isGameOver() {
		if(numOfHits == 17) {
			System.out.println("Unfortunately, AI wins.....");
		}
		return numOfHits == 17;
	}
	
	/**
	* AI fire shot to player grid.
	* @param
	* @return true - if AI hit a player ship part or if AI misses
	* @return false - if AI fires at a player ship part that's been hit or a hit spot (duplicated firing)
	*/
	public boolean attackedByAI() {
		
		//This part is for the AI consecutive firing behavior
		
		//==================================================
		xPos = rand.nextInt(10);
		yPos = rand.nextInt(10);
		while(map[yPos][xPos] == -1 || map[yPos][xPos] == -2 || map[yPos][xPos] == -3 
				|| map[yPos][xPos] == -4 || map[yPos][xPos] == -5) { // no duplicated firing
			xPos = rand.nextInt(10);
			yPos = rand.nextInt(10);
		}
		if(map[yPos][xPos] == 0) { //hits empty space -> 0
			map[yPos][xPos] = -1;  //change it to missed shot -> -1
			numOfShots++;
			return false;
			
		}else if(map[yPos][xPos] == 2) { //hits length-2 Ship part -> 2
			map[yPos][xPos] = -2; //change it to length-2 ship part that's been hit -> -2
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 3) { //hits length-2 Ship part -> 3
			map[yPos][xPos] = -3; //change it to length-2 ship part that's been hit -> -3
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 4) { //hits length-2 Ship part -> 4
			map[yPos][xPos] = -4; //change it to length-2 ship part that's been hit -> -4
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 5) { //hits length-2 Ship part -> 5
			map[yPos][xPos] = -5; //change it to length-2 ship part that's been hit -> -5
			numOfShots++;
			numOfHits++;
			return true;
			
		}else { //should never get here
			return false; 
		}
	}
	
	/**
	* Resets player grid and all variables
	* @param 
	* @return
	*/
	public void reset() {
		numOfShots = 0;
		numOfHits = 0;
		numOfShips = 0;
		numOfSunk = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
				 
			}
		}
	}
	
	/**
	 * Remove one ship from the player grid
	 * @param x x position of the ship
	 * @param y y position of the ship 
	 * @param length length of the ship
	 * @param vertical ship placement is vertical or horizontal
	 * @return
	 */
	public void removeShip(int x, int y, int length, boolean vertical) {
		int[] xPos = new int[length];
		int[] yPos = new int[length];
		if(map[y][x] == 2) {
			for(int i = 0; i < length; i++) {
				xPos[i] = x;
				yPos[i] = y;
				map[y][x] = 0;
				checkSetShip[y][x] = false;
				if(vertical) {
					y++;
				}else {
					x++;
				}
					
			}
			numOfShips--;
		}else if(map[y][x] == 3) { //does not need to call ship.contains?
			for(int i = 0; i < length; i++) {
				xPos[i] = x;
				yPos[i] = y;
				map[y][x] = 0;
				checkSetShip[y][x] = false;
				if(vertical) {
					y++;
				}else {
					x++;
				}
					
			}
			numOfShips--;
		}else if(map[y][x] == 4) {
			for(int i = 0; i < length; i++) {
				xPos[i] = x;
				yPos[i] = y;
				map[y][x] = 0;
				checkSetShip[y][x] = false;
				if(vertical) {
					y++;
				}else {
					x++;
				}
					
			}
			numOfShips--;
		}else if(map[y][x] == 5) {
			for(int i = 0; i < length; i++) {
				xPos[i] = x;
				yPos[i] = y;
				map[y][x] = 0;
				checkSetShip[y][x] = false;
				if(vertical) {
					y++;
				}else {
					x++;
				}
					
			}
			numOfShips--;
		}
	}
	/**
	 * gets the xPos of the player shot position
	 * @return xPos xPos of the player shot
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * gets the yPos of the player shot position
	 * @return yPos yPos of the player shot
	 */
	public int getYPos() {
		return yPos;
	}
}