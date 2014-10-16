import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class KundeTest {

	@Before
	public void setUp() throws Exception {
		
		BT.p.add(new Player(1, "Hugo"));
		BT.p.add(new Player(2, "Maria"));
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
	}
	
	
	
	@Test
	public void testKundenErstellen() { 
		
		Kunde.kundenErstellen();
		
		// erwartet
		Vector<Kunde> lowExp = new Vector<Kunde>();
		lowExp.add(new KundeLow(0, "Cheap", 0));
		lowExp.add(new KundeLow(1, "Günstig und Gut", 0));
		
		Vector<Kunde> midExp = new Vector<Kunde>();
		midExp.add(new KundeMid(2, "Mik", 30));
		
		Vector<Kunde> highExp = new Vector<Kunde>();
		highExp.add(new KundeHigh(3, "Ladi", 60));
		
		
		// Vergleich
		assertEquals(lowExp, BT.klow);
		assertEquals(midExp, BT.kmid);
		assertEquals(highExp, BT.khigh);
	}
}
