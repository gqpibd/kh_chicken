package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	private MemberDao ctrlMember = new MemberDao();
	private MenuDao ctrlMenu = new MenuDao();
	private OrderDao ctrlOrder = new OrderDao();
	private ReviewDao ctrlReview = new ReviewDao();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	
	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}	

}
