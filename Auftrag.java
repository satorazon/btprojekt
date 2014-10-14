public class Auftrag {

	int auid;
	int rep;
	int kap;
	int mat;
	int matsorte;
	double geld;
	int ep;
	String name;
	Angebot a;
	boolean marke;

	public Auftrag(String name, int auid, int minrep, int kap, int mat,
			int matsorte, double geld, int ep, boolean marke) {
		this.auid = auid;
		this.name = name;
		int ran = (int) (Math.random() * 10);
		double rand = ran / 10.0;
		if (rand > 0.5) {
			rand -= 0.5;
		}
		rand += 1;
		rep = (int) (minrep * rand);
		this.mat = mat;
		this.matsorte = matsorte;
		this.kap = kap;
		this.geld = geld;
		this.ep = ep;
		this.marke = marke;
		a = new Angebot("nobody", geld);
	}

	// public void angebot(int auid, String name, double ang){
	// a
	// }

}
