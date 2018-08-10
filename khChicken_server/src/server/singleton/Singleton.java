package server.singleton;

import server.dao.BestSaleMenuDao;
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
	BestSaleMenuDao ctrlBestOrder = new BestSaleMenuDao();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return single;
	}

	public OrderDao getOrderCtrl() {
		return ctrlOrder;
	}

	public BestSaleMenuDao getBestCtrl() {
		return ctrlBestOrder;
	}

	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}
	
}
