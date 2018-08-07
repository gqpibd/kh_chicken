package client.run;

import client.communicator.Communicator;
import client.singleton.Singleton;
import client.view.MenuManagementView;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		s.getComm().makeConnection();
		s.getComm().SendMessage(11, s.getMenuCtrl().getMenDao().get(0));
		// dohyeon_client branch test
		new MenuManagementView();
		//new AddMenuView();
		// hello_ hello_ hello_
		
	}

}
