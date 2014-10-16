import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GebaeudeTest {

	@Before
	public void setUp() throws Exception {
		
		BT.p.add(new Player(1, "Hugo"));
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
	}

	

	@Test
	public void testAusbauen_positiv() { 				// Kontostand > Kosten
		
		BT.p.get(0).geld = 500000;						// ausbkosten=200000, level=1, turn=0
		
		BT.p.get(0).geb.ausbauen();			
		
		assertEquals(2, BT.p.get(0).geb.level);			// Levelerhöhung
		assertEquals(300000, BT.p.get(0).geld, 1e-4); 	// Kontostandreduzierung
		assertEquals(200, BT.p.get(0).geb.lagerRaum);	// Kapazitätserhöhung
		assertEquals(200, BT.p.get(0).geb.prodRaum);	//		"
		assertEquals(200, BT.p.get(0).geb.kosten, 1e-4); // Kostenerhöhung
		assertEquals(300000, BT.p.get(0).geb.ausbkosten, 1e-4); // Kosten f. nächsten Ausbau
	}
	
	
	@Test
	public void testAusbauen_negativ() { 				// Kontostand < Kosten
		
		BT.p.get(0).geb.ausbauen();						// geld=10000, ausbkosten=200000, level=1, turn=0
		
		assertEquals(1, BT.p.get(0).geb.level);			
		assertEquals(10000, BT.p.get(0).geld, 1e-4); 	
		assertEquals(100, BT.p.get(0).geb.lagerRaum);
		assertEquals(100, BT.p.get(0).geb.prodRaum);
		assertEquals(100, BT.p.get(0).geb.kosten, 1e-4);	
		assertEquals(200000, BT.p.get(0).geb.ausbkosten, 1e-4);
	}

	
	@Test
	public void testArbeitsbVerb() { 					

		BT.p.get(0).geb.sc = new Scanner("y");			// Festlegen Benutzereingabe
		
		BT.p.get(0).geb.arbeitsbVerb();					// maRaum=1, geld=10000, prodRaum=100, anzal=5, maZulage=0, rep=50

		assertEquals(2, BT.p.get(0).geb.maRaum);
		assertEquals(100, BT.p.get(0).geb.maZulage);
		assertEquals(70, BT.p.get(0).rep);
	}

}
