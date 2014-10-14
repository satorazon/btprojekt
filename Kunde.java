import java.util.Vector;

abstract class Kunde {

	int id;
	String name;
	int minrep;

	static int auid;

	static Vector<Auftrag> au = new Vector<Auftrag>();

	public Kunde(int id, String name, int minrep) {
		this.id = id;
		this.name = name;
		this.minrep = minrep;
	}

	public static void kundenErstellen() {
		int id = 0;
		String[] kundenname = { "Cheap", "Günstig und Gut", "Mik", "Ladi",
				"H&A", "C&M", "Fliegele", "Ver & Dolce", "Sport Design" };
		for (int i = 0; i < BT.p.size(); i++) {
			BT.klow.add(new KundeLow(id, kundenname[id], 0));
			id++;
		}
		for (int i = 0; i < ((BT.p.size() / 4) + (BT.p.size() / 2)); i++) {
			BT.kmid.add(new KundeMid(id, kundenname[id], 30));
			id++;
		}
		for (int i = 0; i < (BT.p.size() / 2); i++) {
			BT.khigh.add(new KundeHigh(id, kundenname[id], 60));
			id++;
		}
	}

	public static void auftraegeErstellen() {

		// Günstig Kunden
		for (int i = 0; i < BT.klow.size(); i++) {
			int ran = (int) (Math.random() * 10);
			if (ran < 6) { // Kunde soll einen Auftrag erstellen
				BT.klow.get(i).erzAuftrag();
			}
			if (ran > 5) { // Kunde soll zwei Aufträge erstellen
				if (ran < 9) {
					for (int j = 0; j < 2; j++) {
						BT.klow.get(i).erzAuftrag();
					}
				} else { // Kunde soll drei Aufträge erstellen
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
	
	
	
	@Override
	public int hashCode() {		// Ãœberschreiben für JUnit benötigt
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + minrep;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override					// Ãœberschreiben für JUnit benötigt
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kunde other = (Kunde) obj;
		if (id != other.id)
			return false;
		if (minrep != other.minrep)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
