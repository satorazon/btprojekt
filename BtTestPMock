import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest( {Player.class, Kunde.class} )
public class BtTestPMock {



	@Test
	public void testMain() {	
		
		PowerMockito.mockStatic(Player.class);		// Mock der statischen Methoden
		PowerMockito.mockStatic(Kunde.class);
		
		Player p1 = mock(Player.class);				// Mock der Klassen
		Player p2 = mock(Player.class);
		BT.p.add(p1);
		BT.p.add(p2);

		Material m = mock(Material.class);
		BT.materialmarkt = m;
		
		
		BT.main(null);

		
		// Verifizieren der statischen Methodenaufrufe
		
		PowerMockito.verifyStatic();
		Kunde.kundenErstellen();
		
		PowerMockito.verifyStatic();
		Player.erstellen();
		
		PowerMockito.verifyStatic(Mockito.times(10));
		Kunde.auftraegeErstellen();
		
		PowerMockito.verifyStatic(Mockito.times(10));
		Player.auZuteilen();
		
		
		// Verifizieren der nicht-statischen Methodenaufrufe
		
		verify(m, times(10)).erstellen();
		
		verify(p1, times(9)).quartalsBericht();
		verify(p2, times(9)).quartalsBericht();
		
		verify(p1, times(9)).clearRecord();
		verify(p2, times(9)).clearRecord();
		
		verify(p1, times(9)).getZufall();
		verify(p2, times(9)).getZufall();

		verify(p1, times(10)).auBeschaffen();
		verify(p2, times(10)).auBeschaffen();
		
		verify(p1, times(10)).einkaufMenu();
		verify(p2, times(10)).einkaufMenu();
		
		verify(p1, times(10)).investMenu();
		verify(p2, times(10)).investMenu();
		
		verify(p1, times(10)).auBearbeiten();
		verify(p2, times(10)).auBearbeiten();
	}

}
