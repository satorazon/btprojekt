public class Auftrag {

	int auid;
	int rep;
	int qual;
	int kap;
	int mat;
	int matsorte;
	double geld;
	int ep;
	String name;
	Angebot a;

	public Auftrag(String name, int auid, int minrep, int minqual, int kap,
			int mat, int matsorte, double geld, int ep) {
		this.auid = auid;
		this.name = name;
		int ran = (int) (Math.random() * 10);
		double rand = ran / 10.0;
		if (rand > 0.5) {
			rand -= 0.5;
		}
		rand += 1;
		rep = (int) (minrep * rand);
		qual = (int) (minqual * rand);
		this.mat = mat;
		this.kap = kap;
		this.geld = geld;
		this.ep = ep;
	}

}
