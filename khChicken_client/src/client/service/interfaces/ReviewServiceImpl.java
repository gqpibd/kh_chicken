package client.service.interfaces;

import java.util.List;

import dto.ReviewDto;

public interface ReviewServiceImpl {
	
	public List<ReviewDto> select(String menuName) ;

	public List<ReviewDto> selectByUserId(ReviewDto dto) ;

	public void update(ReviewDto dto);

	public List<ReviewDto> getList() ;

	public ReviewDto WritableReview(ReviewDto rDto) ;
}
