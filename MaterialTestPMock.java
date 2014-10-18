import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest( {Material.class} )
public class MaterialTestPMock {

	@Before
	public void setUp() throws Exception {
		
		BT.p.add(new Player(1, "Hugo"));
	}
	
	@After
	public void tearDown() throws Exception {
		
		BT.p.clear();
	}
	
	@Test
	public void testErstellen() {
		 
		PowerMock.mockStatic(Math.class);							// Manipulieren der random-Funktion
		EasyMock.expect(Math.random()).andReturn(0.60).anyTimes();
		PowerMock.replay(Math.class);
		
		Material m = new Material();
		m.erstellen();
		
		// Preise
	    assertEquals(7, m.pmlow, 1e-4);
	    assertEquals(11, m.pmmid, 1e-4);
	    assertEquals(16, m.pmhigh, 1e-4);
	    // Mengen
	    assertEquals(160, m.mlow);
	    assertEquals(110, m.mmid);
	    assertEquals(70, m.mhigh);
	}

}
