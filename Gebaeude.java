import java.util.Scanner;

public class Gebaeude {

	int level;
	int lagerRaum;
	int prodRaum;
	double kosten;
	double ausbkosten;
	Scanner sc = new Scanner(System.in);

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
		lagerRaum = level * 100;
		prodRaum = level * 100;
		kosten = level * 100;
		ausbkosten = (level + 1) * 100000;
	}

	public void ausbauen() {
		if (level < 6) {
			if (BT.p.get(BT.turn).geld > ausbkosten) {
				BT.p.get(BT.turn).geld -= ausbkosten;
				level++;
				lagerRaum = level * 100;
				prodRaum = level * 100;
				kosten = level * 100;
				ausbkosten = (level + 1) * 100000;
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
		System.out
				.println("Möchten Sie die Arbeitsbedingungen für Ihre Mitarbeiter für 5.000 verbessern? y/n");
		String a = sc.next();
		if (a.contentEquals("y")) {
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
	}
}
