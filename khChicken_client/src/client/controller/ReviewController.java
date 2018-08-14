package client.controller;

import java.util.List;

import javax.swing.JFrame;

import client.dao.ReviewDao;
import client.view.Window_Review;
import client.view.Window_Review_Write;
import dto.ReviewDto;

public class ReviewController {

	private ReviewDao revDao = new ReviewDao();

	private Window_Review reviewView; // 리뷰창
	private Window_Review_Write write_view;

	public void insert(ReviewDto dto) {
		revDao.insert(dto);
	}

	public List<ReviewDto> getList() {
		return revDao.getList();
	}

	public List<ReviewDto> my_getList(ReviewDto dto) {
		return revDao.my_getList(dto);
	}

	public boolean update(ReviewDto dto) {
		return revDao.update(dto);
	}

	public void reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
		reviewView = new Window_Review(menuName);
	}

	public void Write_view(JFrame currentFrame, String menuName) {

		if (write_view == null) {
			write_view = new Window_Review_Write(menuName);
		} else {
			write_view.setVisible(true);
		}
		// revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.

	}
}
