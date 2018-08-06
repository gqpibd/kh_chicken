package server.run;

import server.dao.MenuDao;
import server.db.DBConnection;
import server.dto.MenuDto;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("hello");
		DBConnection.initConnection();
		//new SocketControl().serverOpen();
		MenuDto dto = new MenuDto("복고치킨", 15000);  
		new MenuDao().insert(dto);
	}

}
