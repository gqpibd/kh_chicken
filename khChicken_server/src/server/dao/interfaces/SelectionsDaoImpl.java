package server.dao.interfaces;

import java.net.Socket;

public interface SelectionsDaoImpl {
	public void execute(int number, Object dto, Socket sock);
	public void selectByDate(Socket sock);
	public void selectBySalse(Socket sock);
	public void selectByScore(Socket sock);
	public void selectCustomerOrder(Socket sock) ;
	public void selectAddress(Object obj, Socket sock);
}
