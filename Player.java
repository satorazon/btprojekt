import java.util.Scanner;
import java.util.Vector;

public class Player {

	int rep;
	boolean sgv; // Schmiergeld verwendet
	int id;
	String name;
	double geld;
	int qual;

	Gebaeude geb = new Gebaeude(1);
	static Vector<Auftrag> todo = new Vector<Auftrag>();
	Material material = new Material();

	int kap = (geb.ma.anzahl * geb.ma.level) + (geb.mp.anzahl * geb.mp.anzahl);

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static void erstellen() {
		boolean newp = true;
		int id = 0;
		do {
			id++;

			System.out.println("Geben Sie einen Spielernamen ein: ");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			BT.p.add(new Player(id, name));
			BT.p.get(BT.turn).geld = 10000;
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
		} while (newp == true);
	}

	public void getZufall() {
		Zufallsereignis ze = null;
		int calcrep = rep / 10; // "RechenRep" 0 - 9
		int random = (int) (Math.random() * 10); // Random 0 - 9
		if (random > calcrep) { // wenn Random größer als Rep --> schlechtes
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
		sc.close();
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
		boolean weiter = true;
		while (weiter == true) {
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
			kap -= legalau.get(wahl).kap;
			System.out.println("Weitere Angebote machen? y/n");
			if (sc.nextLine().contentEquals("n")) {
				weiter = false;
			}
			sc.close();
		}
	}

	public void auftragInfo() {
		System.out.println("Sie haben folgende Aufträge erhalten: ");
		for (int i = 0; i < todo.size(); i++) {
			System.out.println("Kunde: " + todo.get(i).name);
			System.out.println();
			System.out.println("Entlohnung: " + todo.get(i).a.angebot);
			System.out.println("Benötigte Materialeinheiten" + todo.get(i).mat);
			System.out.println();
			System.out.println();
		}
	}

	public void materialBeschaffen() {
		boolean mehr = true;
		Scanner sc = new Scanner(System.in);
		while (mehr == true) {
			System.out.println("Welche Materialsorte möchten Sie kaufen?");
			System.out.println("Niedrige Qualität " + BT.materialmarkt.pmlow
					+ " (1)");
			System.out.println("Mittlere Qualität " + BT.materialmarkt.pmmid
					+ " (2)");
			System.out.println("Hohe Qualität " + BT.materialmarkt.pmmid
					+ "     (3)");
			int a = sc.nextInt();
			System.out.println("Wieviele Einheiten möchten Sie kaufen?");
			int m = sc.nextInt();
			if (a == 1) {
				if (m < BT.materialmarkt.mlow) {
					double kosten = BT.materialmarkt.pmlow * m;
					if (kosten < geld) {
						System.out.println(m + " Einheiten für " + kosten
								+ " kaufen? y/n");
						if (sc.nextLine().contentEquals("y")) {
							geld -= kosten;
							BT.materialmarkt.mlow -= m;
							material.mlow += m;
						}
					} else {
						System.out.println("Sie haben nicht genug Geld");
					}
				} else {
					System.out.println("Diese Menge ist nicht verfügbar");
				}
			}
			if (a == 2) {
				if (m < BT.materialmarkt.mmid) {
					double kosten = BT.materialmarkt.pmmid * m;
					if (kosten < geld) {
						System.out.println(m + " Einheiten für " + kosten
								+ " kaufen? y/n");
						if (sc.nextLine().contentEquals("y")) {
							geld -= kosten;
							BT.materialmarkt.mmid -= m;
							material.mmid += m;
						}
					} else {
						System.out.println("Sie haben nicht genug Geld");
					}
				} else {
					System.out.println("Diese Menge ist nicht verfügbar");
				}
			}
			if (a == 3) {
				if (m < BT.materialmarkt.mhigh) {
					double kosten = BT.materialmarkt.pmhigh * m;
					if (kosten < geld) {
						System.out.println(m + " Einheiten für " + kosten
								+ " kaufen? y/n");
						if (sc.nextLine().contentEquals("y")) {
							geld -= kosten;
							BT.materialmarkt.mhigh -= m;
							material.mhigh += m;
						}
					} else {
						System.out.println("Sie haben nicht genug Geld");
					}
				} else {
					System.out.println("Diese Menge ist nicht verfügbar");
				}
			}
			System.out.println("Weitere Materialen kaufen? y/n");
			if (sc.nextLine().contains("n")) {
				mehr = false;
			}
		}
		sc.close();
	}

	public void investMenu() {
		System.out.println("Was möchten Sie tun?");
		System.out.println();
		System.out.println("Das Gebäude auf Level " + (geb.level + 1) + " für "
				+ geb.ausbkosten + " ausbauen. (1)");
		System.out.println("");

	}
}
