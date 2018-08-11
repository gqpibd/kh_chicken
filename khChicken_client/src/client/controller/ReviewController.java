package client.controller;

import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;

import client.dao.ReviewDao;
import client.view.Window_Review;
import dto.ReviewDto;

public class ReviewController {

	ReviewDao revDao = new ReviewDao();
	Window_Review reviewView;

	public void insert(ReviewDto dto) {
		revDao.insert(dto);
	}

	public List<ReviewDto> select(String menuName) {
		return revDao.select(menuName);
	}

	public void delete() {
		revDao.delete();
	}

	public void update(ReviewDto dto) {
		revDao.update(dto);
	}

	public void reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		if (reviewView == null) {
			reviewView = new Window_Review(menuName);
		} else {
			reviewView.setVisible(true);
		}

	}

}
