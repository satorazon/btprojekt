class KundeHigh extends Kunde {

	public KundeHigh(int id, String name, int minrep, int minqual) {
		super(id, name, minrep, minqual);
		// TODO Auto-generated constructor stub
	}

	public void erzAuftrag() {
		Auftrag au = null;
		int ran = (int) (Math.random() * 100);
		int geld = ran * 10000;
		ran = (int) (Math.random() * 100);
		geld += ran + 1000;
		int kap = 20;
		kap += (int) (Math.random() * 100);
		int ep = kap + 50;
		au = new Auftrag(minrep, minqual, kap, geld, ep);
	}

}