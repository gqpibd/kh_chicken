package server.communicator;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketControl {
	
	
	
	public void serverOpen() {
		
		try {
			ServerSocket serSocket = new ServerSocket(6000);
			//List<Socket> sockList = new ArrayList<Socket>();
			
			while(true) {
				
				System.out.println("접속 대기중...");
				Socket socket = serSocket.accept();
				//sockList.add(socket);
				System.out.println("클라이언트 연결 ip:"+socket.getInetAddress());
				
				new ReadThread(socket).start();
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	

}
