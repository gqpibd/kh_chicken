package server.communicator;

<<<<<<< HEAD
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dto.MenuShowDto;
=======
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
>>>>>>> refs/remotes/origin/daseul

public class SocketWriter<Type> {

	public static <Type> void WriteAll(Socket sock, ArrayList<Type> o) {
		ObjectOutputStream oos = null;
		try {

			oos = new ObjectOutputStream(sock.getOutputStream());
			for (int i = 0; i < o.size(); i++) {
				oos.writeObject(o);
			}
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
