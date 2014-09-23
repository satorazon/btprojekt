abstract class Kunde {

	int id;
	String name;
	int minrep;
	int minqual;

	public Kunde(int id, String name, int minrep, int minqual) {
		this.id = id;
		this.name = name;
	}

	public void erzAuftrag() {
		// TODO Auto-generated method stub

	}

	/*
	 * public void erzAuftrag() { Auftrag au = null; int ran = (int)
	 * (Math.random() * 5); au = new Auftrag(int minrep, int minqual, int kap,
	 * int geld, int ep); }
	 */

}
