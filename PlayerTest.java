import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class PlayerTest {

	@Before
	public void setUp() throws Exception {
		
		Player player = new Player(1, "Hugo");
		BT.p.add(player);
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
		Kunde.au.clear();
	}


	
	@Test
	public void testGetKap(){
		
		assertEquals(10, BT.p.get(0).getKap());  // ma.anzahl=5, ma.level=1, mp.anzahl=5, mp.level=1
	}
	
	
	@Test
	public void testKostenErmitteln() { 
		
		// kosten=100, Mitarbeiterkosten=500, Maschinenkosten=50, matkosten=0, zufallskosten=0

		assertEquals(650, BT.p.get(0).kostenErmitteln(), 1e-4); 			// ohne fehlgeschlagene Aufträe (failed)
		
		
		BT.p.get(0).failed.add(new Auftrag("Auftrag1", 1, 10, 50, 40, 1, 1000, 5, false));
		BT.p.get(0).failed.add(new Auftrag("Auftrag2", 2, 10, 50, 40, 1, 1500, 5, false));
		
		assertEquals(1275, BT.p.get(0).kostenErmitteln(), 1e-4); 			// mit fehlgeschlagenen Aufträgen
	}

	
	@Test
	public void testAuBearbeiten_positiv() {	// Kapazität ausreichend für Bearbeitung
		
		BT.p.get(0).material.mhigh = 20;
		
		Auftrag auf = new Auftrag("Auftrag1", 1, 10, 9, 10, 3, 1000, 5, true);
		auf.a.angebot = 800;
		BT.p.get(0).todo.add(auf);
		
		BT.p.get(0).auBearbeiten();				// kap=10

		assertEquals(10, BT.p.get(0).material.mhigh);
		assertEquals(5, BT.p.get(0).ep);
		assertEquals(60800, BT.p.get(0).geld, 1e-4);
		assertEquals(50800, BT.p.get(0).ertraege, 1e-4);
		assertEquals(1, BT.p.get(0).completed.size());
		assertEquals(auf, BT.p.get(0).completed.get(0));
	}
	
	
	@Test
	public void testAuBearbeiten_negativ() {	// Kapazität nicht ausreichend
			
		BT.p.get(0).material.mlow = 20;
		BT.p.get(0).rep = 50;
		
		Auftrag auf = new Auftrag("Auftrag1", 1, 10, 15, 10, 1, 1000, 5, true);
		BT.p.get(0).todo.add(auf);
		
		BT.p.get(0).auBearbeiten();					// kap=10
		
		assertEquals(9750, BT.p.get(0).geld, 1e-4);
		assertEquals(30, BT.p.get(0).rep);
		assertEquals(1, BT.p.get(0).failed.size());
		assertEquals(auf, BT.p.get(0).failed.get(0));
		assertEquals(20, BT.p.get(0).material.mlow);		// keine Veränderung
		assertEquals(0, BT.p.get(0).ep);					// 		"
		assertEquals(0, BT.p.get(0).ertraege, 1e-4);		//		"
	}

	
	@Test
	public void testClearRecord() {
	
		BT.p.get(0).matkosten = 500;
		BT.p.get(0).zufallkosten = 300;
		BT.p.get(0).ertraege = 700;
		
		BT.p.get(0).todo.add(new Auftrag("Auftrag1", 1, 10, 50, 40, 1, 1000, 5, false));
		BT.p.get(0).failed.add(new Auftrag("Auftrag2", 2, 10, 50, 40, 1, 1500, 5, false));
		BT.p.get(0).completed.add(new Auftrag("Auftrag3", 2, 10, 50, 40, 1, 1500, 5, false));

		BT.p.get(0).clearRecord();
		
		assertEquals(0, BT.p.get(0).todo.size());
		assertEquals(0, BT.p.get(0).failed.size());
		assertEquals(0, BT.p.get(0).completed.size());
		assertEquals(0, BT.p.get(0).matkosten, 1e-4);
		assertEquals(0, BT.p.get(0).zufallkosten, 1e-4);
		assertEquals(0, BT.p.get(0).ertraege, 1e-4);
	}

	
	@SuppressWarnings("static-access")
	@Test
	public void testAuZuteilen() {

		Auftrag auf1 = new Auftrag("Auftrag1", 1, 10, 50, 40, 1, 1000, 5, false);
		Auftrag auf2 = new Auftrag("Auftrag2", 1, 10, 50, 40, 1, 1000, 5, false);
		auf1.a.name = "Hugo";
		auf2.a.name = "Maria";
		Kunde.au.add(auf1);
		Kunde.au.add(auf2);

		BT.p.get(0).auZuteilen();
		
		assertEquals(1, BT.p.get(0).todo.size());
		assertEquals(auf1, BT.p.get(0).todo.get(0));			
	}
	
	
	
	@Test
	@Ignore
	public void testErstellen() { 			// Problem Benutzereingabe
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetZufall() { 			// Problem Zufallszahl
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testQuartalsBericht() {		// kein Test, da nur Konsolenausgaben und lokale Variablen 
		fail("Not yet implemented");
	}
	
	@Test
	@Ignore
	public void testAuBeschaffen() { 		// Problem Benutzereingabe
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testAuftragInfo() { 		// kein Test, da ausschließlich Konsolenausgabe
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testMaterialBeschaffen() {	// Problem Benutzereingabe
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testInvestMenu() {			// Problem Benutzereingabe
		fail("Not yet implemented");
	}
	
	@Test
	@Ignore
	public void testEinkaufMenu() {			// Problem Benutzereingabe
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testMarkeAnlegen() {		// Problem Benutzereingabe
		fail("Not yet implemented");
	}



}
