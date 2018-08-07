package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	public MemberDao ctrlMember;
	public MenuDao ctrlMenu;
	public OrderDao ctrlOrder;
	public ReviewDao ctrlReview;
	
	private Singleton() {
		ctrlMember = new MemberDao();
		ctrlMenu = new MenuDao();
		ctrlOrder = new OrderDao();
		ctrlReview = new ReviewDao();
	}
	
	public static Singleton getInstance() {
		return single;
	}
	

}
