import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class MitarbeiterschaftTest {

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
		
		assertEquals(500, BT.p.get(0).geb.ma.kostenErmitteln(), 1e-4);  // maZulage=0, level=1, anzahl=5, turn=0
	}

	
	@Test
	public void testSchulen_positiv() { 				// Kontostand > Kosten
		
		BT.p.get(0).geld = 50000;		

		BT.p.get(0).geb.ma.schulen();					// skosten=20000, level=1, maZulage=0, turn=0
		
		assertEquals(2, BT.p.get(0).geb.ma.level); 		// Levelerhöhung
		assertEquals(30000, BT.p.get(0).geld, 1e-4); 	// Kontostandreduzierung	
	}
	
	
	@Test
	public void testSchulen_negativ() { 				// Kontostand < Kosten
		
		BT.p.get(0).geb.ma.schulen();					// skosten=20000, level=1, geld=10000, turn=0
		
		assertEquals(1, BT.p.get(0).geb.ma.level); 		// keine Levelerhöhung
		assertEquals(10000, BT.p.get(0).geld, 1e-4); 	// gleicher Kontostand
	}

	
	
	@Test
	@Ignore
	public void testVergrößern() { 						// Problem Benutzereingabe
		fail("Not yet implemented");
	}

}
