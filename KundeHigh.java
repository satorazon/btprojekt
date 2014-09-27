class KundeHigh extends Kunde {

	public KundeHigh(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		this.name = name;
	}

	public void erzAuftrag() {
		int ran = (int) (Math.random() * 100);
		double geld = ran * 10000;
		ran = (int) (Math.random() * 100);
		geld += ran + 1000;
		int kap = 20;
		kap += (int) (Math.random() * 100);
		int mat = kap / 2;
		int matsorte = 3;
		int ep = kap + 50;
		Kunde.au.add(new Auftrag(name, Kunde.auid, minrep, minqual, kap, mat,
				matsorte, geld, ep));
		Kunde.auid++;
	}

}