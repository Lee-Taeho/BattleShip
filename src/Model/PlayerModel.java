package Model;
import java.util.Random;

public class PlayerModel {
	private final int MAP_SIZE;
	private int map[][];
	int numOfShots;
	int numOfHits;
	int numOfShips;
	Random rand = new Random();

	public PlayerModel() {
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
					System.out.print(".   "); //empty space
				}else if(map[x][y] == 1) {
					System.out.print("S   "); //ship part that has not been hit
				}else if(map[x][y] == 2) {
					System.out.print("X   "); //ship part that has been hit
				}else if(map[x][y] == -1) {
					System.out.print("O   "); //missed shot
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
	public void setShip (Ship s) {
		int[] xPos = s.getXPos();
		int[] yPos = s.getYPos();
		for(int i = 0; i < s.getLength(); i++) {
			map[yPos[i]][xPos[i]] = s.getGridDisplay();
		}
		numOfShips++;
	}

	public boolean isGameOver() {
		return numOfHits == 17;
	}

	public boolean attackedByAI() {
		int xPos = rand.nextInt(10);
		int yPos = rand.nextInt(10);
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
