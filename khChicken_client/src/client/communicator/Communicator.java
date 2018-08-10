package client.communicator;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import dto.OrderedMenuDto;

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
	
	}
	
	
	public void SendMessage(int number, Object o) {
		
		//PrintWriter pw = null;
		ObjectOutputStream oos = null;
		
		
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			
			oos.writeInt(number);
			oos.writeObject(o);
			
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public ArrayList<Object> receiveMessage() {
		
		ArrayList<Object> objList = new ArrayList<>();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

			objList = (ArrayList<Object>) ois.readObject();
			System.out.println("사이즈:" + objList.size());
		} catch (EOFException e) {
			System.out.println("파일을 다 읽었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return objList;
		
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
