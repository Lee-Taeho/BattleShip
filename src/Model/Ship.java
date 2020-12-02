package Model;

public interface Ship {
	boolean registerHit(int x, int y);
	boolean checkIfSunk();
	void shipSkill();
	int getSize();
	int getGridDisplay();
	int[] getxPos();
	int[] getyPos();
}
