package Model;

import java.util.Random;

public class AIPlayer {
	private final int MAP_SIZE;
	private int map[][];
	public int numOfSunk;
	public int numOfShots;
	public int numOfHits;
	
	Carrier AIa;
	Cruiser AIc;
	Destroyer AId1;
	Destroyer AId2;
	Destroyer AId3;
	Submarine AIs1;
	Submarine AIs2;
	Submarine AIs3;
	
	//constructor
	AIPlayer() {
		MAP_SIZE = 10;
		numOfSunk = 0;
		numOfShots = 0;
		numOfHits = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
			}
		}
		/**
		 * we need to create a default constructor in all ship classes and also modify the setShip method in this class
		 * to make AIPlayer be able to generate random position for its ships.
		 */
		//=================== we will modify it later once everything is working ===========================
		AIa = new Carrier(3, 4, true);
		AIc = new Cruiser(5, 4, false);
		AId1 = new Destroyer(5, 0, true);
		AId2 = new Destroyer(7, 0, true);
		AId3 = new Destroyer(9, 0, true);
		AIs1 = new Submarine(5, 6, true);
		AIs2 = new Submarine(7, 6, true);
		AIs3 = new Submarine(9, 6, true);
		
		setShip(AIa);
		setShip(AIc);
		setShip(AId1);
		setShip(AId2);
		setShip(AId3);
		setShip(AIs1);
		setShip(AIs2);
		setShip(AIs3);
		//===================================================================================================
	}
	
	//display method
	public void display() {
		int colNum = 1;
		System.out.print("     ");
		for(;colNum <= MAP_SIZE; colNum++) {
			if(colNum < 10) {
				System.out.print(colNum + "   ");
			}else
				System.out.print(colNum + "  ");
		}
		System.out.println();
		System.out.println();
		for(int x = 0; x < MAP_SIZE; x++) {
			if(x < 9) {
				System.out.print(x + 1 + "    ");
			}else
				System.out.print(x + 1 + "   ");
			for(int y = 0; y < MAP_SIZE; y++) {
				//only for testing

				if(map[x][y] == 0) {
					System.out.print(".   ");
				}else if(map[x][y] == 1) {
					System.out.print("A   ");
				}else if(map[x][y] == 2) {
					System.out.print("C   ");
				}else if(map[x][y] == 3) {
					System.out.print("D   ");
				}else if(map[x][y] == 4) {
					System.out.print("S   ");
				}else if(map[x][y] == -1) {
					System.out.print("X   ");
				}else if(map[x][y] == 5) {
					System.out.print("a   ");
				}else if(map[x][y] == 6) {
					System.out.print("c   ");
				}else if(map[x][y] == 7) {
					System.out.print("d   ");
				}else if(map[x][y] == 8) {
					System.out.print("s   ");
				}

			}
			System.out.println();
			System.out.println();
		}
		System.out.println("numOfShots: " + numOfShots + ", " + "numOfHits: " + numOfHits + ", " + "numOfSunk: " + numOfSunk);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	/**
	//How to make sure one ship does not overlap with another?
	public void setShip(Ship s) {
		Random rand = new Random();
		int xPos = rand.nextInt(10);
		int yPos = rand.nextInt(10);
		boolean vertical = rand.nextBoolean();
		for(int i = 0; i < s.getSize(); i++) {
			map[yPos][xPos] = s.getGridDisplay();
			if(vertical) {
				yPos++;
			}else {
				xPos++;
			}
		}
	}
	*/
	
	//For now we use this version of setShip only for testing
	public void setShip (Ship s) {
		int[] xPos = s.getxPos();
		int[] yPos = s.getyPos();
		for(int i = 0; i < s.getSize(); i++) {
			map[yPos[i]][xPos[i]] = s.getGridDisplay();
		}
	}
	
	public boolean attackedByPlayer(int xPos, int yPos) {
		xPos--;
		yPos--;
		numOfShots++;
			
			if(map[yPos][xPos] == 0) {  //hits empty space
				map[yPos][xPos] = -1;
				return false;
				
			}else if(map[yPos][xPos] == 1) {     //hits Carrier part
				map[yPos][xPos] = 5;
				AIa.registerHit(xPos, yPos);
				numOfHits++;
				if(AIa.checkIfSunk()) {
					numOfSunk++;
				}
				return true;
				
			}else if(map[yPos][xPos] == 2) {     //hits Cruiser part
				map[yPos][xPos] = 6;
				AIc.registerHit(xPos, yPos);
				numOfHits++;
				if(AIc.checkIfSunk()) {
					numOfSunk++;
				}
				return true;
				
			}else if(map[yPos][xPos] == 3) {   //hits Destroyer part
				map[yPos][xPos] = 7;
				if(AId1.contains(xPos, yPos)) {
					AId1.registerHit(xPos, yPos);
					numOfHits++;
					if(AId1.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(AId2.contains(xPos, yPos)) {
					AId2.registerHit(xPos, yPos);
					numOfHits++;
					if(AId2.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(AId3.contains(xPos, yPos)) {
					AId3.registerHit(xPos, yPos);
					numOfHits++;
					if(AId3.checkIfSunk()) {
						numOfSunk++;
					}
				}
				return true;
				
			}else if(map[yPos][xPos] == 4) {   //hits Submarine part
				map[yPos][xPos] = 8;
				if(AIs1.contains(xPos, yPos)) {
					AIs1.registerHit(xPos, yPos);
					numOfHits++;
					if(AIs1.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(AIs2.contains(xPos, yPos)) {
					AIs2.registerHit(xPos, yPos);
					numOfHits++;
					if(AIs2.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(AIs3.contains(xPos, yPos)) {
					AIs3.registerHit(xPos, yPos);
					numOfHits++;
					if(AIs3.checkIfSunk()) {
						numOfSunk++;
					}
				}
				return true;
			}else                //All duplicated hit
				return false;		
	}
	
	public boolean gameOver() {
		return numOfSunk == 8;
	}
}
