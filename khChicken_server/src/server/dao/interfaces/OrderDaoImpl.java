package server.dao.interfaces;

import java.net.Socket;

import dto.OrderedMenuDto;

public interface OrderDaoImpl {
	public void execute(int number, OrderedMenuDto dto, Socket sock);

	public void insert(OrderedMenuDto dto);

	public void selectAvailableCoupon(OrderedMenuDto oDto, Socket sock);
}
