package client.service;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.service.interfaces.ReviewServiceImpl;
import client.singleton.Singleton;
import dto.ReviewDto;

public class ReviewService implements ReviewServiceImpl{
	private final int WRITABLE_REVIEW = 4;
	private final int SELECT_BY_ID = 5;

	List<ReviewDto> rList = new ArrayList<ReviewDto>();
	Socket sock;

	public ReviewService() {
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

	public List<ReviewDto> selectByUserId(ReviewDto dto) {	// 특정 사용자에 대한 리뷰 정보를 불러온다
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

	public List<ReviewDto> getList() {
		return rList;
	}

	public ReviewDto WritableReview(ReviewDto rDto) { // 리뷰를 작성 할 수 있는 과거 주문 내역을 불러온다. 
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(WRITABLE_REVIEW, rDto);
		rDto = (ReviewDto) comm.receiveObject();
		
		return rDto;
	}


}
