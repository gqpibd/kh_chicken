package server.communicator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		while (true) {
			try {
				Singleton s = Singleton.getInstance();

				// receive
				ObjectInputStream ois = new ObjectInputStream(sock.getInputStream()); // dto받기
				BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // 번호받기

				Object obj = null;
				int number;
				number = Integer.parseInt(br.readLine());

				switch (number) {
				case 0:
				case 1:
				case 2:
				case 3:

					obj = ois.readObject();
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

				// send
				ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
				oos.writeObject(obj);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
