package server.communicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketControl {

	public void serverOpen() {

		try {
			ServerSocket serSocket = new ServerSocket(6000);

			while (true) {

				System.out.println("접속 대기중...");
				Socket socket = serSocket.accept();
				System.out.println("클라이언트 연결 ip:" + socket.getInetAddress());

				new ReadThread(socket).start();
				Thread.sleep(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
