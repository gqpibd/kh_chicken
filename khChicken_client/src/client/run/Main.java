package client.run;

import client.communicator.Communicator;
import client.dto.MemberDto;
import client.singleton.Singleton;
import client.view.Window_ACount;
import client.view.Window_Loging;
import client.view.Window_Review;

public class Main {

	public static void main(String[] args) {
		
	//	Singleton single = Singleton.getInstance();
	//	single.comm.makeConnection();
		//single.Win_Acount = new Window_ACount();
		//single.Win_Loging = new Window_Loging();
		new Window_Review();
	
	}

}
