package client.communicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dto.MenuShowDto;

public class Communicator {
	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("192.168.30.5", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");

			//BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			//insert orderedMenuDto 
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MenuShowDto> getShowMenu() {
		
		List<MenuShowDto> showDtoList = new ArrayList<>();
		Object obj = showDtoList;
		//server에 요청 
		
		OutputStream output;
		
		try {
			
			output = sock.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);	//보내고
			oos.writeInt(2);
			oos.writeObject(obj);
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// receive 
		InputStream input;
		
		try {
			
			input = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(input); //dto받고
			obj = ois.readObject();
			showDtoList = (List<MenuShowDto>)obj;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return showDtoList;
		
		
	}
	
	
	
}
