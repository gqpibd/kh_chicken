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

import server.dto.MemberDto;
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

		while (true) {
			try {
				
				//input. output 스트림 생성, printwriter 생성(번호 받기용)
				InputStream input = sock.getInputStream();
				OutputStream out = sock.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

				pw.println("Welcome");
				pw.flush();

				// receive
				BufferedReader br = new BufferedReader(new InputStreamReader(input)); // client에서 받은 번호 input

				Object obj = null;
				int number;

				number = Integer.parseInt(br.readLine());
				System.out.println("received: " + number);
				
				switch (number) {
				case 0:	//insert	
				case 1:	//select
				case 2:	//delete
				case 3:	//update
					ObjectInputStream ois = new ObjectInputStream(input); // dto받기
					obj = ois.readObject();
					
					//어떤 dto 인지 구분
					if (obj instanceof MemberDto) {	

					} else if (obj instanceof MenuDto) {

					} else if (obj instanceof MenuShowDto) {

					} else if (obj instanceof OrderedMenuDto) {

					} else if (obj instanceof ReviewDto) {

					}
					break;

				case 4: // menu 불러오기
					
				case 5: // review 불러오기
					
				case 6: // 전체매출 불러오기
					
				case 7: // 내 주문내역 불러오기

				}

				// send (받는건 번호+dto 지만 보내는건 한번만 해도됨)
				ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
				oos.writeObject(obj);
				sleep(100);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}