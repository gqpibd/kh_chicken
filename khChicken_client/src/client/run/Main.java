package client.run;

import client.singleton.Singleton;
import client.view.Window_Review;

public class Main {

	public static void main(String[] args) {
		
		Singleton single = Singleton.getInstance();
		single.comm.makeConnection();
		
		//single.Win_Acount = new Window_ACount();
		//single.Win_Loging = new Window_Loging();
		new Window_Review();
	
	}

}
