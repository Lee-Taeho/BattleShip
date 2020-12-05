package Model;

import java.util.Random;

public class AIPlayerModel {
	private final int MAP_SIZE = 10;
	private int map[][];
	private boolean checkSetShip[][]; //This is a boolean map only use in setShip method
	int numOfShots;
	int numOfHits;
	Random rand = new Random();
	
	public AIPlayerModel() {
		numOfShots = 0;
		numOfHits = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		checkSetShip = new boolean [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
			}
		}
		setShip(); //we call setShip() in the constructor
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
		System.out.println("numOfShots: " + numOfShots + ", " + "numOfHits: " + numOfHits);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	//This set ship method will generate all five ships' position randomly
	//then set checkSetShip[y][x] to true
	public void setShip () {
		
		boolean vertical;
		int x;
		int y;
		//For ShipModel s1
		//a do-while loop keep generating new x,y starting position until the setShipHelper returns true
		final int LENGTH_2 = 2;

		ShipModel s1 = new ShipModel(LENGTH_2);
		int xPos1[] = new int[s1.getLength()];
		int yPos1[] = new int[s1.getLength()];
		do {
			vertical = rand.nextBoolean();
			x = rand.nextInt(10); //randomly generate the first x-coordinate
			y = rand.nextInt(10); //randomly generate the first y-coordinate
			for(int i = 0; i < s1.getLength(); i++) { 
				xPos1[i] = x;
				yPos1[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!setShipHelper(xPos1, yPos1, s1.getLength()));
		s1.setXPos(xPos1);
		s1.setYPos(yPos1);
		s1.setVertical(vertical);
		
		for(int i = 0; i < s1.getLength(); i++) {
			map[yPos1[i]][xPos1[i]] = s1.getGridDisplay();
			checkSetShip[yPos1[i]][xPos1[i]] = true;
		}
		
		//For ShipModel s2
		//a do-while loop keep generating new x,y starting position until the setShipHelper returns true
		final int LENGTH_3 = 3;
		ShipModel s2 = new ShipModel(LENGTH_3);
		int xPos2[] = new int[s2.getLength()];
		int yPos2[] = new int[s2.getLength()];
		do {
			vertical = rand.nextBoolean();
			x = rand.nextInt(10); //randomly generate the first x-coordinate
			y = rand.nextInt(10); //randomly generate the first y-coordinate
			for(int i = 0; i < s2.getLength(); i++) {
				xPos2[i] = x;
				yPos2[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!setShipHelper(xPos2, yPos2, s2.getLength()));
		s2.setXPos(xPos2);
		s2.setYPos(yPos2);
		s2.setVertical(vertical);
		
		for(int i = 0; i < s2.getLength(); i++) {
			map[yPos2[i]][xPos2[i]] = s2.getGridDisplay();
			checkSetShip[yPos2[i]][xPos2[i]] = true;
		}
		
		//For ShipModel s3
		//a do-while loop keep generating new x,y starting position until the setShipHelper returns true
		ShipModel s3 = new ShipModel(LENGTH_3);
		int xPos3[] = new int[s3.getLength()];
		int yPos3[] = new int[s3.getLength()];
		do {
			vertical = rand.nextBoolean();
			x = rand.nextInt(10); //randomly generate the first x-coordinate
			y = rand.nextInt(10); //randomly generate the first y-coordinate
			xPos3 = new int[s3.getLength()];
			yPos3 = new int[s3.getLength()];
			for(int i = 0; i < s3.getLength(); i++) {
				xPos3[i] = x;
				yPos3[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!setShipHelper(xPos3, yPos3, s3.getLength()));
		s3.setXPos(xPos3);
		s3.setYPos(yPos3);
		s3.setVertical(vertical);

		for(int i = 0; i < s3.getLength(); i++) {
			map[yPos3[i]][xPos3[i]] = s3.getGridDisplay();
			checkSetShip[yPos3[i]][xPos3[i]] = true;
		}
		
		//For ShipModel s4
		//a do-while loop keep generating new x,y starting position until the setShipHelper returns true
		final int LENGTH_4 = 4;
		ShipModel s4 = new ShipModel(LENGTH_4);
		int xPos4[] = new int[s4.getLength()];
		int yPos4[] = new int[s4.getLength()];
		do {
			vertical = rand.nextBoolean();
			x = rand.nextInt(10); //randomly generate the first x-coordinate
			y = rand.nextInt(10); //randomly generate the first y-coordinate
			xPos4 = new int[s4.getLength()];
			yPos4 = new int[s4.getLength()];
			for(int i = 0; i < s4.getLength(); i++) {
				xPos4[i] = x;
				yPos4[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!setShipHelper(xPos4, yPos4, s4.getLength()));
		s4.setXPos(xPos4);
		s4.setYPos(yPos4);
		s4.setVertical(vertical);
		
		for(int i = 0; i < s4.getLength(); i++) {
			map[yPos4[i]][xPos4[i]] = s4.getGridDisplay();
			checkSetShip[yPos4[i]][xPos4[i]] = true;
		}
		
		//For ShipModel s5
		//a do-while loop keep generating new x,y starting position until the setShipHelper returns true
		final int LENGTH_5 = 5;
		ShipModel s5 = new ShipModel(LENGTH_5);
		int xPos5[] = new int[s5.getLength()];
		int yPos5[] = new int[s5.getLength()];
		do {
			vertical = rand.nextBoolean();
			x = rand.nextInt(10); //randomly generate the first x-coordinate
			y = rand.nextInt(10); //randomly generate the first y-coordinate
			xPos5 = new int[s5.getLength()];
			yPos5 = new int[s5.getLength()];
			for(int i = 0; i < s5.getLength(); i++) {
				xPos5[i] = x;
				yPos5[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!setShipHelper(xPos5, yPos5, s5.getLength()));
		s5.setXPos(xPos5);
		s5.setYPos(yPos5);
		s5.setVertical(vertical);
		
		for(int i = 0; i < s5.getLength(); i++) {
			map[yPos5[i]][xPos5[i]] = s5.getGridDisplay();
			checkSetShip[yPos5[i]][xPos5[i]] = true;
		}
	}
	
	
	//check the ship part position one by one to see
	//if it overlaps with another ship, and also check if
	//the xPos[i] and yPos[i] is out of bound, if yes return false
	private boolean setShipHelper (int xPos[], int yPos[], int length) {
		for(int i = 0; i < length; i++) {
			if(xPos[i] > 9 || xPos[i] < 0 || yPos[i] > 9 || yPos[i] < 0 || checkSetShip[yPos[i]][xPos[i]]) { //out of bound check should go first
				return false;                                                                                //then the overlap check
			}
		}
		return true;
	}
	
	public boolean isGameOver() {
		return numOfHits == 17;
	}
	
	public boolean attackedByPlayer(int xPos, int yPos) {
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
			
		}else { //all duplicated firing -> -1, -2, -3, -4, -5
			return false; //return false
		}
	}
	
	public void reset() {
		numOfShots = 0;
		numOfHits = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		checkSetShip = new boolean [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
			}
		}
		setShip(); //we call setShip() in the constructor
	}
}
