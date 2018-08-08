package client.communicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import client.dto.MemberDto;
import client.dto.MenuDto;
import client.dto.MenuShowDto;
import client.dto.OrderedMenuDto;
import client.dto.ReviewDto;
import client.singleton.Singleton;
import client.view.SaleManageView;

public class Communicator {
	private Socket sock;
	

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");
			
		} catch (IOException e) {
					e.printStackTrace();
		}
			Singleton s = Singleton.getInstance();
	
	}
	
	
	public void SendMessage(int number, Object o) {
		
		PrintWriter pw = null;
		ObjectOutputStream oos = null;
		
		
		try {
			OutputStream ops = sock.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(ops));
			pw.println(number);
			pw.flush();
			
			oos = new ObjectOutputStream(ops);
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Object SendAndReceiveMessage() {
		
		return null;
	}
			
			
			/*OutputStream out = sock.getOutputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
				
			
			
			
			// reader
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			Object obj = ois.readObject();
			
			//어떤 dto 인지 구분
			if (obj instanceof MemberDto) {
				
			} else if (obj instanceof MenuDto) {
				
			} else if (obj instanceof MenuShowDto) {
				
			} else if (obj instanceof OrderedMenuDto) {
				pw.println("6");
				pw.flush();
				new SaleManageView().obj = obj;
			} else if (obj instanceof ReviewDto) {
				
			}
			
			
			
			// writer
			// 각 클래스 내부에서 구현
			

		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/



}
