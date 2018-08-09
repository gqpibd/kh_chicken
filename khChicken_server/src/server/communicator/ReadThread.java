package server.communicator;

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
import java.net.Socket;
import java.util.HashMap;

import client.dto.MemberDto;
import server.dto.MenuDto;
import server.dto.MenuShowDto;
import server.dto.OrderedMenuDto;
import server.dto.ReviewDto;
import server.singleton.Singleton;

public class ReadThread extends Thread {

	Socket sock;

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		super.run();
		Singleton s = Singleton.getInstance();

		ObjectInputStream ois = null;
		
			try {
				while (true) {
				//input. output 스트림 생성, printwriter 생성(번호 받기용)
				InputStream input = sock.getInputStream();
				OutputStream ouyput = sock.getOutputStream();
				OutputStream out = sock.getOutputStream();
				
				// receive
				BufferedReader br = new BufferedReader(new InputStreamReader(input)); // client에서 받은 번호 input
				int number = Integer.parseInt(br.readLine());
					
				PrintWriter pw = new PrintWriter(ouyput);
				pw.println("1");
				pw.flush();
				
				
				Object obj = null;
					ois = new ObjectInputStream(input); // dto받기
					obj = ois.readObject();
					MemberDto dto = (MemberDto)obj;
					
					//어떤 dto 인지 구분
					if (obj instanceof MemberDto) {
						dto = (MemberDto)obj;
						
						switch (number) {
						case 0:	//insert
							System.out.println(dto.getId());
							s.ctrlMember.insert(dto);
							break;
						case 1:	//select
							boolean join = s.ctrlMember.select(dto);
							ObjectOutputStream oos = new ObjectOutputStream(ouyput);
							oos.writeObject(join);
							oos.flush();
							


							boolean Loging = s.ctrlMember.select_loging(dto);
							oos = new ObjectOutputStream(out);
							System.out.println(Loging);
							oos.writeObject(Loging);
							oos.flush();
							break;
						}

					} else if (obj instanceof MenuDto) {

					} else if (obj instanceof MenuShowDto) {

					} else if (obj instanceof OrderedMenuDto) {

					} else if (obj instanceof ReviewDto) {

					}
					
				/*case 4: // menu 불러오기
					
				case 5: // review 불러오기
					
				case 6: // 전체매출 불러오기
					
				case 7: // 내 주문내역 불러오기
*/

				// send (받는건 번호+dto 지만 보내는건 한번만 해도됨)
			//	ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			//	oos.writeObject(obj);
			//	sleep(100);
					
				

				}
			} catch (FileNotFoundException e) {
			//	e.printStackTrace();
			} catch (IOException e) {
			//	e.printStackTrace();
			} catch (ClassNotFoundException e) {
			//	e.printStackTrace();
			} /*catch (InterruptedException e) {
				e.printStackTrace();
			}*/ 
			
		}

	
}
