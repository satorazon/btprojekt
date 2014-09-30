import java.util.Vector;

abstract class Kunde {

	int id;

	String name;
	int minrep;
	int minqual;

	static int auid;

	static Vector<Auftrag> au = new Vector<Auftrag>();

	static Vector<Kunde> klow = new Vector<Kunde>();
	static Vector<Kunde> kmid = new Vector<Kunde>();
	static Vector<Kunde> khigh = new Vector<Kunde>();

	public Kunde(int id, String name, int minrep, int minqual) {
		this.id = id;
		this.name = name;
	}

	public static void kundenErstellen() {
		int id = 0;
		String[] kundenname = { "Cheap", "Günstig und Gut", "Mik", "Ladi",
				"H&A", "C&M", "Fliegele", "Ver & Dolce", "Sport Design" };
		for (int i = 0; i < BT.p.size(); i++) {
			klow.add(new KundeLow(id, kundenname[id], 0, 10));
			id++;
		}
		for (int i = 0; i < ((BT.p.size() / 4) + (BT.p.size() / 2)); i++) {
			kmid.add(new KundeMid(id, kundenname[id], 60, 40));
			id++;
		}
		for (int i = 0; i < (BT.p.size() / 2); i++) {
			khigh.add(new KundeHigh(id, kundenname[id], 60, 80));
			id++;
		}
	}

	public static void auftraegeErstellen() {

		// Günstig Kunden
		for (int i = 0; i < klow.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) { // Kunde soll einen Auftrag erstellen
				klow.get(i).erzAuftrag();
			}
			if (ran > 5) { // Kunde soll zwei Aufträge erstellen
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						klow.get(i).erzAuftrag();
					}
				} else { // Kunde soll drei Aufträge erstellen
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

	public abstract void erzAuftrag();

	public static void auftragsBestätigung() {
		for (int i = 0; i < au.size(); i++) {
			for (int j = 0; j < BT.p.size(); j++) {
				if (BT.p.get(j).name.contentEquals(au.get(i).a.name)) {
					BT.p.get(BT.turn).todo.add(au.get(i));
				}
			}
		}

	}

}
