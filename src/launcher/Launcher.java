package launcher;

import model.Emergency;

public class Launcher {

	public static void main(String[] args) {
		String serverDBEmergency = "https://nassim-k.fr/api";

		Emergency emergency = new Emergency(serverDBEmergency);
		emergency.run();
	}

}
