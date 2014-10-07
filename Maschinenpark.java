import java.util.Scanner;

public class Maschinenpark {

	int anzahl;
	int level;
	int ukosten;
	Scanner sc = new Scanner(System.in);

	public Maschinenpark(int level) {
		this.level = level;
		anzahl = 5;
		ukosten = (level + 1) * 10000;
	}

	public double kostenErmitteln() {
		double k = (anzahl / level) * 10;
		return k;
	}

	public void upgraden() {
		if (level < 5) {
			if (BT.p.get(BT.turn).geld > ukosten) {
				BT.p.get(BT.turn).geld -= ukosten;
				level++;
				System.out
						.println("Ihr Maschinenpark wurde erfolgreich verbessert.");
			} else {
				System.out
						.println("Sie haben nicht genug Geld um Ihren Maschinenpark zu verbessern");
			}
		} else {
			System.out
					.println("Sie haben Ihren Maschinenpark bereits auf die h�chste Stufe optimiert.");
		}
		ukosten = (level + 1) * 10000;
	}

	public void vergr��ern() {
		System.out
				.println("Wieviele Maschinen-Einheiten m�chten sie kaufen? 1 ME = 5.000");
		int anz = sc.nextInt();
		if (BT.p.get(BT.turn).geld > (anz * 5000)) {
			if (BT.p.get(BT.turn).geb.prodRaum > (anz * 5)) {
				System.out.println("M�chten Sie " + anz
						+ " Maschinen-Einheiten f�r " + (anz * 5000)
						+ " kaufen?");
				System.out.println("Die Maschienen werden " + (anz * 5)
						+ " Raum-Einheiten belegen.");
				System.out.println("y/n");
				String a = sc.next();
				if (a.contentEquals("y")) {
					BT.p.get(BT.turn).geld -= anz * 5000;
					BT.p.get(BT.turn).geb.prodRaum -= anz * 5;
					BT.p.get(BT.turn).geb.mp.anzahl += anz;
					System.out
							.println("Sie haben Ihren Maschinenpark erfolgreich um "
									+ anz + "Maschinen-Einheiten erweitert");
				} else {
					System.out.println("Der Vorgang wurde abgebrochen");
				}
			} else {
				System.out
						.println("Sie haben nicht genug Produktionsraum um diese Menge an Maschinen aufzustellen");
			}
		} else {
			System.out
					.println("Sie haben nicht genug Geld um diese Menge an Maschinen zu kaufen");
		}
	}

}