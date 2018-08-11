package server.communicator;

<<<<<<< HEAD
=======
import java.io.EOFException;
>>>>>>> refs/remotes/origin/도현+다슬+승지
import java.io.FileNotFoundException;
import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStream;
=======
>>>>>>> refs/remotes/origin/도현+다슬+승지
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
<<<<<<< HEAD
import java.io.OutputStream;
=======
>>>>>>> refs/remotes/origin/도현+다슬+승지
import java.net.Socket;
import java.util.List;

<<<<<<< HEAD
=======
import dto.BestSaleMenuDto;
import dto.MemberDto;
>>>>>>> refs/remotes/origin/도현+다슬+승지
import dto.MenuShowDto;
import dto.OrderedMenuDto;
import dto.ReviewDto;
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
		ObjectInputStream ois = null;

<<<<<<< HEAD
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
=======
		try {
			while (true) {
				ois = new ObjectInputStream(sock.getInputStream()); // dto받기

				int number = ois.readInt();
				System.out.println(number);

>>>>>>> refs/remotes/origin/도현+다슬+승지
				switch (number) {
<<<<<<< HEAD
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
						
=======
				case 0: // insert
				case 1: // select
				case 2: // delete
				case 3: // update

					Object obj = ois.readObject();
					// 어떤 dto 인지 구분
					if (obj instanceof MemberDto) {
>>>>>>> refs/remotes/origin/도현+다슬+승지
					} else if (obj instanceof MenuShowDto) {
						System.out.println("MenuShowDto received");
						s.getMenuCtrl().execute(number, (MenuShowDto) obj, sock);
					} else if (obj instanceof OrderedMenuDto) {
<<<<<<< HEAD

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
=======
						// orderDao에 소켓 넘겨주기. 나머지 작업은 타고타고 들어가서 전송까지 해줄것.
						
					} else if (obj instanceof ReviewDto) {
>>>>>>> refs/remotes/origin/도현+다슬+승지
					}
		/*			break;

<<<<<<< HEAD
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
=======
				case 4: // menu 불러오기

				case 5: // review 불러오기

				case 6: // 전체매출 불러오기
					s.getOrderCtrl().select(sock);
					break;
				case 7: // 매출별 순위 불러오기
					s.getBestCtrl().select(sock);
					break;
				}
				sleep(100);
>>>>>>> refs/remotes/origin/도현+다슬+승지
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("소켓이 닫혔습니다");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
				