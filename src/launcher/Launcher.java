package launcher;

import model.Emergency;

public class Launcher {

	public static void main(String[] args) {
		String endpointDBEmergency = "TODO";

		Emergency emergency = new Emergency(endpointDBEmergency);
		emergency.run();
	}

}
