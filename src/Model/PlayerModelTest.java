package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.PlayerModel;
import Model.ShipModel;

class PlayerModelTest {

	PlayerModel p1 = new PlayerModel();
	ShipModel s1 = new ShipModel(2,3,3,true);
	ShipModel s2 = new ShipModel(1,3,3,false);
	
	
	@Test
	void testSetShip() {
		p1.setShip(s1);
		assertFalse(p1.setShip(s2));
	}
	
	@Test
	void testIsGameOver() {
		p1.attackedByAI();
		assertFalse(p1.isGameOver());
	}

}
