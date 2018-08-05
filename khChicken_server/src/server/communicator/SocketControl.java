package server.communicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketControl {
	
	
	
	public void serverOpen() {
		
		try {
			ServerSocket serSocket = new ServerSocket(9000);
			//List<Socket> sockList = new ArrayList<Socket>();
			
			while(true) {
				Socket socket = null;
				System.out.println("접속 대기중...");
				socket = serSocket.accept();
				//sockList.add(socket);
				
				System.out.println("클라이언트 연결 ip:"+socket.getInetAddress());
				
				new ReadThread(socket).start();
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	

}
