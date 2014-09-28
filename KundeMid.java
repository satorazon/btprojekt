class KundeMid extends Kunde {

	public KundeMid(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		this.name = name;
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 50);
		double geld = ran * 10000;
		int lilran = (int) (Math.random() * 50);
		geld += lilran + 1000;
		int kap = ((ran / 50) * 10) / 2;
		int mat = kap;
		int matsorte = 2;
		int ep = kap + 25;
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, minqual, kap, mat,
				matsorte, geld, ep));
		Kunde.auid++;
	}

}