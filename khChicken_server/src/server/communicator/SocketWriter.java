package server.communicator;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketWriter<T> {

	public static <T> void Write(Socket sock, T o) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(o);
			oos.flush();
			System.out.println("write");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
