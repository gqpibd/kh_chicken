package client.communicator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class Communicator {
	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");

			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(sock.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendMessage(int number, Object o) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
//		try {
//			oos = new ObjectOutputStream(sock.getOutputStream());
//			ois = new ObjectInputStream(sock.getInputStream());
//
//			oos.writeInt(number);
//			oos.writeObject(o);
//			oos.flush();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// try {
//			// oos.close();
//			// } catch (IOException e) {
//			// e.printStackTrace();
//			// }
//		}
		sendImage();
		sendImage();
		
		//sendImage();

	}

	public void sendImage() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			
			oos = new ObjectOutputStream(sock.getOutputStream());

			BufferedImage im = ImageIO.read(new File("d:\\images\\aa.jpg"));
			System.out.println(im.toString());
			ImageIO.write(im, "jpg", oos);
			oos.close();
			makeConnection();
			System.out.println("sent");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object SendAndReceiveMessage() {

		return null;
	}
}
