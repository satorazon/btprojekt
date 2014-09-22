import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class BT {

	static Vector<Player> v = new Vector<Player>();

	public static void main(String[] args) {
		spielerErstellen();
		int i = 0;
		while (true) {
			i++;
			Collections.shuffle(v); // Spieler mischen
			for (int j = 0; j < v.size(); j++) { // Phase 1: Rundeninformationen
				quartalsbericht(v.get(j));
				zufallsereignis(v.get(j));
				j++;
			}
			auftragserstellung();
			for (int j = 0; j < v.size(); j++) { // Phase 2: Auftrag beschaffen
				auftragsbeschaffung(v.get(j));
				j++;
			}
			auftragvergabe();
			materialerstellung();
			for (int j = 0; j < v.size(); j++) { // Phase 3: Material beschaffen
				auftraginfo(v.get(j));
				materialbeschaffung(v.get(j));
				j++;
			}
			materialvergabe();
			for (int j = 0; j < v.size(); j++) { // Phase 4: Investitionen
				materialinfo(v.get(j));
				gebaeude(v.get(j));
				mitarbeiter(v.get(j));
				maschinen(v.get(j));
				j++;
			}
			gebmitinfo();
		}
	}

	private static void materialerstellung() {
		// TODO Auto-generated method stub
		
	}

	private static void auftragserstellung() {
		// TODO Auto-generated method stub
		
	}

	private static void spielerErstellen() {
		boolean newp = true;
		int id = 0;
		do {
			id++;
			v.add(new Player(id));
			System.out.println("Player Name");
			Scanner sc = new Scanner(System.in);
			String n = sc.nextLine();
			Player.setName(n);
			if (id >= 2) {
				System.out.println("Another Player? y/n");
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
		// TODO Auto-generated method stub

	}

	private static void zufallsereignis(Player player) {
		boolean e = false;
		int rep = player.getRep();
		rep = rep / 10;
		int random = (int) (Math.random() * 10);
		if (random * 2 > rep) {
			if (rep < 6) {
				Zufallsereignis erg = new Zufallsereignis(1); // schlecht
			}
		}
		if (random == rep) {
			if (e == false) {
				Zufallsereignis erg = new Zufallsereignis(0); // gut
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
