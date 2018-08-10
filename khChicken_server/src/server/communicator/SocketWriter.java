package server.communicator;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketWriter<Type> {

	public static <Type> void Write(Socket sock, Type o) {
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