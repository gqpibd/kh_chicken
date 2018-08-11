package server.run;

import server.communicator.SocketControl;
import server.db.DBConnection;

public class MainClass {

<<<<<<< HEAD
	public static void main(String[] args) {

		new DBConnection().getConnection();
=======
	public static void main(String[] args) {		
		DBConnection.initConnection();
>>>>>>> refs/remotes/origin/도현+다슬+승지
		new SocketControl().serverOpen();

	}

}
