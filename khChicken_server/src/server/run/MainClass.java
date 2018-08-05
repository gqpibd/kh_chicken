package server.run;

import server.communicator.SocketControl;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("hello");
		new SocketControl().serverOpen();
	}

}
