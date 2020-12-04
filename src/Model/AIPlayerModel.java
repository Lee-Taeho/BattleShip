package Model;
import java.util.Random;

public class AIPlayerModel {
	private final int MAP_SIZE = 10;
	private int map[][];
	private boolean checkSetShip[][]; //This is a boolean map only use in setShip method
	int numOfShots;
	int numOfHits;
	int numOfSunk;
	public int numOfShips;
	
	public AIPlayerModel() {
		numOfShots = 0;
		numOfHits = 0;
		numOfShips = 0;
		numOfSunk = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		checkSetShip = new boolean [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
				 checkSetShip[x][y] = false;
			}
		}
	}
	
	//display method only for testing
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
	
	//This set ship method will generate all five ships' position randomly
	//then set checkSetShip[y][x] to true
	public void setShip () {
		ShipModel s1 = new ShipModel(2);
		ShipModel s2 = new ShipModel(3);
		ShipModel s3 = new ShipModel(3);
		ShipModel s4 = new ShipModel(4);
		ShipModel s5 = new ShipModel(5);
		
		Random rand = new Random();
		
	}
	
	public boolean isGameOver() {
		return numOfSunk == 5;
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
}
