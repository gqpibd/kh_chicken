package client.run;

import client.communicator.Communicator;
import client.view.mainView;

public class Main {

	public static void main(String[] args) {
		new Communicator().makeConnection();
		new mainView();
		
	}

}
