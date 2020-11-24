import java.util.Random;

public class Player {
	private final int MAP_SIZE;
	private int map[][];
	public int numOfSunk;
	public int numOfShots;
	public int numOfHits;
	Random rand = new Random();
	
	Carrier a;
	Cruiser c;
	Destroyer d1;
	Destroyer d2;
	Destroyer d3;
	Submarine s1;
	Submarine s2;
	Submarine s3;
	
	//constructor
	Player() {
		numOfSunk = 0;
		numOfShots = 0;
		numOfHits = 0;
		MAP_SIZE = 10;
		map = new int [MAP_SIZE][MAP_SIZE];
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				 map[x][y] = 0;
			}
		}
		
		a = new Carrier(3, 4, true);
		c = new Cruiser(5, 4, false);
		d1 = new Destroyer(5, 0, true);
		d2 = new Destroyer(7, 0, true);
		d3 = new Destroyer(9, 0, true);
		s1 = new Submarine(5, 6, true);
		s2 = new Submarine(7, 6, true);
		s3 = new Submarine(9, 6, true);
		
		setShip(a);
		setShip(c);
		setShip(d1);
		setShip(d2);
		setShip(d3);
		setShip(s1);
		setShip(s2);
		setShip(s3);
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
	}
	
	//1. what to do when xPos and yPos out of bound or ships overlap??
	public void setShip (Ship s) {
		int[] xPos = s.getxPos();
		int[] yPos = s.getyPos();
		for(int i = 0; i < s.getSize(); i++) {
			map[yPos[i]][xPos[i]] = s.getGridDisplay();
		}
	}
	
	//1. what to do when xPos and yPos out of bound??
	public boolean attackedByAI() {
		int xPos = rand.nextInt(10);
		int yPos = rand.nextInt(10);
		numOfShots++;
			if(map[yPos][xPos] == 0) { //hits empty grid
				map[yPos][xPos] = -1;
				System.out.println("AI fired at (" + (xPos + 1) + "," + (yPos + 1) +")");
				return false;
				
			}else if(map[yPos][xPos] == 1) { //hits Carrier part
				map[yPos][xPos] = 5;
				a.registerHit(xPos, yPos);
				numOfHits++;
				if(a.checkIfSunk()) {
					numOfSunk++;
				}
				System.out.println("AI fired at (" + (xPos + 1) + "," + (yPos + 1) +")");
				return true;
				
			}else if(map[yPos][xPos] == 2) { //hits Cruiser part
				map[yPos][xPos] = 6;
				c.registerHit(xPos, yPos);
				numOfHits++;
				if(a.checkIfSunk()) {
					numOfSunk++;
				}
				System.out.println("AI fired at (" + (xPos + 1) + "," + (yPos + 1) +")");
				return true;
				
			}else if(map[yPos][xPos] == 3) { //hits Destroyer part
				map[yPos][xPos] = 7;
				if(d1.contains(xPos, yPos)) {
					d1.registerHit(xPos, yPos);
					numOfHits++;
					if(d1.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(d2.contains(xPos, yPos)) {
					d2.registerHit(xPos, yPos);
					numOfHits++;
					if(d2.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(d3.contains(xPos, yPos)) {
					d3.registerHit(xPos, yPos);
					numOfHits++;
					if(d3.checkIfSunk()) {
						numOfSunk++;
					}
				}
				System.out.println("AI fired at (" + (xPos + 1) + "," + (yPos + 1) +")");
				return true;
				
			}else if(map[yPos][xPos] == 4) { //hits Submarine part
				map[yPos][xPos] = 8;
				if(s1.contains(xPos, yPos)) {
					s1.registerHit(xPos, yPos);
					numOfHits++;
					if(s1.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(s2.contains(xPos, yPos)) {
					s2.registerHit(xPos, yPos);
					numOfHits++;
					if(s2.checkIfSunk()) {
						numOfSunk++;
					}
				}else if(s3.contains(xPos, yPos)) {
					s3.registerHit(xPos, yPos);
					numOfHits++;
					if(s3.checkIfSunk()) {
						numOfSunk++;
					}
				}
				System.out.println("AI fired at (" + (xPos + 1) + "," + (yPos + 1) +")");
				return true;
				
			}else            //All duplicated hit
				return false;
	}
	
	public boolean gameOver() {
		return numOfSunk == 8;
	}
}
