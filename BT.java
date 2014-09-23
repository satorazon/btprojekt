import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BT {

	static Vector<Player> p = new Vector<Player>();
	static Vector<Kunde> klow = new Vector<Kunde>();
	static Vector<Kunde> kmid = new Vector<Kunde>();
	static Vector<Kunde> khigh = new Vector<Kunde>();

	static int runde = 0;

	public static void main(String[] args) {
		spielerErstellen();
		kundenErstellen();
		while (true) {
			runde++;
			Collections.shuffle(p); // Spieler mischen
			for (int j = 0; j < p.size(); j++) { // Phase 1: Rundeninformationen
				quartalsbericht(p.get(j));
				p.get(j).getZufall();
			}
			auftragserstellung();
			for (int j = 0; j < p.size(); j++) { // Phase 2: Auftrag beschaffen
				auftragsbeschaffung(p.get(j));
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

	private static void kundenErstellen() {
		int id = 0;
		String[] kundenname = { "Cheap", "Günstig und Gut", "Mik", "Ladi",
				"H&A", "C&M", "Fliegele", "Ver & Dolce", "Sport Design" };
		for (int i = 0; i < p.size(); i++) {
			klow.add(new KundeLow(id, kundenname[id], 0, 10));
			id++;
		}
		for (int i = 0; i < ((p.size() / 4) + (p.size() / 2)); i++) {
			kmid.add(new KundeMid(id, kundenname[id], 60, 40));
			id++;
		}
		for (int i = 0; i < (p.size() / 2); i++) {
			khigh.add(new KundeHigh(id, kundenname[id], 60, 80));
			id++;
		}
	}

	private static void spielerErstellen() {
		boolean newp = true;
		int id = 0;
		do {
			id++;
			p.add(new Player(id));
			System.out.println("Geben Sie einen Spielernamen ein: ");
			Scanner sc = new Scanner(System.in);
			String n = sc.nextLine();
			Player.setName(n);
			Player.setGeld(10000);
			if (id >= 2) {
				System.out.println(" Einen weiteren Spieler hinzufügen? y/n");
				String a = sc.nextLine();
				sc.close();
				if (a.contentEquals("y")) {
					newp = true;
				} else {
					newp = false;
				}
			} else {
				newp = true;
			}
		} while (newp = true);
	}

	private static void quartalsbericht(Player player) {
		if (runde > 1) {
			/*
			 * Zum Schluss noch alle wichtigen Daten aus der letzten Runde
			 * sammeln und hier in der nächsten Runde darstellen
			 */
		}
	}

	private static void auftragserstellung() {
		// Günstig Kunden
		for (int i = 0; i < klow.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) {
				klow.get(i).erzAuftrag();
			}
			if (ran > 5) {
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						klow.get(i).erzAuftrag();
					}
				} else {
					for (int j = 0; j < 3; j++) {
						klow.get(i).erzAuftrag();
					}
				}
			}
		}
		// Mittel Kunden
		for (int i = 0; i < kmid.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) {
				kmid.get(i).erzAuftrag();
			}
			if (ran > 5) {
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						kmid.get(i).erzAuftrag();
					}
				} else {
					for (int j = 0; j < 3; j++) {
						kmid.get(i).erzAuftrag();
					}
				}
			}
		}
		// Edel Kunden
		for (int i = 0; i < khigh.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) {
				khigh.get(i).erzAuftrag();
			}
			if (ran > 5) {
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						khigh.get(i).erzAuftrag();
					}
				} else {
					for (int j = 0; j < 3; j++) {
						khigh.get(i).erzAuftrag();
					}
				}
			}
		}

	}

	private static void auftragsbeschaffung(Player player) {
		// TODO Auto-generated method stub

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
