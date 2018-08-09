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

import server.dto.MemberDto;
import server.dto.MenuDto;
import server.dto.MenuShowDto;
import server.dto.OrderedMenuDto;
import server.dto.ReviewDto;
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
				
				//input. output �뒪�듃由� �깮�꽦, printwriter �깮�꽦(踰덊샇 諛쏄린�슜)
				InputStream input = sock.getInputStream();
				OutputStream out = sock.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

				pw.println("Welcome");
				pw.flush();
				// receive
				BufferedReader br = new BufferedReader(new InputStreamReader(input)); // client�뿉�꽌 諛쏆� 踰덊샇 input

				Object obj = null;
				int number;

				number = Integer.parseInt(br.readLine());
				System.out.println("received: " + number);
				
				switch (number) {
				case 0:	//insert	
				case 1:	//select
				case 2:	//delete
				case 3:	//update
					ObjectInputStream ois = new ObjectInputStream(input); // dto諛쏄린
					obj = ois.readObject();
					
					//�뼱�뼡 dto �씤吏� 援щ텇
					if (obj instanceof MemberDto) {	

					} else if (obj instanceof MenuDto) {

					} else if (obj instanceof MenuShowDto) {

					} else if (obj instanceof OrderedMenuDto) {

					} else if (obj instanceof ReviewDto) {

					}
					break;

				case 4: // menu 遺덈윭�삤湲�
					
				case 5: // review 遺덈윭�삤湲�
					
				case 6: // �쟾泥대ℓ異� 遺덈윭�삤湲�
					
				case 7: // �궡 二쇰Ц�궡�뿭 遺덈윭�삤湲�

				}

				// send (諛쏅뒗嫄� 踰덊샇+dto 吏�留� 蹂대궡�뒗嫄� �븳踰덈쭔 �빐�룄�맖)
				ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
				oos.writeObject(obj);
				sleep(100);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
