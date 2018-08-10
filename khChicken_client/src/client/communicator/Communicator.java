package client.communicator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Communicator {
	public static final int INSERT = 0;
	public static final int SELECT = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;

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
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());

			oos.writeInt(number);
			oos.writeObject(o);

			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendImage(String path) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());

			BufferedImage im = ImageIO.read(new File(path));
			System.out.println(im.toString());
			ImageIO.write(im, "jpg", oos);
			oos.flush();
			oos.close();
			makeConnection();
			System.out.println("이미지 보냄");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Object> receiveMessage() {
		// ObjectInputStream ois = null;
		ArrayList<Object> objList = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

			objList = (ArrayList<Object>) ois.readObject();
		} catch (EOFException e) {
			System.out.println("파일을 다 읽었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return objList;
	}

}
