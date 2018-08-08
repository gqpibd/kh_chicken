package client.communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import client.singleton.Singleton;

public class Communicator {
	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");
			
			Singleton single = Singleton.getInstance();
			single.memCtrl.Sockdao(sock);
			
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
