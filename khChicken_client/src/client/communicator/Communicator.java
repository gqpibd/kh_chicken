package client.communicator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	}
	
	
	public void SendMessage(int number, Object o) {
		
		//PrintWriter pw = null;
		ObjectOutputStream oos = null;
		
		
		try {
			OutputStream ops = sock.getOutputStream();
			//pw = new PrintWriter(new OutputStreamWriter(ops));
			//pw.println(number);
			//pw.flush();
			
			oos = new ObjectOutputStream(ops);
			oos.writeInt(number);
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*try {
				//pw.close();
				//oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	
	
	public List<OrderedMenuDto> receiveMessage() {
		
		Singleton s = Singleton.getInstance();
		Object obj = null;
		
			try {
				// receive
				InputStream input = sock.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(input); // dto받기
				obj = ois.readObject();
//				System.out.println(obj.toString());
				
				
				
				
				
//				switch (number) {
//				case 0:	//insert	
//				case 1:	//select
//				case 2:	//delete
//				case 3:	//update
//					
//					obj = ois.readObject();
//					
//					//어떤 dto 인지 구분
//					if (obj instanceof MemberDto) {	
//
//					} else if (obj instanceof MenuDto) {
//
//					} else if (obj instanceof MenuShowDto) {
//
//					} else if (obj instanceof OrderedMenuDto) {
//
//					} else if (obj instanceof ReviewDto) {
//
//					}
//					break;
//
//				case 4: // menu 불러오기
//					
//				case 5: // review 불러오기
//					
//				case 6: // 전체매출 불러오기
//					obj = s.ctrlOrder.select();
//					break;
//				case 7: // 내 주문내역 불러오기
//
//				}
//
//				// send (받는건 번호+dto 지만 보내는건 한번만 해도됨)
//				ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
//				oos.writeObject(obj);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
	

			return s.ordCtrl.select(obj);
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
