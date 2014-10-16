import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class PlayerTest {

	@Before
	public void setUp() throws Exception {
		
		BT.p.add(new Player(1, "Hugo"));
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
	public void testErstellen() { 			
		BT.p.clear();						// leere Spielerliste als Ausgangsbasis

		String s = "Maria;Harald;n";		// simulierte Benutzereingaben
		Player.sc = new Scanner(s).useDelimiter(";");
		Player.erstellen();
		
		assertEquals(2, BT.p.size());
		assertEquals(new Player(1, "Maria"), BT.p.get(0));
		assertEquals(new Player(2, "Harald"), BT.p.get(1));
	}


	@Test
	@Ignore
	public void testQuartalsBericht() {		// kein Test, da nur Konsolenausgaben und lokale Variablen 
		fail("Not yet implemented");
	}
	
	@Test
	public void testAuBeschaffen_ohneMarke() { 		

		Auftrag auf1 = new Auftrag("A", 1, 10, 8, 40, 1, 4000, 5, false); 	// wird angeboten
		Auftrag auf2 = new Auftrag("B", 2, 10, 50, 40, 1, 1000, 5, true); 	// nicht angeboten
		Auftrag auf3 = new Auftrag("C", 3, 10, 50, 40, 1, 1000, 5, false); 	// nicht angeboten
		Kunde.au.add(auf1);
		Kunde.au.add(auf2);
		Kunde.au.add(auf3);

		String s = "1;3000;n";			// simulierte Benutzereingaben
		Player.sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).auBeschaffen();		// getKap=10

		assertEquals(3000, Kunde.au.get(0).a.angebot, 1e-4);
		assertEquals("Hugo", Kunde.au.get(0).a.name);

		
		Kunde.au.clear();
	}
	
	@Test
	public void testAuBeschaffen_mitMarke() { 		

		Auftrag auf1 = new Auftrag("A", 1, 60, 10, 40, 1, 4000, 5, false); 	// nicht angeboten
		Auftrag auf2 = new Auftrag("B", 2, 30, 15, 40, 1, 1000, 5, true); 	// nicht angeboten
		Auftrag auf3 = new Auftrag("C", 3, 10, 8, 40, 1, 2000, 5, true); 	// angeboten
		Kunde.au.add(auf1);
		Kunde.au.add(auf2);
		Kunde.au.add(auf3);

		String s = "1;1500;n";		// simulierte Benutzereingaben
		Player.sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).marke = true;
		
		BT.p.get(0).auBeschaffen();		// getKap=10
		
		assertEquals("Hugo", Kunde.au.get(2).a.name);
		assertEquals(1500, Kunde.au.get(2).a.angebot, 1e-4);

		
		Kunde.au.clear();
	}

	@Test
	@Ignore
	public void testAuftragInfo() { 		// kein Test, da ausschließlich Konsolenausgabe
		fail("Not yet implemented");
	}

	@Test
	public void testMaterialBeschaffen_low() {	

		BT.materialmarkt.mlow = 80;
		BT.materialmarkt.pmlow = 5;
		
		String s = "1;20;y;n";				// simulierte Benutzereingaben
		Player.sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).materialBeschaffen();	// kosten 100
		
		assertEquals(9900, BT.p.get(0).geld, 1e-4);
		assertEquals(100, BT.p.get(0).matkosten, 1e-4);
		assertEquals(60, BT.materialmarkt.mlow);
		assertEquals(20, BT.p.get(0).material.mlow, 1e-4);
		assertEquals(80, BT.p.get(0).geb.lagerRaum, 1e-4);
	}
	
	@Test
	public void testMaterialBeschaffen_mid() {	

		BT.materialmarkt.mmid = 80;
		BT.materialmarkt.pmmid = 10;
		
		String s = "2;30;y;n";				// simulierte Benutzereingaben
		Player.sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).materialBeschaffen();	// kosten 300
		
		assertEquals(9700, BT.p.get(0).geld, 1e-4);
		assertEquals(300, BT.p.get(0).matkosten, 1e-4);
		assertEquals(50, BT.materialmarkt.mmid);
		assertEquals(30, BT.p.get(0).material.mmid, 1e-4);
		assertEquals(70, BT.p.get(0).geb.lagerRaum, 1e-4);
	}
	
	@Test
	public void testMaterialBeschaffen_high() {	

		BT.materialmarkt.mhigh = 60;
		BT.materialmarkt.pmhigh = 12;
		
		String s = "3;20;y;n";				// simulierte Benutzereingaben
		BT.p.get(0).sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).materialBeschaffen();	// kosten 240
		
		assertEquals(9760, BT.p.get(0).geld, 1e-4);
		assertEquals(240, BT.p.get(0).matkosten, 1e-4);
		assertEquals(40, BT.materialmarkt.mhigh);
		assertEquals(20, BT.p.get(0).material.mhigh, 1e-4);
		assertEquals(80, BT.p.get(0).geb.lagerRaum, 1e-4);
	}


	@Test
	public void testInvestMenu() {			
		
		String s = "3;6;1;2;4;5;7";				// simulierte Benutzereingaben
		BT.p.get(0).sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).geb = mock(Gebaeude.class);
		BT.p.get(0).geb.mp = mock(Maschinenpark.class);
		BT.p.get(0).geb.ma = mock(Mitarbeiterschaft.class);

		BT.p.get(0).investMenu();
		
		verify(BT.p.get(0).geb.mp).upgraden();
		verify(BT.p.get(0).geb.mp).vergrößern();
		verify(BT.p.get(0).geb.ma).vergrößern();
		verify(BT.p.get(0).geb.ma).schulen();
		verify(BT.p.get(0).geb).ausbauen();
	}
	
	@Test
	public void testEinkaufMenu() {		
		
		String s = "2;1;3";					// simulierte Benutzereingaben
		
		Player spy = spy(BT.p.get(0));			// Spion auf Player-Objekt
		spy.sc = new Scanner(s).useDelimiter(";");
		doNothing().when(spy).auftragInfo();	// Mocking von Player-Methoden
		doNothing().when(spy).materialBeschaffen();
		
		spy.einkaufMenu();
		
		verify(spy).auftragInfo();
		verify(spy).materialBeschaffen();
	}

	
	@Test
	public void testMarkeAnlegen() {	

		String s = "Sporty;y";				// simulierte Benutzereingaben
		BT.p.get(0).sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).geld = 300000;
		
		BT.p.get(0).markeAnlegen();
		
		assertTrue(true == BT.p.get(0).marke);
		assertEquals(100000, BT.p.get(0).geld, 1e-4);
	}



}
