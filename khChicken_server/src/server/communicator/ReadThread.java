package server.communicator;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import dto.ReviewDto;
import server.dto.MemberDto;
import server.dto.MenuDto;
import server.dto.MenuShowDto;
import server.dto.OrderedMenuDto;

import server.singleton.Singleton;

public class ReadThread extends Thread {

	Socket sock;

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		super.run();
		Singleton s = Singleton.getInstance();

		while (true) {
			try {
				
				
				InputStream input = sock.getInputStream();
				OutputStream out = sock.getOutputStream();
				ObjectInputStream ois = new ObjectInputStream(input);
				int number = ois.readInt();
				

				
				Object obj = null;
				ois = new ObjectInputStream(input);
				obj = ois.readObject();
				
			/*	
				switch (number) {
				case 0:	//insert	
				case 1:	//select
				case 2:	//delete
				case 3:	//update
			*/
				Singleton single = Singleton.getInstance();
					if (obj instanceof dto.MemberDto) {	
						dto.MemberDto dto = (dto.MemberDto)obj;
						obj = single.ctrlMember.Choice(dto, number);
						ObjectOutputStream oos = new ObjectOutputStream(out);
						oos.writeObject(obj);
						oos.flush();
						
					} else if (obj instanceof MenuShowDto) {

					} else if (obj instanceof OrderedMenuDto) {

					} else if (obj instanceof dto.ReviewDto) {
						ReviewDto dto = (ReviewDto) obj;
						List<dto.ReviewDto> list = single.ctrlReview.Choice(number,dto);
						if(list == null) {
						}else {
						ObjectOutputStream oos = new ObjectOutputStream(out);
						oos.writeObject(list);
						oos.flush();
						System.out.println(list+"1ggggg");
						}
					}
		/*			break;

				case 4: // menu 遺덈윭�삤湲�
					
				case 5: // review 遺덈윭�삤湲�
					
				case 6: // �쟾泥대ℓ異� 遺덈윭�삤湲�
					
				case 7: // �궡 二쇰Ц�궡�뿭 遺덈윭�삤湲�

				}*/

				// send (諛쏅뒗嫄� 踰덊샇+dto 吏�留� 蹂대궡�뒗嫄� �븳踰덈쭔 �빐�룄�맖)

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
}
