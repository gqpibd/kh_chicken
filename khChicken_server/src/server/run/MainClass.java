package server.run;

import server.communicator.SocketControl;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("hello");
		//DBConnection.initConnection();
		new SocketControl().serverOpen();
		//MenuDto dto = new MenuDto("복고치킨", 15000);  
		//new MenuDao().insert(dto);
	}

}
