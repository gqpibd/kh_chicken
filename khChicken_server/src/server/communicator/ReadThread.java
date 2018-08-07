package server.communicator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageOutputStream;

import server.singleton.Singleton;

public class ReadThread extends Thread {

	Socket sock;

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		super.run();
		// Singleton s = Singleton.getInstance();
		// ObjectOutputStream oos = null;
		// ObjectInputStream ois = null;
		// // while (true) {
		// try {
		// // sock.getOutputStream();
		// oos = new ObjectOutputStream(sock.getOutputStream());
		// ois = new ObjectInputStream(sock.getInputStream()); // dto받기
		//
		// // int number = ois.readInt();
		// // System.out.println(number);
		//
		// // switch (number) {
		// // case 0: // insert
		// // case 1: // select
		// // case 2: // delete
		// // case 3: // update
		// //
		// // Object obj = ois.readObject();
		// // // 어떤 dto 인지 구분
		// // if (obj instanceof MemberDto) {
		// //
		// // } else if (obj instanceof MenuShowDto) {
		// // System.out.println("MenuShowDto received");
		// // s.getMenuCtrl().execute(number, (MenuShowDto) obj);
		// // } else if (obj instanceof OrderedMenuDto) {
		// //
		// // } else if (obj instanceof ReviewDto) {
		// //
		// // }
		// // break;
		// //
		// // case 4: // menu 불러오기
		// //
		// // case 5: // review 불러오기
		// //
		// // case 6: // 전체매출 불러오기
		// //
		// // case 7: // 내 주문내역 불러오기
		// //
		// // }
		//
		// // // send (받는건 번호+dto 지만 보내는건 한번만 해도됨)
		// // ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		// // oos.writeObject(obj);
		// sleep(100);
		//
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } /*
		// * catch (ClassNotFoundException e) { e.printStackTrace(); }
		// */catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		receiveImage();

	}

	public void receiveImage() {

		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedImage im = null;// = ImageIO.read(ois);
			
			im = ImageIO.read(ois);
			
			
			if (im == null) {
				System.out.println("null");
				return;
			} else {
				System.out.println(im.toString());
				new ImgTester(im);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
