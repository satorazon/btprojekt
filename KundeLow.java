class KundeLow extends Kunde {

	public KundeLow(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		this.name = name;
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 10);
		double geld = ran * 10000;
		int lilran = (int) (Math.random() * 10);
		geld += lilran * 1000;
		int kap = (ran * 10) / 3;
		int mat = kap;
		int matsorte = 1;
		int ep = kap;
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, minqual, kap, mat,
				matsorte, geld, ep));
		Kunde.auid++;
	}

}
