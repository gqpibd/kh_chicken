package server.communicator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWriter<T> {

	// 데이터를 전송할 때 쓰인다. 뭘 보낼지 모르기 때문에 generic을 사용했다.
	public static <T> void Write(Socket sock, T o) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
