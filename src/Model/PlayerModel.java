package Model;
import java.util.Random;

public class PlayerModel {
	private final int MAP_SIZE = 10;
	private int map[][];
	private boolean checkSetShip[][]; //This is a boolean map only use in setShip method
	int numOfShots;
	int numOfHits;
	int numOfSunk;
	public int numOfShips;
	Random rand = new Random();
	ShipModel s1;
	ShipModel s2;
	ShipModel s3;
	ShipModel s4;
	ShipModel s5;
	
	public PlayerModel() {
		numOfShots = 0;
		numOfHits = 0;
		numOfShips = 0;
		numOfSunk = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		checkSetShip = new boolean[MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
			}
		}
	}
	
	//display method only for testing
	public void display() {
		int colNum = 0;
		System.out.print("     ");
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
		System.out.println("numOfShots: " + numOfShots + ", " + "numOfHits: " + numOfHits + ", " + "numOfShips: " + numOfShips);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
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
	
	public boolean isGameOver() {
		return numOfSunk == 5;
	}
	
	public boolean attackedByAI() {
		
		//This part is for the AI consecutive firing behavior
		
		//==================================================
		
		int xPos = rand.nextInt(10);
		int yPos = rand.nextInt(10);
		if(map[yPos][xPos] == 0) { //hits empty space -> 0
			map[yPos][xPos] = -1;  //change it to missed shot -> -1
			numOfShots++;
			return false;
			
		}else if(map[yPos][xPos] == 2) { //hits length-2 Ship part -> 2
			map[yPos][xPos] = -2; //change it to length-2 ship part that's been hit -> -2
			s1.registerHit(xPos, yPos);
			numOfHits++;
			if(s1.checkIfSunk()) {
				numOfSunk++;
				numOfShips--;
			}
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 3) { //hits length-2 Ship part -> 3
			map[yPos][xPos] = -3; //change it to length-2 ship part that's been hit -> -3
			
			if(s2.contains(xPos, yPos)) {     //since we have two length 3 ship, one is s2, one is s3
				s2.registerHit(xPos, yPos);   //we need to call ShipModel.contains() to know which one 
				numOfHits++;                  //it is
				if(s2.checkIfSunk()) {
					numOfSunk++;
					numOfShips--;
				}
			}else if(s3.contains(xPos, yPos)) {
				s3.registerHit(xPos, yPos);
				numOfHits++;
				if(s3.checkIfSunk()) {
					numOfSunk++;
					numOfShips--;
				}
			}
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 4) { //hits length-2 Ship part -> 4
			map[yPos][xPos] = -4; //change it to length-2 ship part that's been hit -> -4
			s4.registerHit(xPos, yPos);
			numOfHits++;
			if(s4.checkIfSunk()) {
				numOfSunk++;
				numOfShips--;
			}
			numOfShots++;
			numOfHits++;
			return true;
			
		}else if(map[yPos][xPos] == 5) { //hits length-2 Ship part -> 5
			map[yPos][xPos] = -5; //change it to length-2 ship part that's been hit -> -5
			s5.registerHit(xPos, yPos);
			numOfHits++;
			if(s5.checkIfSunk()) {
				numOfSunk++;
				numOfShips--;
			}
			numOfShots++;
			numOfHits++;
			return true;
			
		}else { //all duplicated firing -> -1, -2, -3, -4, -5
			return false; //return false
		}
	}
	
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
	
	//This part just updates the player grid when we remove the ship
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
}
