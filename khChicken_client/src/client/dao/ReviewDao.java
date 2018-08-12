package client.dao;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
 
import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.ReviewDto;

public class ReviewDao {

	List<ReviewDto> rList = new ArrayList<ReviewDto>();
	Socket sock;

	public ReviewDao() {
	}

	public void SockDao(Socket sock) {
		this.sock = sock;

	}

	public void insert(ReviewDto dto) {

	}

	public List<ReviewDto> select(String menuName) { // 특정 메뉴에 대한 리뷰 정보를 불러온다
		ReviewDto dto = new ReviewDto();
		dto.setMenuName(menuName); 

		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT, dto);
		rList.clear(); // 일단 내용일 있을지도 모르니 비워준다.
		ArrayList<Object> resultList = comm.receiveMessage();
		System.out.println(resultList);
		for (int i = 0; i < resultList.size(); i++) {
			// 받은 dto 형식에 맞게 변환해 저장
			rList.add((ReviewDto) resultList.get(i));
		}
		return rList;
	}

	public void update(ReviewDto dto) { // 작성한 리뷰를 등록한다. 기존 구매 내역에 추가됨.
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.UPDATE, dto);
	}

	public void delete() {

	}

	public List<ReviewDto> getList() {
		return rList;
	}

}
