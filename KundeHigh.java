class KundeHigh extends Kunde {

	public KundeHigh(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		this.name = name;
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 100);
		double geld = ran * 10000;
		int lilran = (int) (Math.random() * 100);
		geld += lilran + 1000;
		int kap = ran;
		int mat = kap;
		int matsorte = 3;
		int ep = kap + 50;
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, minqual, kap, mat,
				matsorte, geld, ep));
		Kunde.auid++;
	}

}