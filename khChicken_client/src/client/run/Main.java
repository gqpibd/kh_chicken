package client.run;

import client.communicator.Communicator;
import client.singleton.Singleton;
import client.view.order;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		s.comm.makeConnection();
		new order();
	}

}
