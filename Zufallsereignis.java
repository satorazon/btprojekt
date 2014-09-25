public class Zufallsereignis {

	String ereignis;
	static double geld;
	double sgeld;
	int rep;

	public Zufallsereignis(int k, int ran) {
		if (k == 1) {
			switch (ran) {
			case 1:
				ereignis = "Ein Kontrolleur wird sich zum Ende des Quartals eines Ihrer Werke ansehen."
						+ "Verbessern Sie Ihre Arbeitsbedingungen für 50.000 (1) oder nehmen Sie eine schlechte Bewertung in kauf -20 Rep(2)? "
						+ "Sie können auch versuchen die Situation mit einem Schmiergeld abzuwenden.(3)";
				geld = 50000;
				rep = 20;
				break;

			case 2:
				ereignis = "blabla";
				geld = 0;
				rep = 0;
				break;

			case 3:
				ereignis = "blabla";
				geld = 0;
				rep = 0;
				break;

			case 4:
				ereignis = "blabla";
				geld = 0;
				rep = 0;
				break;

			case 5:
				ereignis = "blabla";
				geld = 0;
				rep = 0;
				break;

			default:
				ereignis = "blabla"; // muss eigentlich nicht ausgefüllt werden
				geld = 0;
				rep = 0;
				break;
			}
		}
	}

	public String getEreignis() {
		// TODO Auto-generated method stub
		return ereignis;
	}

	public static double getGeld() {
		return geld;
	}
}
