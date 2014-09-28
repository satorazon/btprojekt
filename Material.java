public class Material {

	int mlow;
	int mmid;
	int mhigh;
	double pmlow;
	double pmmid;
	double pmhigh;

	public void erstellen() {
		double random = (Math.random() * 10) - 5;
		random = Math.round(random * 100) / 100;
		pmlow = 6 + random;
		random = (Math.random() * 10) - 5;
		random = Math.round(random * 100) / 100;
		pmmid = 10 + random;
		random = (Math.random() * 10) - 5;
		random = Math.round(random * 100) / 100;
		pmhigh = 15 + random;
		mlow = (int) (pmhigh * 50);
		mmid = (int) (pmmid * 50);
		mhigh = (int) (pmlow * 50);
	}

}
