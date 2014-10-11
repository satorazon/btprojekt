import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class MaschinenparkTest {

	@Before
	public void setUp() throws Exception {
				
		BT.p.add(new Player(1, "Hugo"));
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
	}

	
	
	@Test
	public void testKostenErmitteln() {
	
		assertEquals(50, BT.p.get(0).geb.mp.kostenErmitteln(), 1e-4);   // level=1, anzahl=5
	}

	
	@Test
	public void testUpgraden_positiv() { 				// Kontostand > Kosten
		
		BT.p.get(0).geld = 50000;					

		BT.p.get(0).geb.mp.upgraden();					// ukosten=20000, level=1, turn=0
		
		assertEquals(2, BT.p.get(0).geb.mp.level); 		// Levelerhöhung
		assertEquals(30000, BT.p.get(0).geld, 1e-4); 	// Kontostandreduzierung
	}
	
	
	@Test
	public void testUpgraden_negativ() { 				// Kontostand < Kosten

		BT.p.get(0).geb.mp.upgraden();					// ukosten=20000, level=1, geld=10000
		
		assertEquals(1, BT.p.get(0).geb.mp.level); 		// keine Levelerhöhung
		assertEquals(10000, BT.p.get(0).geld, 1e-4); 	// keine Kontostandänderung
	}

	
	
	@Test
	@Ignore
	public void testVergrößern() { 						// Problem Benutzereingabe
		fail("Not yet implemented");
	}

}
