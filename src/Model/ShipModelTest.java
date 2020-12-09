package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.ShipModel;

class ShipModelTest {

	ShipModel s1 = new ShipModel(2, 3, 3, true);
	@Test
	void testGetGridDisplay() {
		s1.getGridDisplay();
		assertEquals(3, s1.getGridDisplay());
	}
}
