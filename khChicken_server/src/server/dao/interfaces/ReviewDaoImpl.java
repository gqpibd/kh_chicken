package server.dao.interfaces;

import java.net.Socket;

import dto.ReviewDto;

public interface ReviewDaoImpl {
	public void execute(int number, ReviewDto dto, Socket sock);

	public void update(ReviewDto dto);

	public void select(ReviewDto dto, Socket sock);

	public void select_WritableReview(ReviewDto dto, Socket sock);

	public void select_byUserId(ReviewDto dto, Socket sock);
}
