package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	MemberDao ctrlMember = new MemberDao();
	MenuDao ctrlMenu = new MenuDao();
	OrderDao ctrlOrder = new OrderDao();
	ReviewDao ctrlReview = new ReviewDao();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	
	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}
	

}
