package client.run;

import client.singleton.Singleton;
<<<<<<< HEAD
import client.view.Window_Review;
=======
import client.view.MainView;
>>>>>>> refs/remotes/origin/도현+다슬+승지

public class Main {

	public static void main(String[] args) {
<<<<<<< HEAD
=======
		Singleton s = Singleton.getInstance(); 
		s.getComm().makeConnection(); // 소켓 연결을 먼저 한다.
		new MainView();
>>>>>>> refs/remotes/origin/도현+다슬+승지
		
		Singleton single = Singleton.getInstance();
		single.comm.makeConnection();
		
		//single.Win_Acount = new Window_ACount();
		//single.Win_Loging = new Window_Loging();
		new Window_Review();
	
	}

}
