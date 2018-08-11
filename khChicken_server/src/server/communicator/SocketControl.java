package server.communicator;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketControl {

	public void serverOpen() {
		ServerSocket serSocket = null;
		try {
<<<<<<< HEAD
			ServerSocket serSocket = new ServerSocket(6000);
			//List<Socket> sockList = new ArrayList<Socket>();
			
			while(true) {
				
=======
			serSocket = new ServerSocket(6000);

			while (true) {

>>>>>>> refs/remotes/origin/도현+다슬+승지
				System.out.println("접속 대기중...");
				Socket socket = serSocket.accept();
				System.out.println("클라이언트 연결 ip:" + socket.getInetAddress());

				new ReadThread(socket).start();
<<<<<<< HEAD
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
=======
				Thread.sleep(100);
			}
		} catch (IOException e) {
>>>>>>> refs/remotes/origin/도현+다슬+승지
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				serSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
