package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.AIPlayerModel;

class AIPlayerModelTest {

	AIPlayerModel p2 = new AIPlayerModel();
	
	
	@Test
	void testAttackedByPlayer() {
		assertFalse(p2.attackedByPlayer(2, 2));
	}
	
	@Test
	void testIsGameOver() {
		p2.attackedByPlayer(2,2);
		assertFalse(p2.isGameOver());
	}
	
	@Test
	void testReset(){
		p2.reset();
		assertEquals(0, p2.getNumOfHits());
	}
}
