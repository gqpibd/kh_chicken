package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	
	public MemberDao ctrlMember = new MemberDao();
	public MenuDao ctrlMenu = new MenuDao();
	public OrderDao ctrlOrder = new OrderDao();
	public ReviewDao ctrlReview = new ReviewDao();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	

}
