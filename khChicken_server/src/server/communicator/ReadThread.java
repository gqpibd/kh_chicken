package server.communicator;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dto.BestSaleMenuDto;
import dto.MemberDto;
import dto.MenuShowDto;
import dto.OrderedMenuDto;
import dto.ReviewDto;
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
		//ObjectOutputStream oos = null;
		//ObjectInputStream ois = null;
		
		try {
			
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(sock.getInputStream()); // dto받기
				int number = ois.readInt();
				System.out.println("number = "+number);
				Object obj = ois.readObject();
				
				switch (number) {
				case 0: // insert
				case 1: // select
				case 2: // delete
				case 3: // update

					
					// 어떤 dto 인지 구분
					if (obj instanceof MemberDto) {
					} else if (obj instanceof MenuShowDto) {
					} else if (obj instanceof OrderedMenuDto) {
						// orderDao에 소켓 넘겨주기. 나머지 작업은 타고타고 들어가서 전송까지 해줄것.
						
					} else if (obj instanceof ReviewDto) {
					}
					break;

				case 4: // menu 불러오기

				case 5: // review 불러오기

				case 6: // 전체매출 불러오기
					s.getOrderCtrl().select(sock);
					break;
				case 7: // 매출별 순위 불러오기
					s.getBestCtrl().select(sock);
					break;
				}
				sleep(100);
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
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
				