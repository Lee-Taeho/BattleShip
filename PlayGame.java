/**
 * author: JiaJun Dai
 * course: CS151
 * Description: This is just a demo, still working on it
 */
import java.util.Scanner;

public class PlayGame {

	public static void main(String argv[]) {
		Scanner s = new Scanner (System.in);
		
//=============== player class constructor will set all ships ========================
		Player p1 = new Player();
		
//================= AIPlayer class constructor will set all ships ==========================
		AIPlayer p2 = new AIPlayer();
		
		
//================= This nested while loop takes control of hit & continue firing and game over ======================
		boolean playerContinueFire;
		boolean AIContinueFire;
		
		while(true) {
			while(true){
				
				p1.display();
				System.out.println("=====================================================");
				p2.display();
				
				System.out.println("Enter the x coordinate of your firing position (1 ~ 10): ");
				int xPos = s.nextInt();
				System.out.println("Enter the y coordinate of your firing position (1 ~ 10): ");
				int yPos = s.nextInt();
				
				playerContinueFire = p2.attackedByPlayer(xPos, yPos);
				
				if(!playerContinueFire) {
					break;
				}
				
				if(p2.gameOver()){
					System.out.println("Congratulation! Player wins!!!!!");
					return;
				}
			}
			
			while(true){
				
				p1.display();
				System.out.println("=====================================================");
				p2.display();
				
				AIContinueFire = p1.attackedByAI();
				
				if(!AIContinueFire) {
					break;
				}
				
				if(p1.gameOver()) {
					System.out.println("Unfortunately, AI wins!!!!!");
					return;
				}
				
			}
		}
		//=====================================================================================================
		
	}
}
