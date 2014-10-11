import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class KundeHighTest {

	@Before
	public void setUp() throws Exception {
		
		BT.p.add(new Player(1, "Hugo"));
		BT.p.add(new Player(2, "Maria"));
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
		Kunde.au.clear();
	}
	
	
	
	@Test
	public void testKundenErstellen() { 
		
		Kunde.kundenErstellen();
		
		// erwartet
		Vector<Kunde> lowExp = new Vector<Kunde>();
		lowExp.add(new KundeLow(0, "Cheap", 0, 10));
		lowExp.add(new KundeLow(1, "Günstig und Gut", 0, 10));
		
		Vector<Kunde> midExp = new Vector<Kunde>();
		midExp.add(new KundeMid(2, "Mik", 30, 40));
		
		Vector<Kunde> highExp = new Vector<Kunde>();
		highExp.add(new KundeHigh(3, "Ladi", 60, 80));
		
		
		// Vergleich
		assertEquals(lowExp, BT.klow);
		assertEquals(midExp, BT.kmid);
		assertEquals(highExp, BT.khigh);
	}

	
	@Test
	public void testAuftragsBestätigung() {
		
		Kunde.au.add(new Auftrag("Auftrag1", 1, 10, 50, 40, 1, 1000, 5, false));
		Kunde.au.add(new Auftrag("Auftrag2", 2, 10, 50, 40, 1, 1000, 5, false));
		
		Kunde.au.get(1).a.name = "Hugo"; 			// von welchem Spieler Angebot für den Auftrag vorliegt
		Kunde.au.get(0).a.name = "Olaf";
		
		Kunde.auftragsBestätigung();
		
		// 1 Bestätigung für Hugo
		assertEquals(1, BT.p.get(0).todo.size());	
		assertEquals(Kunde.au.get(1), BT.p.get(0).todo.get(0));
		
		// keine Bestätigung für Maria
		assertEquals(0, BT.p.get(1).todo.size());			
	}
	
	
	
	@Test
	@Ignore
	public void testAuftraegeErstellen() {			// Problem Zufallszahl
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testErzAuftrag() {					// Problem Zufallszahl
		fail("Not yet implemented");
	}

}
