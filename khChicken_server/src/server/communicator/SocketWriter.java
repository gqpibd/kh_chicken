package server.communicator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SocketWriter<Type> {

	public static  void WriteAll(Socket sock, ArrayList<Object> o) {
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
	
	public void sendImage(Socket sock, String path) {
		ObjectOutputStream oos = null;
		try {

			oos = new ObjectOutputStream(sock.getOutputStream());

			BufferedImage im = ImageIO.read(new File(path));
			System.out.println(im.toString());
			ImageIO.write(im, "jpg", oos);
			oos.flush();
					
			oos.close();
			//makeConnection();
			System.out.println("sent");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
