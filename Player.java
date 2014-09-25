import java.util.Scanner;
import java.util.Vector;

public class Player {

	int rep;
	boolean sgv; // Schmiergeld verwendet
	int id;
	static String name;
	static double geld;
	int qual;
	int kap;

	public Player(int id) {
		this.id = id;
	}

	public static void erstellen() {
		boolean newp = true;
		int id = 0;
		do {
			id++;
			BT.p.add(new Player(id));
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

	public static void setName(String n) {
		name = n;
	}

	public int getRep() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getEP() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setGeld(int i) {
		geld = i;
	}

	public double getGeld() {
		return geld;
	}

	public void getZufall() {
		Zufallsereignis ze = null;
		String ereignis;
		int calcrep = rep / 10; // "RechenRep" 0 - 9
		int random = (int) (Math.random() * 10); // Random 0 - 9
		if (random > rep) { // wenn Random größer als Rep --> schlechtes
							// Ereignis
			int k = 1;
			int ran = (int) (Math.random() * 5);
			ze = new Zufallsereignis(k, ran);
		}
		if (rep > 5) {
			if (random == rep) { // wenn Random gleich Rep aber größer als 5 -->
									// gutes Ereignis
				int k = 0;
				int ran = (int) (Math.random() * 3);
				ze = new Zufallsereignis(k, ran);
			}
		}
		System.out.println(ze.ereignis);

		// Todo: Infos Anzeigen (Geld, Rep, Kapazität, usw)

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		if (input == 1) { // Zahlen
			geld = geld - ze.geld;
			rep += 10;
		} else if (input == 3) { // Schmiergeldversuch
			System.out.println("Wieviel Schmiergeld wollen Sie zahlen?");
			System.out
					.println("Denken Sie daran: ein höherer Betrag erhöht Ihre Erfolgschancen.");
			ze.sgeld = sc.nextDouble();
			double a = ze.geld * 0.75;
			double ran = Math.random() * a;
			if (ran > ze.sgeld) {
				System.out.println("Sie sind aufgeflogen!");
				System.out.println("Sie verlieren " + ze.rep
						+ " Reputationspunkte.");
				rep -= ze.rep;
			}
			geld -= ze.sgeld;
		} else { // Ignorieren
			rep -= ze.rep;
		}

	}

	public void quartalsBericht() {
		if (BT.runde > 1) {
			/*
			 * Zum Schluss noch alle wichtigen Daten aus der letzten Runde
			 * sammeln und hier in der nächsten Runde darstellen
			 */
		}
	}

	public void auftragb() {
		Vector<Auftrag> legalau = new Vector<Auftrag>();
		System.out.println("Aufträge:");
		System.out.println();
		int j = 0;
		for (int i = 0; i < Kunde.au.size(); i++) {
			if (rep >= Kunde.au.get(i).rep) {
				if (qual >= Kunde.au.get(i).qual) {
					if (kap >= Kunde.au.get(i).kap) {
						legalau.add(Kunde.au.get(i));
						j++;
						System.out.println("(" + j + ".)");
						System.out.println("Kunde: 			  "
								+ Kunde.au.get(i).name);
						System.out.println("Höchstpreis: 	  "
								+ Kunde.au.get(i).geld);
						System.out.println("Erfahrungspunkte: "
								+ Kunde.au.get(i).ep);
						System.out.println();
						System.out.println();
					}
				}
			}
		}
		Scanner sc = new Scanner(System.in);
		Angebot a;
		boolean weiter = true;
		while (weiter = true) {
			System.out.println("Wähle einen Auftrag: ");
			int wahl = sc.nextInt();
			System.out.println("Der Höchstpreis liegt bei "
					+ legalau.get(wahl).geld + ", Machen Sie "
					+ legalau.get(wahl).name + " ein besseres Angebot: ");
			double ang = sc.nextDouble();
			if (ang > legalau.get(wahl).a.angebot) {
				legalau.get(wahl).a.angebot = ang;
				legalau.get(wahl).a.name = name;
			}
			System.out.println("Angebot an " + legalau.get(wahl).name
					+ " gesendet.");
			System.out.println("Weitere Angebote machen? y/n");
			if (sc.nextLine().contentEquals("n")) {
				weiter = false;
			}
		}
	}
}
