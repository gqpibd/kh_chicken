package client.communicator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Communicator {
	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("192.168.30.35", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
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
		PrintWriter pw = null;
		ObjectOutputStream oos = null;
		try {
			OutputStream ops = sock.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(ops));
			pw.println(number);
			pw.flush();

			oos = new ObjectOutputStream(ops);
			oos.writeObject(o);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Object SendAndReceiveMessage() {

		return null;
	}
}
