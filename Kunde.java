import java.util.Vector;

abstract class Kunde {

	int id;

	String name;
	int minrep;
	int minqual;

	static int auid;

	static Vector<Auftrag> au = new Vector<Auftrag>();

	public Kunde(int id, String name, int minrep, int minqual) {
		this.id = id;
		this.name = name;
	}

	public static void kundenErstellen() {
		int id = 0;
		String[] kundenname = { "Cheap", "G�nstig und Gut", "Mik", "Ladi",
				"H&A", "C&M", "Fliegele", "Ver & Dolce", "Sport Design" };
		for (int i = 0; i < BT.p.size(); i++) {
			BT.klow.add(new KundeLow(id, kundenname[id], 0, 10));
			id++;
		}
		for (int i = 0; i < ((BT.p.size() / 4) + (BT.p.size() / 2)); i++) {
			BT.kmid.add(new KundeMid(id, kundenname[id], 60, 40));
			id++;
		}
		for (int i = 0; i < (BT.p.size() / 2); i++) {
			BT.khigh.add(new KundeHigh(id, kundenname[id], 60, 80));
			id++;
		}
	}

	public static void auftraegeErstellen() {

		// G�nstig Kunden
		for (int i = 0; i < BT.klow.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) { // Kunde soll einen Auftrag erstellen
				BT.klow.get(i).erzAuftrag();
			}
			if (ran > 5) { // Kunde soll zwei Auftr�ge erstellen
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						BT.klow.get(i).erzAuftrag();
					}
				} else { // Kunde soll drei Auftr�ge erstellen
					for (int j = 0; j < 3; j++) {
						BT.klow.get(i).erzAuftrag();
					}
				}
			}
		}
		// Mittel Kunden
		for (int i = 0; i < BT.kmid.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) {
				BT.kmid.get(i).erzAuftrag();
			}
			if (ran > 5) {
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						BT.kmid.get(i).erzAuftrag();
					}
				} else {
					for (int j = 0; j < 3; j++) {
						BT.kmid.get(i).erzAuftrag();
					}
				}
			}
		}
		// Edel Kunden
		for (int i = 0; i < BT.khigh.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) {
				BT.khigh.get(i).erzAuftrag();
			}
			if (ran > 5) {
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						BT.khigh.get(i).erzAuftrag();
					}
				} else {
					for (int j = 0; j < 3; j++) {
						BT.khigh.get(i).erzAuftrag();
					}
				}
			}
		}

	}

	public abstract void erzAuftrag();

	public static void auftragsBest�tigung() {
		for (int i = 0; i < au.size(); i++) {
			for (int j = 0; j < BT.p.size(); j++) {
				if (BT.p.get(j).name.contentEquals(au.get(i).a.name)) {
					BT.p.get(BT.turn).todo.add(au.get(i));
				}
			}
		}

	}

}
