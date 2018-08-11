package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {

	public static final int INSERT = 0;
	public static final int SELECT = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;

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

	public OrderDao getOrderCtrl() {
		return ctrlOrder;
	}

	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}

	public MemberDao getMemCtrl() {
		return ctrlMember;
	}
	
	public ReviewDao getRevCtrl() {
		return ctrlReview;
	}
	
}
