import java.util.Scanner;
import java.util.Vector;

public class BT {

	public static void main(String[] args) {
		spielerErstellen();
		int i = 0;
		while(true){
			i++;
			phaseone();
		}
	}

	private static void phaseone() {
		// TODO Auto-generated method stub
		
	}

	private static void spielerErstellen() {
		boolean newp = true;
		int id = 0;
		Vector<Player> v = new Vector<Player>();
		do {
			id++;
			v.add(new Player(id));
			System.out.println("Player Name");
			Scanner sc = new Scanner(System.in);
			String n = sc.nextLine();
			Player.setName(n);
			if (id >= 2) {
				System.out.println("Another Player? y/n");
				String a = sc.nextLine();
				sc.close();
				if (a.contentEquals("y")) {
					newp = true;
				} else {
					newp = false;
				}
			} else {
				newp = true;
			}
		} while (newp = true);
	}

}
