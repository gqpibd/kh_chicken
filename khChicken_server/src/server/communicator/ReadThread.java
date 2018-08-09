package server.communicator;

import java.io.BufferedReader;
import java.io.EOFException;
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
import java.net.SocketException;

import server.dao.MenuDao;
import dto.MemberDto;
import dto.MenuDto;
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
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(sock.getInputStream()); // dto받기
			while (true) {
				int number = ois.readInt();
				System.out.println(number);

				switch (number) {
				case 0: // insert
				case 1: // select
				case 2: // delete
				case 3: // update

					Object obj = ois.readObject();
					// 어떤 dto 인지 구분
					if (obj instanceof MemberDto) {

					} else if (obj instanceof MenuShowDto) {
						System.out.println("MenuShowDto received");
						s.ctrlMenu.getShowMenu(sock);
						
					} else if (obj instanceof OrderedMenuDto) {

					} else if (obj instanceof ReviewDto) {

					}
					break;

				case 4: // menu 불러오기

				case 5: // review 불러오기

				case 6: // 전체매출 불러오기

				case 7: // 내 주문내역 불러오기

				}
				sleep(100);
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SocketException e){
			System.out.println("소켓이 닫힙니다");
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

	}

}
