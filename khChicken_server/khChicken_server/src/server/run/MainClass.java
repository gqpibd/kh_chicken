package server.run;

import server.communicator.SocketControl;
import server.db.DBConnection;

public class MainClass {

	public static void main(String[] args) {

		new DBConnection().getConnection();
		new SocketControl().serverOpen();
	}

}