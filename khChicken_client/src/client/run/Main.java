package client.run;

import client.singleton.Singleton;
import client.view.mainView;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		
		s.comm.makeConnection();
		new mainView();
		
	}

}
