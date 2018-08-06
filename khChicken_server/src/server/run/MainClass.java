package server.run;

import server.dao.MenuDao;
import server.db.DBConnection;

public class MainClass {

	public static void main(String[] args) {
		/*System.out.println("hello");
		new SocketControl().serverOpen();*/
		
		DBConnection.initConnect();
		
		new MenuDao().insert();
		
		
		//new MenuDao().select();
		
	}

}
