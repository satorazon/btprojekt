import java.util.Scanner;

public class Player {

	int rep;
	boolean sgv;
	int id;
	static String name;
	static int geld;

	public Player(int id) {
		this.id = id;
	}

	public static void setName(String n) {
		name = n;
	}

	public int getRep() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getEP() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setGeld(int i) {
		geld = i;
	}

	public int getGeld() {
		return geld;
	}

	public void getZufall() {
		Zufallsereignis ze = null;
		String ereignis;
		boolean e = false;
		rep = rep / 10;
		int random = (int) (Math.random() * 10);
		if (random * 2 > rep) {
			if (rep < 6) {
				int k = 1;
				int ran = (int) (Math.random() * 5);
				ze = new Zufallsereignis(k, ran);
			}
		}
		if (random == rep) {
			if (e == false) {
				int k = 0;
				int ran = (int) (Math.random() * 5) + 1;
				ze = new Zufallsereignis(k, ran);
			}
		}
		ereignis = ze.getEreignis();
		System.out.println(name);

		// Todo: Infos Anzeigen (Geld, Rep, Kapazität, usw)

		System.out.println(ereignis);
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		if (input == 1) {
			geld = geld - ze.geld;
			rep += 10; // Könnte noch angepasst werden evtl. auswirkung auf
						// Qualität oder Arbeitsbed.?
		} else if (input == 3) {
			geld -= ze.sgeld;
			sgv = true;
		} else {
			rep -= ze.rep;
		}

	}
}
