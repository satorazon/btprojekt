import java.util.Scanner;

public class Gebaeude {

	int level;
	int lagerRaum = level * 100;
	int prodRaum = level * 100;
	double kosten = level * 100;
	double ausbkosten = (level + 1) * 100000;

	Maschinenpark mp;
	Mitarbeiterschaft ma;

	int arbeitsbed;
	int maRaum;
	int maZulage;

	public Gebaeude(int level) {
		this.level = level;
		mp = new Maschinenpark(1);
		ma = new Mitarbeiterschaft(1);
		arbeitsbed = 1;
		maRaum = 1;
		maZulage = 0;
	}

	public void ausbauen() {
		if (level < 6) {
			if (BT.p.get(BT.turn).geld > ausbkosten) {
				lagerRaum = level * 100;
				prodRaum = level * 100;
				kosten = level * 10;
				BT.p.get(BT.turn).geld -= ausbkosten;
				level++;
				System.out.println("Ihr Gebäude wurde erfolgreich ausgebaut.");
			} else {
				System.out
						.println("Sie haben nicht genug Geld um Ihr Gebäude auszubauen.");
			}
		} else {
			System.out
					.println("Ein weiterer Ausbau Ihres Gebäudes ist nicht möglich.");
		}
	}

	public void arbeitsbVerb() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Möchten Sie die Arbeitsbedingungen für Ihre Mitarbeiter für 5.000 verbessern? y/n");
		if (sc.nextLine().contentEquals("y")) {
			if (maRaum < 5) {

				if (BT.p.get(BT.turn).geld > 5000) {
					if (((prodRaum + ma.anzahl * maRaum) - (ma.anzahl * (maRaum + 1))) > 0) {
						maRaum++;
						maZulage += 100;
						BT.p.get(BT.turn).rep += 20;
					}
				}
			}
		}
		sc.close();
	}

	// TODO: PRODUKTIONSKAPAZIÄT - IN AUFTRÄGEN - IM GEBÄUDE

}
