import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

public class Player {

	int id;
	String name;
	String mname;
	int rep;
	double geld;
	int ep;
	boolean marke;
	boolean sgv; // Schmiergeld verwendet
	double matkosten;
	double zufallkosten;
	double ertraege;
	DecimalFormat d = new DecimalFormat("#0.00");

	Gebaeude geb = new Gebaeude(1);
	Material material = new Material();
	Vector<Auftrag> todo = new Vector<Auftrag>();
	Vector<Auftrag> failed = new Vector<Auftrag>();
	Vector<Auftrag> completed = new Vector<Auftrag>();
	int kap = (geb.ma.anzahl * geb.ma.level) + (geb.mp.anzahl * geb.mp.anzahl); // Produktionskapazität
	static Scanner sc = new Scanner(System.in);

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		geld = 10000;
	}

	public static void erstellen() {
		System.out.println("Willkommen bei Bangladesch Tycoon!");
		boolean newp = true;
		int id = 0;
		do {
			id++;
			System.out.println("Geben Sie einen Spielernamen ein: ");
			String name = sc.next();
			BT.p.add(new Player(id, name)); // Spieler zur Liste hinzufügen
			System.out
					.println("Willkommen "
							+ name
							+ ", Sie erhalten eine kleine Textilfabrik und 10.000$ Startkapital");
			if (id >= 2) { // Mindestens 2 Spieler
				System.out
						.println("Möchten Sie einen weiteren Spieler hinzufügen? y/n");
				String a = sc.next();
				if (a.contentEquals("y")) {
					newp = true;
				} else {
					newp = false;
				}
			} else {
				System.out
						.println("Es wird mindestens noch ein Spieler benötigt");
				newp = true;
			}
		} while (newp == true);
		System.out.println();
	}

	public void getZufall() {
		Zufallsereignis ze = null;
		int calcrep = rep / 10; // "RechenRep" 0 - 9
		int random = (int) (Math.random() * 10); // Random 0 - 9
		if (random > calcrep) { // wenn Random größer als Rep --> schlechtes
								// Ereignis
			int k = 1; // 1 = schlechtes Ereignis
			int ran = (int) (Math.random() * 5);
			ze = new Zufallsereignis(k, ran);
		}
		if (rep > 5) {
			if (random == rep) { // wenn Random gleich Rep aber größer als 5
									// -->
									// gutes Ereignis
				int k = 0;
				int ran = (int) (Math.random() * 3);
				ze = new Zufallsereignis(k, ran);
			}
		}
		System.out.println(ze.ereignis);
		int input = sc.nextInt();
		if (input == 1) { // Zahlen
			geld -= ze.geld;
			zufallkosten += ze.geld;
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
			zufallkosten += ze.sgeld;
		} else { // Ignorieren
			rep -= ze.rep;
		}
	}

	public void quartalsBericht() {
		if (BT.runde > 1) {
			double gesgeld = 0;
			double gesstrafe = 0;
			int gesep = 0;
			System.out.println("Reputationspunkte: " + rep);
			System.out.println("Erfahrungspunkte: " + ep);
			System.out.println("Geld: " + d.format(geld) + "$");
			System.out.println("------------------------------");
			System.out.println("Erledigte Aufträge: ");
			for (int i = 0; i < completed.size(); i++) {
				System.out.println(completed.get(i).name + "\t\t\t"
						+ d.format(completed.get(i).a.angebot) + "$ \t\t"
						+ completed.get(i).ep + "EP");
				gesgeld += completed.get(i).a.angebot;
				gesep += completed.get(i).ep;
			}
			System.out.println("\t\t\t\t\t\t" + d.format(gesgeld) + "$ \t\t"
					+ gesep + "EP");
			System.out.println("Nicht erledigte Aufträge: ");
			for (int i = 0; i < failed.size(); i++) {
				System.out.println(failed.get(i).name + "\t\t\t"
						+ "Strafzahlung: "
						+ (d.format(failed.get(i).geld * 0.25)) + "$\t\t"
						+ "-20EP");
				gesstrafe += (completed.get(i).geld * 0.25);
			}
			System.out.println("\t\t\t\t\t\t" + d.format(gesstrafe) + "$ \t\t"
					+ (-20 * failed.size()) + "EP");
			System.out.println("------------------------------");
			System.out.println("Rohstoffbestand: ");
			System.out.println("Günstiges Material: " + material.mlow
					+ " Einheiten");
			System.out.println("Durchschnitts Material: " + material.mmid
					+ " Einheiten");
			System.out.println("Hochwertiges Material: " + material.mhigh
					+ " Einheiten");
			System.out.println("------------------------------");
			System.out.println("Gebäude: ");
			System.out.println("Ausbau-Stufe: " + geb.level);
			System.out.println("Kosten letztes Quartal: "
					+ d.format(geb.kosten));
			System.out.println("------------------------------");
			System.out.println("Mitarbeiter: " + geb.ma.anzahl);
			System.out.println("Fortbildungs-Level: " + geb.ma.level);
			System.out.println("Kosten letztes Quartal: "
					+ d.format(geb.ma.kostenErmitteln()));
			System.out.println("------------------------------");
			System.out.println("Maschinenpark: " + geb.mp.anzahl);
			System.out.println("Upgrade-Level: " + geb.mp.level);
			System.out.println("Kosten letztes Quartal: "
					+ d.format(geb.mp.kostenErmitteln()));
			System.out.println("------------------------------");
			System.out.println("Gesamtkosten: " + d.format(kostenErmitteln()));
			System.out.println("Erträge: " + d.format(ertraege));
			System.out.println("Bilanz: "
					+ d.format((ertraege - kostenErmitteln())));
		}

	}

	public double kostenErmitteln() {
		double k = 0;
		k += geb.kosten;
		k += geb.ma.kostenErmitteln();
		k += geb.mp.kostenErmitteln();
		for (int i = 0; i < failed.size(); i++) {
			k += (failed.get(i).geld * 0.25);
		}
		k += matkosten;
		k += zufallkosten;
		return k;
	}

	public void auBeschaffen() {
		Vector<Integer> legalau = new Vector<Integer>(); // Liste der für den
															// Spieler wählbaren
															// Aufträge
		boolean weiter = true;
		while (weiter == true) {
			System.out.println("Sie können aus folgenden Aufträgen wählen:");
			System.out.println();
			int j = 0;
			for (int i = 0; i < Kunde.au.size(); i++) {
				if (marke == false) {
					if (Kunde.au.get(i).marke == false) { // Spieler hat keine
															// Marke
						if (rep >= Kunde.au.get(i).rep) { // Prüfung auf Rep
							if (kap >= Kunde.au.get(i).kap) { // Prüfung auf Kap
								legalau.add(i);
								j++;
								System.out.println("(" + j + ".) Kunde: "
										+ Kunde.au.get(i).name);
								System.out.println("Höchstpreis: "
										+ d.format(Kunde.au.get(i).geld) + "$");
								System.out.println("Erfahrungspunkte: "
										+ Kunde.au.get(i).ep);
								switch (Kunde.au.get(i).matsorte) {
								case 1:
									System.out.println("Material: Günstig");
									break;
								case 2:
									System.out
											.println("Material: Durchschnitt");
									break;
								case 3:
									System.out.println("Material: Hochwertig");
									break;
								}
								System.out.println("Menge: "
										+ Kunde.au.get(i).mat);
								System.out.println("Benötigte Kapazität: "
										+ Kunde.au.get(i).kap);
								System.out.println();
								System.out.println();
							}
						}
					}
				} else { // Spieler hat Marke
					if (rep >= Kunde.au.get(i).rep) {
						if (kap >= Kunde.au.get(i).kap) {
							legalau.add(i);
							j++;
							System.out.println("(" + j + ".) Kunde: "
									+ Kunde.au.get(i).name);
							System.out.println("Höchstpreis: "
									+ d.format(Kunde.au.get(i).geld) + "$");
							System.out.println("Erfahrungspunkte: "
									+ Kunde.au.get(i).ep);
							System.out.println("Material:");
							switch (Kunde.au.get(i).matsorte) {
							case 1:
								System.out.println("Art: Günstig");
								break;
							case 2:
								System.out.println("Art: Durchschnitt");
								break;
							case 3:
								System.out.println("Art: Hochwertig");
								break;
							}
							System.out.println("Menge: " + Kunde.au.get(i).mat);
							System.out.println();
							System.out.println();
						}
					}
				}
			}
			System.out.println("Wählen Sie eines der Angebote: ");
			int wahl = sc.nextInt() - 1;
			System.out.println("Der Höchstpreis für diesen Auftrag liegt bei "
					+ d.format(Kunde.au.get(legalau.get(wahl).intValue()).geld)
					+ "$.");
			System.out.println("Machen Sie "
					+ Kunde.au.get(legalau.get(wahl).intValue()).name
					+ " ein besseres Angebot: ");
			double ang = sc.nextDouble();
			if (ang < Kunde.au.get(legalau.get(wahl).intValue()).a.angebot) {
				Kunde.au.get(legalau.get(wahl).intValue()).a.angebot = ang;
				Kunde.au.get(legalau.get(wahl).intValue()).a.name = name;
			}
			System.out.println();
			System.out.println("Das Angebot wurde an "
					+ Kunde.au.get(legalau.get(wahl).intValue()).name
					+ " gesendet");
			System.out.println();
			System.out.println("Weitere Angebote machen? y/n");
			String a = sc.next();
			if (a.contentEquals("n")) {
				weiter = false;
			}
		}
	}

	public void auftragInfo() {
		System.out.println();
		System.out.println("Sie haben folgende Aufträge erhalten: ");
		System.out.println();
		for (int i = 0; i < todo.size(); i++) {
			System.out.println("Kunde: " + todo.get(i).name);
			System.out.println();
			if (todo.get(i).marke == false) {
				System.out.println("Marken-Auftrag: nein");
				System.out.println("Entlohnung: "
						+ d.format(todo.get(i).a.angebot) + "$");
			} else {
				System.out.println("Marken-Auftrag: ja");
				System.out.println("Entlohnung: "
						+ (d.format(todo.get(i).a.angebot + 50000)) + "$");
			}
			if (todo.get(i).matsorte == 1) {
				System.out.println("Materialart: Günstig");
			} else if (todo.get(i).matsorte == 2) {
				System.out.println("Materialart: Durchschnitt");
			} else {
				System.out.println("Materialart: Hochwertig");
			}
			System.out.println("Benötigte Materialeinheiten: "
					+ todo.get(i).mat);
			System.out.println();
			System.out.println();
		}
	}

	public void materialBeschaffen() {
		boolean mehr = true;
		while (mehr == true) {
			System.out.println("Welche Materialsorte möchten Sie kaufen?");
			System.out.println("(1) Niedrige Qualität "
					+ d.format(BT.materialmarkt.pmlow) + "$ \t\t noch "
					+ BT.materialmarkt.mlow + " Stück");
			System.out.println("(2) Mittlere Qualität "
					+ d.format(BT.materialmarkt.pmmid) + "$ \t\t noch "
					+ BT.materialmarkt.mmid + " Stück");
			System.out.println("(3) Hohe Qualität "
					+ d.format(BT.materialmarkt.pmmid) + "$ \t\t noch "
					+ BT.materialmarkt.mhigh + " Stück");
			System.out.println("(4) Zurück");
			int a = sc.nextInt();
			int m = 0;
			if (a != 4) {
				System.out.println("Wieviele Einheiten möchten Sie kaufen?");
				m = sc.nextInt();
			} else {
				mehr = false;
			}
			if (mehr != false) {
				if (a == 1) {
					if (m <= BT.materialmarkt.mlow) {
						double kosten = BT.materialmarkt.pmlow * m;
						if (kosten < geld) {
							System.out.println(m + " Einheiten für "
									+ d.format(kosten) + "$" + " kaufen? y/n");
							if (sc.next().contentEquals("y")) {
								geld -= kosten;
								matkosten += kosten;
								BT.materialmarkt.mlow -= m;
								material.mlow += m;
								System.out.println("Erfolgreich!");
							}
						} else {
							System.out.println("Sie haben nicht genug Geld");
						}
					} else {
						System.out.println("Diese Menge ist nicht verfügbar");
					}
				}
				if (a == 2) {
					if (m <= BT.materialmarkt.mmid) {
						double kosten = BT.materialmarkt.pmmid * m;
						if (kosten < geld) {
							System.out.println(m + " Einheiten für "
									+ d.format(kosten) + "$" + " kaufen? y/n");
							if (sc.next().contentEquals("y")) {
								geld -= kosten;
								matkosten += kosten;
								BT.materialmarkt.mmid -= m;
								material.mmid += m;
								System.out.println("Erfolgreich!");
							}
						} else {
							System.out.println("Sie haben nicht genug Geld");
						}
					} else {
						System.out.println("Diese Menge ist nicht verfügbar");
					}
				}
				if (a == 3) {
					if (m <= BT.materialmarkt.mhigh) {
						double kosten = BT.materialmarkt.pmhigh * m;
						if (kosten < geld) {
							System.out.println(m + " Einheiten für "
									+ d.format(kosten) + "$" + " kaufen? y/n");
							if (sc.next().contentEquals("y")) {
								geld -= kosten;
								matkosten += kosten;
								BT.materialmarkt.mhigh -= m;
								material.mhigh += m;
								System.out.println("Erfolgreich!");
							}
						} else {
							System.out.println("Sie haben nicht genug Geld");
						}
					} else {
						System.out.println("Diese Menge ist nicht verfügbar");
					}
				}
				System.out.println("Weitere Materialen kaufen? y/n");
				if (sc.next().contains("n")) {
					mehr = false;
				}
			}
		}
	}

	public void investMenu() {
		boolean weiter = true;
		while (weiter == true) {
			System.out.println("Was möchten Sie tun?");
			System.out.println();
			System.out.println("(1) Das Gebäude für " + d.format(geb.ausbkosten)+ "$" +
					" auf Level " + (geb.level + 1) + " ausbauen");
			System.out.println("(2) Neue Maschinen kaufen");
			System.out.println("(3) Ihre Maschinen für "
					+ d.format(geb.mp.ukosten) + "$" + " auf Level "
					+ (geb.mp.level + 1) + " verbessern");
			System.out.println("(4) Neue Mitarbeiter einstellen");
			System.out.println("(5) Ihre Mitarbeiter für "
					+ d.format(geb.ma.skosten) + "$" + " auf Level "
					+ (geb.ma.level + 1) + " fortbilden");
			System.out.println("(6) Ihre eigene Marke gründen");
			System.out.println("(7) Nichts unternehmen");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				geb.ausbauen();
				break;
			case 2:
				geb.mp.vergrößern();
				break;
			case 3:
				geb.mp.upgraden();
				break;
			case 4:
				geb.ma.vergrößern();
				break;
			case 5:
				geb.ma.schulen();
				break;
			case 6:
				if (BT.runde >= 10) {
					if (marke != true) {
						markeAnlegen();
					} else {
						System.out
								.println("Sie haben bereits eine Marke angelegt");
					}
				} else {
					System.out
							.println("Diese Funktion ist erst ab Runde 10 verfügbar");
				}
				break;
			case 7:
				weiter = false;
				break;
			}
			if (input != 7) {
				System.out
						.println("Möchten sie weitere Investitionen tätigen? y/n");

				if (sc.next().contentEquals("n")) {
					weiter = false;
				}
			}
		}
	}

	public void auBearbeiten() {
		boolean fail = false;
		int restkap = kap;
		for (int i = 0; i < todo.size(); i++) {
			restkap -= todo.get(i).kap;
			if (restkap < 0) {
				fail = true;
			} else {
				switch (todo.get(i).matsorte) {
				case 1:
					material.mlow -= todo.get(i).mat;
					if (material.mlow < 0) {
						fail = true;
						material.mlow = 0;
					}
					break;
				case 2:
					material.mmid -= todo.get(i).mat;
					if (material.mlow < 0) {
						fail = true;
						material.mmid = 0;
					}
					break;
				case 3:
					material.mhigh -= todo.get(i).mat;
					if (material.mlow < 0) {
						fail = true;
						material.mhigh = 0;
					}
					break;
				}
			}
			if (fail == true) {
				rep -= 20;
				geld -= 0.25 * todo.get(i).geld;
				failed.add(todo.get(i));
			} else {
				ep += todo.get(i).ep;
				geld += todo.get(i).a.angebot;
				ertraege += todo.get(i).a.angebot;
				if (todo.get(i).marke == true) {
					geld += 50000;
					ertraege += 50000;
				}
				completed.add(todo.get(i));
			}

		}

	}

	public void einkaufMenu() {
		boolean weiter = true;
		while (weiter == true) {
			System.out.println("Was möchten Sie tun: ");
			System.out.println("(1) Erhaltene Aufträge ansehen");
			System.out.println("(2) Material einkaufen");
			System.out.println("(3) Nichts unternehmen");
			int input = sc.nextInt();
			if (input == 1) {
				auftragInfo();
			} else if (input == 2) {
				materialBeschaffen();
			} else {
				weiter = false;
			}
		}
	}

	public void markeAnlegen() {
		if (geld > 200000) {
			System.out.println("Geben Sie Ihrer Marke einen Namen: ");
			mname = sc.next();
			System.out.println("Möchten Sie " + mname
					+ " für 200000$ gründen? y/n");
			if (sc.next().contains("y")) {
				marke = true;
				geld -= 200000;
			} else {
				System.out.println("Vorgang abgebrochen");
			}
		} else {
			System.out.println("Sie benötigen 200000$ um eine Marke anzulegen");
		}
	}

	public void clearRecord() {
		todo.clear();
		failed.clear();
		completed.clear();
		matkosten = 0;
		zufallkosten = 0;
		ertraege = 0;
	}

	public static void auZuteilen() {
		for (int i = 0; i < BT.p.size(); i++) {
			for (int j = 0; j < Kunde.au.size(); j++) {
				if (Kunde.au.get(j).a.name.equals(BT.p.get(i).name)) {
					BT.p.get(i).todo.add(Kunde.au.get(j));
				}
			}
		}

	}
}

// TODO Spieler muss Marke erstellen