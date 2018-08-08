package client.run;

import client.communicator.Communicator;
import client.singleton.Singleton;
import client.view.ManageView;
import client.view.SaleManageView;

public class Main {

	public static void main(String[] args) {
		
		Singleton s = Singleton.getInstance();
		s.comm.makeConnection();
		
		
		new ManageView().setVisible(true);
		
		
		
		
	}

}
