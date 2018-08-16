package server.dao.interfaces;

import java.net.Socket;

import dto.MenuShowDto;

public interface MenuDaoImpl {
	public void execute(int number, MenuShowDto dto, Socket sock);

	public void insert(MenuShowDto dto);

	public void update(MenuShowDto dto);

	public void select(Socket sock);

	public void receiveAndSaveImage(String name, Socket sock);
}
