package server.run;

import server.communicator.SocketControl;
import server.dao.MenuDao;
import server.db.DBConnection;

public class MainClass {

	public static void main(String[] args) {
		/*System.out.println("hello");
		new SocketControl().serverOpen();*/
		
		DBConnection.initConnect();
		
		new SocketControl().serverOpen();
		
	}

}
