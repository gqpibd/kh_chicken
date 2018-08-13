package client.controller;

import java.util.List;

import javax.swing.JFrame;

import client.dao.ReviewDao;
import client.view.Window_Review;
<<<<<<< HEAD
=======
import client.view.Window_Review_While;
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
import client.view.Window_Testview;
import dto.ReviewDto;

public class ReviewController {

<<<<<<< HEAD
	private ReviewDao revDao = new ReviewDao();

	private Window_Review reviewView; // 리뷰창
	private Window_Testview _reviewView;
=======

	private ReviewDao revDao = new ReviewDao();

	//private Window_Review reviewView; // 리뷰창
	private Window_Testview reviewView;
	private Window_Review_While reviewWhileView;
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi

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

<<<<<<< HEAD
	public void update(ReviewDto dto) {
		revDao.update(dto);
=======
	public boolean update(ReviewDto dto) {
		return revDao.update(dto);
	}

	public void reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
		reviewView = new Window_Testview(menuName);
		//reviewView = new Window_Review(menuName);
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
	}
<<<<<<< HEAD

	public void reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
		_reviewView = new Window_Testview(menuName);
		reviewView = new Window_Review(menuName);
=======
	
	public void reviewWhileView(JFrame currentFrame, String menuName) {
	//public void reviewWhileView(JFrame currentFrame) {
		if (reviewWhileView== null ) {
			//reviewWhileView =new Window_Review_While(menuName);
			reviewWhileView =new Window_Review_While(menuName);
		}else {
			reviewWhileView.setVisible(true);
		}
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
	}

	public List<ReviewDto> getList() {
		return revDao.getList();
	}

}
