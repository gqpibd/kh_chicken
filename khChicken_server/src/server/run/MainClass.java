package server.run;

import server.communicator.SocketControl;
import server.db.DBConnection;

public class MainClass {

	public static void main(String[] args) {		
		DBConnection.initConnection();
		
		new SocketControl().serverOpen();
		//MenuDto dto = new MenuDto("복고치킨", 15000);  
		//new MenuDao().insert(dto);
	}

}
