package client.communicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import client.dto.MemberDto;
import client.dto.MenuDto;
import client.dto.MenuShowDto;
import client.dto.OrderedMenuDto;
import client.dto.ReviewDto;

public class Communicator {
	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");

			
			// reader
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			Object obj = ois.readObject();
			
			//어떤 dto 인지 구분
			if (obj instanceof MemberDto) {
				
			} else if (obj instanceof MenuDto) {
				
			} else if (obj instanceof MenuShowDto) {
				
			} else if (obj instanceof OrderedMenuDto) {
				
			} else if (obj instanceof ReviewDto) {
				
			}
			
			
			// writer
			// 각 클래스 내부에서 구현
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
