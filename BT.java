import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BT {

	static Vector<Player> p = new Vector<Player>();
	static int runde = 0;

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
			Kunde.auftragErstellen();
			for (int j = 0; j < p.size(); j++) { // Phase 2: Auftrag beschaffen
				p.get(j).auftragBeschaffen();
			}
			auftragvergabe();
			materialerstellung();
			for (int j = 0; j < p.size(); j++) { // Phase 3: Material beschaffen
				auftraginfo(p.get(j));
				materialbeschaffung(p.get(j));
			}
			materialvergabe();
			for (int j = 0; j < p.size(); j++) { // Phase 4: Investitionen
				materialinfo(p.get(j));
				gebaeude(p.get(j));
				mitarbeiter(p.get(j));
				maschinen(p.get(j));
			}
			gebmitinfo();
		}
	}

	private static void maschinen(Player player) {
		// TODO Auto-generated method stub

	}

	private static void gebmitinfo() {
		// TODO Auto-generated method stub

	}

	private static void materialerstellung() {
		// TODO Auto-generated method stub

	}

	private static void mitarbeiter(Player player) {
		// TODO Auto-generated method stub

	}

	private static void gebaeude(Player player) {
		// TODO Auto-generated method stub

	}

	private static void materialinfo(Player player) {
		// TODO Auto-generated method stub

	}

	private static void materialvergabe() {
		// TODO Auto-generated method stub

	}

	private static void auftragvergabe() {
		// TODO Auto-generated method stub

	}

	private static void auftraginfo(Player player) {
		// TODO Auto-generated method stub

	}

	private static void materialbeschaffung(Player player) {
		// TODO Auto-generated method stub

	}

	// reihenfolge losen, bericht, zufallsereignis
	// interaktiv kampf auftrag rohstoff
	// investieren geb mit

}
