import static org.junit.Assert.*;

import java.util.Scanner;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest( {Player.class} )
public class PlayerTestPMock {

	
	@Before
	public void setUp() throws Exception {
		
		PowerMock.mockStatic(Math.class);							// Manipulieren der random-Funktion
		EasyMock.expect(Math.random()).andReturn(0.60).anyTimes();
		PowerMock.replay(Math.class);
		
		BT.p.add(new Player(1, "Hugo"));
	}
	
	@After
	public void tearDown() throws Exception { 
		
		BT.p.clear();
	}
	
	
	@Test
	public void testGetZufall_abwenden() { 	// Player möchte mit einer Verbesserungsinvestition Ereignis abwenden

		String s = "1";						// simulierte Benutzereingabe
		Player.sc = new Scanner(s);
		
		BT.p.get(0).getZufall();  			// rep=50, calcrep=5, random=6, k=1, ran=4, ereignis(k,ran), gut=false
		
		assertEquals(2000, BT.p.get(0).geld, 1e-4);
		assertEquals(8000, BT.p.get(0).zufallkosten, 1e-4);
		assertEquals(60, BT.p.get(0).rep);
	}
	
	@Test
	public void testGetZufall_abwarten() { 	// Player möchte nichts gegen Ereignis unternehmen

		String s = "2";						// simulierte Benutzereingabe
		Player.sc = new Scanner(s);
		
		BT.p.get(0).getZufall();  			
		
		assertEquals(20, BT.p.get(0).rep);
	}
	 
	@Test
	public void testGetZufall_schmiergeld() { 	// Player möchte mit Schmiergeld Ereignis abwenden

		String s = "3;3000";						// simulierte Benutzereingabe
		Player.sc = new Scanner(s).useDelimiter(";");
		
		BT.p.get(0).getZufall();  			// ran=225
		
		assertEquals(7000, BT.p.get(0).geld, 1e-4);
		assertEquals(3000, BT.p.get(0).zufallkosten, 1e-4);
	}
	
}
