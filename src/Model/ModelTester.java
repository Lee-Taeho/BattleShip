package Model;
import java.util.Scanner;

public class ModelTester {
	public static void main(String argv[]) {
		Scanner s = new Scanner (System.in);
		
	//=============== player class constructor will set all ShipModels ========================
		PlayerModel p1 = new PlayerModel();
		ShipModel s1 = new ShipModel(1,3,2,true);
		ShipModel s2 = new ShipModel(4,0,3,true);
		ShipModel s3 = new ShipModel(5,6,3,false);
		ShipModel s4 = new ShipModel(7,1,4,true);
		ShipModel s5 = new ShipModel(3,8,5,false);
		p1.setShip(s1);
		p1.setShip(s2);
		p1.setShip(s3);
		p1.setShip(s4);
		p1.setShip(s5);
		
			
	//================= AIPlayer class constructor will set all ships ==========================
		AIPlayerModel p2 = new AIPlayerModel();
		
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

				if(p2.isGameOver()){
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
				
				if(p1.isGameOver()) {
					System.out.println("Unfortunately, AI wins!!!!!");
					return;
				}
				
			}
		}
		//=====================================================================================================
		
	}
}
