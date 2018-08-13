package server.communicator;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		ObjectInputStream ois = null;

		try {
			while (true) {
				ois = new ObjectInputStream(sock.getInputStream()); // dto받기

				int number = ois.readInt();		
				Object obj = ois.readObject();
				
				if (obj instanceof MemberDto) { 			// 로그인, 회원가입
					s.getMemCtrl().execute(number, (MemberDto) obj, sock);					
				} else if (obj instanceof MenuShowDto) { 	// 메뉴 보여주기, 추가 삭제
					s.getMenuCtrl().execute(number, (MenuShowDto) obj, sock);
				} else if ((obj instanceof OrderedMenuDto)) { // 주문하기, 매출관리
					s.getOrderCtrl().execute(number, (OrderedMenuDto) obj, sock);
				} else if (obj instanceof ReviewDto) { 		// 리뷰보기
					s.getRevCtrl().execute(number, (ReviewDto) obj, sock);
				} 
				sleep(100);
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("소켓이 닫혔습니다");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
