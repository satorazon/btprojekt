import java.util.Collections;
import java.util.Vector;

public class BT {

	static Vector<Player> p = new Vector<Player>(); // Liste der Spieler
	static Vector<Kunde> klow = new Vector<Kunde>(); // Kundenlisten
	static Vector<Kunde> kmid = new Vector<Kunde>();
	static Vector<Kunde> khigh = new Vector<Kunde>();
	static Material materialmarkt = new Material(); // Materialmarkt
	static int runde; // Rundennummer
	static int turn; // Aktiver Spieler

	public static void main(String[] args) {
		Player.erstellen();
		Kunde.kundenErstellen();
		while (true) {
			runde++;
			System.out.println("Runde: " + runde);
			System.out.println();
			Collections.shuffle(p); // Spieler Reihenfolge mischen
			Kunde.au.clear();
			for (turn = 0; turn < p.size(); turn++) { // Phase 1:
														// Rundeninformationen
														// und Zufallsereignis
				if (runde > 1) {
					System.out.println("------------------------------------");
					System.out.println(p.get(turn).name + " ist an der Reihe");
					System.out.println("------------------------------------");
					System.out.println();
					p.get(turn).quartalsBericht();
					p.get(turn).clearRecord();
					p.get(turn).getZufall();
				}
			}
			Kunde.auftraegeErstellen();
			for (turn = 0; turn < p.size(); turn++) { // Phase 2: Angebote für
														// Auftäge abgeben
				System.out.println();
				System.out.println(p.get(turn).name + " ist an der Reihe");
				System.out.println();
				p.get(turn).auBeschaffen();
			}
			Player.auZuteilen();
			materialmarkt.erstellen();
			for (turn = 0; turn < p.size(); turn++) { // Phase 3: Material und
														// Rohstoffe
														// beschaffen
				System.out.println();
				System.out.println(p.get(turn).name + " ist an der Reihe");
				System.out.println();
				p.get(turn).einkaufMenu();
			}
			for (turn = 0; turn < p.size(); turn++) { // Phase 4: Investitionen
														// tätigen und Aufträge
														// bearbeiten
				System.out.println(p.get(turn).name + " ist an der Reihe");
				System.out.println();
				p.get(turn).investMenu();
				p.get(turn).auBearbeiten();
			}
		}
	}
}
