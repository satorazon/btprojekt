public class Material {

	int mlow;
	int mmid;
	int mhigh;
	double pmlow;
	double pmmid;
	double pmhigh;

	public void erstellen() {
		double random = (Math.random() * 10) - 5;
		pmlow = 6 + random;
		random = (Math.random() * 10) - 5;
		pmmid = 10 + random;
		random = (Math.random() * 10) - 5;
		pmhigh = 15 + random;
		mlow = (int) (pmhigh * 10 * BT.p.size());
		mmid = (int) (pmmid * 10 * BT.p.size());
		mhigh = (int) (pmlow * 10 * BT.p.size());
	}

}
