package server.communicator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
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
			System.out.println(menus.size());
			for (int i = 0; i < menus.size(); i++) {
				File f = new File("d:/images/" + menus.get(i).getMenu_name().replaceAll(" ", "_") + ".jpg");
				BufferedImage im = ImageIO.read(f);

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ImageIO.write(im, "jpg", byteArrayOutputStream);
				byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
				oos.write(size);
				oos.write(byteArrayOutputStream.toByteArray());
				oos.flush();
				//int size = (int) f.length();
				//oos.writeInt(size);

			}
			System.out.println("sent");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
