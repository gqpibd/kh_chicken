package server.run;

import server.communicator.SocketControl;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.db.DBConnection;
import server.singleton.Singleton;

public class MainClass {

	public static void main(String[] args) {
		/*System.out.println("hello");
		new SocketControl().serverOpen();*/
		
		
		
		DBConnection.initConnect();
		new SocketControl().serverOpen();
		
		
	}

}
