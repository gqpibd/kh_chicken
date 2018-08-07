package client.run;

import client.communicator.Communicator;
import client.view.ManageView;
import client.view.SaleManageView;

public class Main {

	public static void main(String[] args) {
		
		new Communicator().makeConnection();
		
		new ManageView().setVisible(true);
		
		
		
		
	}

}
