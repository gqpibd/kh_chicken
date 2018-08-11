package client.controller;

import java.util.List;

import javax.swing.JFrame;

import client.dao.ReviewDao;
import client.singleton.Singleton;
import client.view.Window_Review;
import dto.ReviewDto;

public class ReviewController {

	private ReviewDao revDao = new ReviewDao();

	private Window_Review reviewView; // 리뷰창

	public void insert(ReviewDto dto) {
		revDao.insert(dto);
	}
	//
	// public List<ReviewDto> select(String menuName) {
	// return revDao.select(menuName);
	// }

	public void delete() {
		revDao.delete();
	}

	public void update(ReviewDto dto) {
		revDao.update(dto);
	}

	public void reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
		reviewView = new Window_Review(menuName);
	}

	public List<ReviewDto> getList() {
		return revDao.getList();
	}

}
