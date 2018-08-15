package client.service;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.ReviewDto;

public class ReviewDao {
	private final int WRITABLE_REVIEW = 4;
	private final int SELECT_BY_ID = 5;

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
		rList = (ArrayList<ReviewDto>) comm.receiveObject();
		return rList;
	}

	public List<ReviewDto> selectByUserId(ReviewDto dto) {		
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(SELECT_BY_ID, dto);
		rList.clear(); // 일단 내용일 있을지도 모르니 비워준다.
		rList = (ArrayList<ReviewDto>) comm.receiveObject();
		return rList;
	}

	public void update(ReviewDto dto) { // 작성한 리뷰를 등록한다. 기존 구매 내역에 추가됨.
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.UPDATE, dto);
		
	}

	public void delete() {

	}

	public List<ReviewDto> getList() {
		System.out.println(rList.size());
		return rList;
	}

	public ReviewDto WritableReview(ReviewDto rDto) {
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(WRITABLE_REVIEW, rDto);
		rDto = (ReviewDto) comm.receiveObject();
		
		return rDto;
	}


}
