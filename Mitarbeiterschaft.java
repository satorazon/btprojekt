import java.util.Scanner;

public class Mitarbeiterschaft {

	int anzahl;
	int level;
	int skosten;
	Scanner sc = new Scanner(System.in);

	public Mitarbeiterschaft(int level) {
		this.level = level;
		anzahl = 5;
		skosten = (level + 1) * 10000;
	}

	public double kostenErmitteln() {
		int k = ((level * 100) + BT.p.get(BT.turn).geb.maZulage) * anzahl;
		return k;
	}

	public void schulen() {
		if (level < 5) {
			if (BT.p.get(BT.turn).geld > skosten) {
				BT.p.get(BT.turn).geld -= skosten;
				level++;
				System.out
						.println("Ihre Mitarbeiter erhalten eine Fortbildung.");
			} else {
				System.out
						.println("Sie haben nicht genug Geld um Ihre Mitarbeiter fortzubilden");
			}
		} else {
			System.out.println("Ihren Mitarbeiter sind optimal ausgebildet.");
		}
		skosten = (level + 1) * 10000;
	}

	public void vergr��ern() {
		System.out
				.println("Wieviele Mitarbeiter m�chten Sie einstellen? 1 MA = 1.000");
		int anz = sc.nextInt();
		if (BT.p.get(BT.turn).geld > (anz * 1000)) {
			if (BT.p.get(BT.turn).geb.prodRaum > (anz * BT.p.get(BT.turn).geb.maRaum)) {
				System.out.println("M�chten Sie " + anz + " Mitarbeiter f�r "
						+ (anz * 1000) + " einstellen?");
				System.out.println("Die Mitarbeiter ben�tigen "
						+ (anz * BT.p.get(BT.turn).geb.maRaum)
						+ " Raum-Einheiten.");
				System.out.println("y/n");
				String a = sc.next();
				if (a.contentEquals("y")) {
					BT.p.get(BT.turn).geld -= anz * 5000;
					BT.p.get(BT.turn).geb.prodRaum -= anz
							* BT.p.get(BT.turn).geb.maRaum;
					BT.p.get(BT.turn).geb.ma.anzahl += anz;
					System.out.println("Sie haben erfolgreich " + anz
							+ " neue Mitarbeiter eingestellt");
				} else {
					System.out.println("Der Vorgang wurde abgebrochen");
				}
			} else {
				System.out
						.println("Sie haben nicht genug Produktionsraum um diese Menge an Mitarbeitern einzustellen");
			}
		} else {
			System.out
					.println("Sie haben nicht genug Geld um diese Menge an Mitarbeitern einzustellen");
		}
	}

}
