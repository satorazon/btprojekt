import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest( {Kunde.class} )
public class KundeTestPMock {

	@Before
	public void setUp() throws Exception {
		
		PowerMock.mockStatic(Math.class);							// Manipulieren der random-Funktion
		EasyMock.expect(Math.random()).andReturn(0.40).anyTimes();
		PowerMock.replay(Math.class);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testAuftraegeErstellen() {			
		 
		KundeLow k1 = mock(KundeLow.class);
		BT.klow.add(k1);
		KundeLow k2 = mock(KundeLow.class);
		BT.klow.add(k2);
		KundeMid k3 = mock(KundeMid.class);
		BT.kmid.add(k3);
		KundeHigh k4 = mock(KundeHigh.class);
		BT.khigh.add(k4);

		Kunde.auftraegeErstellen();
		
		verify(k1, times(1)).erzAuftrag();
		verify(k2, times(1)).erzAuftrag();
		verify(k3, times(1)).erzAuftrag();
		verify(k4, times(1)).erzAuftrag();
	}

}
