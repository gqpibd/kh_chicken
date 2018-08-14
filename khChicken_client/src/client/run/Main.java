package client.run;

<<<<<<< HEAD
import client.singleton.Singleton;
=======
import client.communicator.Communicator;
import client.singleton.Singleton;
import client.view.order;
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git

public class Main {

	public static void main(String[] args) {
<<<<<<< HEAD
		Singleton s = Singleton.getInstance(); 
		s.getComm().makeConnection(); // 소켓 연결을 먼저 한다.
		s.getMainView().setVisible(true);
		
=======
		Singleton s = Singleton.getInstance();
		s.comm.makeConnection();
		new order();
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
	}

}
