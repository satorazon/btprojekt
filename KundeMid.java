class KundeMid extends Kunde {

	public KundeMid(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		this.name = name;
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 10);
		int rgeld = ran * 10000;
		int lilran = (int) (Math.random() * 10);
		rgeld += (lilran * 10) + 1000;
		double geld = rgeld;
		int kap = ran;
		int mat = kap + (lilran / 2);
		int matsorte = 2;
		int ep = kap + 25;
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