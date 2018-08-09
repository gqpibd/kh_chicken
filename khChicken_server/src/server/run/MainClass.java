package server.run;

import server.communicator.SocketControl;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.db.DBConnection;

public class MainClass {

	public static void main(String[] args) {
		/*System.out.println("hello");
		new SocketControl().serverOpen();*/
		
		
		
		DBConnection.initConnect();
<<<<<<< HEAD
		
		new SocketControl().serverOpen();
=======
		new SocketControl().serverOpen();
		
		
		
>>>>>>> branch 'seungji_server' of https://github.com/gqpibd/kh_semi.git
		
	}

}
