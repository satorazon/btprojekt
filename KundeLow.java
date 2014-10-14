class KundeLow extends Kunde {

	public KundeLow(int id, String name, int minrep) {
		super(id, name, minrep);
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 10);
		int rgeld = ran * 10000;
		int lilran = (int) (Math.random() * 10);
		rgeld += lilran * 1000;
		double geld = rgeld;
		int kap = ran + 1;
		int mat = kap + (lilran / 2);
		int matsorte = 1;
		int ep = kap + (ran / 2);
		boolean marke = false;
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, kap, mat, matsorte,
				geld, ep, marke));
		Kunde.auid++;
	}

}
