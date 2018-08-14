package client.controller;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import client.dao.ReviewDao;
import client.view.ReviewDialog;
import client.view.ReviewWriteDialog;
import dto.MemberDto;
import dto.ReviewDto;

public class ReviewController {

	private ReviewDao revDao = new ReviewDao();

	private ReviewDialog reviewView; // 리뷰창
	private ReviewWriteDialog write_view;

	public void insert(ReviewDto dto) {
		revDao.insert(dto);
	}

	public List<ReviewDto> getList() {
		return revDao.getList();
	}

	public void update(ReviewDto dto) {
		revDao.update(dto);
	}

	public ReviewDto WritableReview(ReviewDto rDto) { // 내가 작성할 수 있는 리뷰가 있나
		return revDao.WritableReview(rDto);
	}
	
	public boolean reviewView(JFrame currentFrame, String menuName) {
		currentFrame.setVisible(false);
		revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
		reviewView = new ReviewDialog(currentFrame, menuName);
		return reviewView.getSelection();
	}

	public void Write_review(JDialog currentFrame, ReviewDto rDto) {
		write_view = new ReviewWriteDialog(rDto);		
		// revDao.select(menuName); // 리뷰를 불러와 리뷰 다오에 있는 리스트를 셋팅해준다.
	}
	
	public double getNewScore() { 
		return write_view.getNewScore();
	}
	
	public ReviewDto getNewReview() {
		return write_view.getNewReview();
	}

	public List<ReviewDto> selectByUserId(ReviewDto rdto) {
		return revDao.selectByUserId(rdto);
	}


}
