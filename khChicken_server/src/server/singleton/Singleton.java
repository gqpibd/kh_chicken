package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
<<<<<<< HEAD
	
	MemberDao ctrlMember = new MemberDao();
	MenuDao ctrlMenu = new MenuDao();
	OrderDao ctrlOrder = new OrderDao();
	ReviewDao ctrlReview = new ReviewDao();
=======
	public MemberDao ctrlMember = new MemberDao();
	public MenuDao ctrlMenu = new MenuDao();
	public OrderDao ctrlOrder = new OrderDao();
	public ReviewDao ctrlReview = new ReviewDao();
>>>>>>> refs/remotes/origin/daseul
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	
	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> refs/remotes/origin/daseul

}
