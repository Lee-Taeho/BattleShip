package Model;
import java.util.Random;

public class AIPlayerModel {
	private final int MAP_SIZE;
	private int map[][];
	int numOfShots;
	int numOfHits;
	public int numOfShips;
	
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
	
	//For now we use this version of setShip only for testing
	public void setShip () {
		ShipModel s1 = new ShipModel(2);
		ShipModel s2 = new ShipModel(3);
		ShipModel s3 = new ShipModel(3);
		ShipModel s4 = new ShipModel(4);
		ShipModel s5 = new ShipModel(5);
		
		Random rand = new Random();
		int x;
		int y;
		boolean vertical;
		
		//set Ship s1
		boolean checkOverlap1 = false;
		do {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			vertical = rand.nextBoolean();
			
			while(!checkOverlap1) {
				if(map[y][x] == 1) {
					checkOverlap1 = false;
				}else {
					if(vertical) {
						y++;
					}else {
						x++;
					}
					
				}
			}
			
			int[] xPos1 = new int[s1.getLength()];
			int[] yPos1 = new int[s1.getLength()];
			for(int i = 0; i < s1.getLength(); i++) {
				xPos1[i] = x;
				yPos1[i] = y;
				if(vertical) {
					y++;
				}else {
					x++;
				}
			}
		}while(!checkOverlap1);
		
		//set Ship s2
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		int[] xPos2 = new int[s2.getLength()];
		int[] yPos2 = new int[s2.getLength()];
		vertical = rand.nextBoolean();
		for(int i = 0; i < s2.getLength(); i++) {
			xPos2[i] = x;
			yPos2[i] = y;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		
		//set Ship s3
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		int[] xPos3 = new int[s3.getLength()];
		int[] yPos3 = new int[s3.getLength()];
		vertical = rand.nextBoolean();
		for(int i = 0; i < s3.getLength(); i++) {
			xPos3[i] = x;
			yPos3[i] = y;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		
		//set Ship s4
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		int[] xPos4 = new int[s4.getLength()];
		int[] yPos4 = new int[s4.getLength()];
		vertical = rand.nextBoolean();
		for(int i = 0; i < s4.getLength(); i++) {
			xPos4[i] = x;
			yPos4[i] = y;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		
		//set Ship s5
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		int[] xPos5 = new int[s5.getLength()];
		int[] yPos5 = new int[s5.getLength()];
		vertical = rand.nextBoolean();
		for(int i = 0; i < s1.getLength(); i++) {
			xPos5[i] = x;
			yPos5[i] = y;
			if(vertical) {
				y++;
			}else {
				x++;
			}
		}
		
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
		numOfShips = 0;
		map = new int [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
			}
		}
	}
}
