import java.util.Collections;
import java.util.Vector;

public class BT {

	static Vector<Player> p = new Vector<Player>();
	static int runde = 0;
	static Material materialmarkt = new Material();
	static int turn;

	public static void main(String[] args) {
		Player.erstellen();
		Kunde.kundenErstellen();
		while (true) {
			runde++;
			Collections.shuffle(p); // Spieler mischen
			for (turn = 0; turn < p.size(); turn++) { // Phase 1://
														// Rundeninformationen
				p.get(turn).quartalsBericht();
				p.get(turn).getZufall();
			}
			Kunde.auftraegeErstellen();
			for (turn = 0; turn < p.size(); turn++) { // Phase 2: Auftrag //
														// beschaffen
				p.get(turn).auftragb();
			}
			materialmarkt.erstellen();
			for (turn = 0; turn < p.size(); turn++) { // Phase 3: Material
														// beschaffen
				p.get(turn).auftragInfo();
				p.get(turn).materialBeschaffen();
			}
			for (turn = 0; turn < p.size(); turn++) { // Phase 4: Investitionen
				p.get(turn).investMenu(); // Gebäude, Maschinen, Mitarbeiter
			}
		}
	}
}
