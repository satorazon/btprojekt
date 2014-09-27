import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BT {

	static Vector<Player> p = new Vector<Player>();
	static int runde = 0;
	static Material materialmarkt = new Material();

	public static void main(String[] args) {
		Player.erstellen();
		Kunde.kundenErstellen();
		while (true) {
			runde++;
			Collections.shuffle(p); // Spieler mischen
			for (int j = 0; j < p.size(); j++) { // Phase 1: Rundeninformationen
				p.get(j).quartalsBericht();
				p.get(j).getZufall();
			}
			Kunde.auftraegeErstellen();
			for (int j = 0; j < p.size(); j++) { // Phase 2: Auftrag beschaffen
				p.get(j).auftragb();
			}
			materialmarkt.erstellen();
			for (int j = 0; j < p.size(); j++) { // Phase 3: Material beschaffen
				p.get(j).auftragInfo();
				p.get(j).materialBeschaffen();
			}
			for (int j = 0; j < p.size(); j++) { // Phase 4: Investitionen
				gebaeude(p.get(j));
				mitarbeiter(p.get(j));
				maschinen(p.get(j));
			}
		}
	}

	private static void maschinen(Player player) {
		// TODO Auto-generated method stub

	}

	private static void mitarbeiter(Player player) {
		// TODO Auto-generated method stub

	}

	private static void gebaeude(Player player) {
		// TODO Auto-generated method stub

	}

}
