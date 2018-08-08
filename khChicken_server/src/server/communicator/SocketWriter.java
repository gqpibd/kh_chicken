package server.communicator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dto.MenuShowDto;

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

	public static void sendImages(Socket sock, ArrayList<MenuShowDto> menus) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			for (int i = 0; i < menus.size(); i++) {
				BufferedImage im = ImageIO.read(new File("d:/images/"+menus.get(i).getMenu_name().replaceAll(" ", "_") + ".jpg"));
				System.out.println(im.toString());
				ImageIO.write(im, "jpg", oos);
				oos.flush();
			}
			
			oos.close();
			// makeConnection();
			System.out.println("sent");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
