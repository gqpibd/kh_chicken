package client.service;

import java.util.List;

import dto.ReviewDto;

public interface ReviewServiceInter {
	

	public void insert(ReviewDto dto) ;

	public List<ReviewDto> select(String menuName) ;

	public List<ReviewDto> selectByUserId(ReviewDto dto) ;

	public void update(ReviewDto dto);

	public void delete() ;

	public List<ReviewDto> getList() ;

	public ReviewDto WritableReview(ReviewDto rDto) ;
}
