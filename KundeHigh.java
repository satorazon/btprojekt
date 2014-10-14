class KundeHigh extends Kunde {

	public KundeHigh(int id, String name, int minrep) {
		super(id, name, minrep);
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 10);
		int rgeld = ran * 100000;
		int lilran = (int) (Math.random() * 100);
		rgeld += lilran + 10000;
		double geld = rgeld;
		int kap = ran;
		int mat = kap + (lilran / 2);
		int matsorte = 3;
		int ep = kap + 50;
		boolean marke = false;
		if (BT.runde >= 10) {
			ran = (int) (Math.random() * 3);
			if ((ran + 1) > 2) {
				marke = true;
			}
		}
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, kap, mat, matsorte,
				geld, ep, marke));
		Kunde.auid++;
	}
}
